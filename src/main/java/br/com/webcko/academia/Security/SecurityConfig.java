package br.com.webcko.academia.Security;


import br.com.webcko.academia.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


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
                .requestMatchers("/api/login").permitAll()
                .requestMatchers("/api/usuario/**").hasRole("ADMIN")
                .anyRequest().authenticated()//permitindo qualquer requisicao para essa URL
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//politica de gerenciamento de sessao
                //indica q a API nao vai criar ou usar sessao de servidor pra manter o estado de autenticação
                //compativel com o JWT, nosso gerador de token
                .and();
        return http.build();
    }
    //peguei esse do codigo do professor, porem ainda nao tem uso, mas pode ser q sim, as veiz pode ser q sim, as veiz pode ser q nao
//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.autenticacaoService)
//                .passwordEncoder(this.passwordEncoder());
//    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
