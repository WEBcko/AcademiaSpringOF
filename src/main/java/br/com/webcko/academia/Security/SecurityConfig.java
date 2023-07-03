package br.com.webcko.academia.Security;


import br.com.webcko.academia.components.JWTAuthenticationFilter;
import br.com.webcko.academia.components.JWTAuthorizationFilter;
import br.com.webcko.academia.entity.UsuarioRole;
import br.com.webcko.academia.service.AutenticacaoService;
import br.com.webcko.academia.service.TokenService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
    //tentei com o WebSecurityConfigure porem nao funfa aquele negocio, os metodos q ele cria automaticamente nao consegui usar
    //pelo que li, esse é o formato q ta na moda

    @Autowired
    private AutenticacaoService autenticacaoService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenService tokenService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers( "/api/usuario/**").hasRole("ADMIN")
                .requestMatchers("/api/configuracao/**").permitAll()
                .requestMatchers("/api/entradasaida/**").permitAll()
                .requestMatchers("/api/exercicio/**").permitAll()
                .requestMatchers("/api/grupo/**").hasRole("ADMIN")
                .requestMatchers("/api/treino/**").permitAll()
                .requestMatchers("/api/treinoexercicios/**").permitAll()
                .requestMatchers("/api/treinoexercicios/**").permitAll()
                .anyRequest().authenticated()//permitindo qualquer requisicao para essa URL
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//politica de gerenciamento de sessao
                //indica q a API nao vai criar ou usar sessao de servidor pra manter o estado de autenticação
                //compativel com o JWT, nosso gerador de token
                .and()
                .addFilter(authenticationFilter(authenticationManager))
                .addFilterBefore(new JWTAuthorizationFilter(tokenService, userDetailsService), UsernamePasswordAuthenticationFilter.class);
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
    @Bean
    public JWTAuthenticationFilter authenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager, tokenService, userDetailsService);
        filter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Unauthorized\"}");
        });
        return filter;
    }

    @Bean
    public JWTAuthorizationFilter authorizationFilter() throws Exception {
        return new JWTAuthorizationFilter(tokenService, userDetailsService);
    }


}
