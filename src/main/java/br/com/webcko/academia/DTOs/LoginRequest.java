package br.com.webcko.academia.DTOs;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String senha;
}
