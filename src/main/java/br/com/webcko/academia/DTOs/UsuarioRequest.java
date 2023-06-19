package br.com.webcko.academia.DTOs;

import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.entity.UsuarioRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;


@Data
public class UsuarioRequest extends Usuario {


    private String nome;

    private String email;

    private String telefone;

    private String senha;

    @Enumerated(EnumType.STRING)
    private UsuarioRole role;
}