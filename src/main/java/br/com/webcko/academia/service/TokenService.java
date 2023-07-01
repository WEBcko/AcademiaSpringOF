package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    private static final String CHAVE_SECRETA = "SuaChaveSecreta";


    public String gerarToken(Usuario usuario) {
        Key chave = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("id", usuario.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(chave)
                .compact();
    }
}
