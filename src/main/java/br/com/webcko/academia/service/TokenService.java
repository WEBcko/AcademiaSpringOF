package br.com.webcko.academia.service;

import br.com.webcko.academia.entity.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Service
@ComponentScan
public class TokenService {

    public static final String CHAVE_SECRETA = "SuaChaveSecretaSuaChaveSecretaSuaChaveSecretaSuaChaveSecreta";

    private Key chave;

    public TokenService() {
        // Inicialize a chave secreta com base na string fixa
        this.chave = new SecretKeySpec(CHAVE_SECRETA.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }
    public String gerarToken(String username, String senha) {
        return Jwts.builder()
                .setSubject(username)
                .claim("senha", senha)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(chave)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(chave).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims obterClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(chave).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return null;
        }
    }
}
