package br.com.webcko.academia.DTOs;

import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.entity.UsuarioRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

public class UsuarioRequest extends Usuario {
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String telefone;
    @Getter
    @Setter
    private String senha;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private UsuarioRole role;
}