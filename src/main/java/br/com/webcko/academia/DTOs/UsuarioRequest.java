package br.com.webcko.academia.DTOs;

import br.com.webcko.academia.entity.UsuarioRole;
import lombok.Getter;
import lombok.Setter;

public class UsuarioRequest {
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String senha;
    @Getter
    @Setter
    private UsuarioRole role;
}