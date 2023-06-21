package br.com.webcko.academia.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.webcko.academia.entity.Permissoes.*;

@RequiredArgsConstructor//gera contrutores automaticamente
public enum UsuarioRole {
    CLIENTE(Collections.emptySet()),//conjunto de permissoes vazias para clientes
    ADMIN(
            Set.of(//cria um conjunto fixo de permissoes especificas
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    PERSONAL_READ,
                    PERSONAL_UPDATE,
                    PERSONAL_CREATE
            )
    ),
    PERSONAL(
            Set.of(
                    PERSONAL_READ,
                    PERSONAL_UPDATE,
                    PERSONAL_CREATE
            )
    );

    @Getter
    private final Set<Permissoes> permissions;//criado para acessar as permissoes do enum Permissoes

    public List<SimpleGrantedAuthority> getAuthorities() {//retorna na lista de permissao de cada role
        var authorities = getPermissions()//pego as permissoes do enum Permissoes
                .stream()//mapeio cada permissao para uma permissao correspondente
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        //com o getPermission de cada permissao temos a permissao em String que é passado para SimpleGranted...
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
        //adiciona a autoridade de nível superior (ROLE) com o nome do tipo de usuário
        //convenção do spring security, peguei na net
    }
}