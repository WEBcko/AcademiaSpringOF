package br.com.webcko.academia.DTOs;

import br.com.webcko.academia.entity.UsuarioRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

public class UsuarioRoleRequest {
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private UsuarioRole novoRole;
}