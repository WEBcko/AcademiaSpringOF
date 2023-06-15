package br.com.webcko.academia.DTOs;

import br.com.webcko.academia.entity.UsuarioRole;
import lombok.Getter;
import lombok.Setter;

public class UsuarioRoleRequest {
    @Getter
    @Setter
    private UsuarioRole novoRole;
}