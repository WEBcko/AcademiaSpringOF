//package br.com.webcko.academia.components;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.flywaydb.core.internal.util.StringUtils;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TokenValidationService {
//
//    private static final String CHAVE_SECRETA = "SuaChaveSecreta";
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(CHAVE_SECRETA).parseClaimsJws(token);
//            return true;
//        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    public Claims getClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(CHAVE_SECRETA).parseClaimsJws(token).getBody();
//    }
//
//    public String getTokenFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
