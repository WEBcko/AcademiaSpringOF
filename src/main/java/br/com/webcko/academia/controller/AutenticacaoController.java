package br.com.webcko.academia.controller;

import br.com.webcko.academia.DTOs.LoginRequest;
import br.com.webcko.academia.entity.Usuario;
import br.com.webcko.academia.repository.UsuarioRepository;
import br.com.webcko.academia.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){

        System.out.println(loginRequest);
        try {
            // Criar um objeto de autenticação com as credenciais fornecidas
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getSenha())
            );
            System.out.println("authentication: " + authentication);

            // Obter o usuário autenticado a partir do objeto de autenticação
            Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

            String token = tokenService.gerarToken(usuarioAutenticado);

            System.out.println("Token gerado: " + token);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            System.out.println("erro");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
