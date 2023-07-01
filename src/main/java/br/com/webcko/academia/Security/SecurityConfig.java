package br.com.webcko.academia.Security;


import br.com.webcko.academia.entity.UsuarioRole;
import br.com.webcko.academia.service.AutenticacaoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.io.IOException;
import java.nio.file.AccessDeniedException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //tentei com o WebSecurityConfigure porem nao funfa aquele negocio, os metodos q ele cria automaticamente nao consegui usar
    //pelo que li, esse é o formato q ta na moda

    @Autowired
    private AutenticacaoService autenticacaoService;




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/usuario/lista").hasRole(UsuarioRole.ADMIN.getPermissions().toString())
                .anyRequest().authenticated()//permitindo qualquer requisicao para essa URL
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//politica de gerenciamento de sessao
                //indica q a API nao vai criar ou usar sessao de servidor pra manter o estado de autenticação
                //compativel com o JWT, nosso gerador de token
                .and();
//                .exceptionHandling()
//                .accessDeniedHandler((request, response, accessDeniedException) -> {
//                    response.setStatus(HttpStatus.FORBIDDEN.value());
//                    response.getWriter().write("Acesso negado: " + accessDeniedException.getMessage());
//                    response.flushBuffer();
//                });
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
