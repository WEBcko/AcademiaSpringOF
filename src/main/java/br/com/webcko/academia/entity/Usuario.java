package br.com.webcko.academia.entity;

import br.com.webcko.academia.DTOs.LoginRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario extends AbstractEntity implements UserDetails {

    @Getter
    @Setter
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Getter @Setter
    @Column(name = "peso")
    private BigDecimal peso;

    @Getter @Setter
    @Column(name = "altura")
    private BigDecimal altura;

    @Getter @Setter
    @Column(name = "cep", length = 10)
    private String cep;

    @Getter @Setter
    @Column(name = "numero_casa")
    private BigDecimal numeroCasa;

    @Getter @Setter
    @Column(name = "data_nascimento", columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @Getter @Setter
    @Column(name = "telefone", nullable = false, length = 30)
    private String telefone;

    @Getter @Setter
    @Column(name = "cpf", unique = true, length = 16)
    private String cpf;

    @Getter @Setter
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Getter @Setter
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UsuarioRole role;

    public static Usuario fromLoginRequest(LoginRequest loginRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(loginRequest.getUsername());
        usuario.setSenha(loginRequest.getSenha());
        return usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}