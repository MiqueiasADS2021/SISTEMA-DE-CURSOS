package br.edu.cursosifpb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //Aqui que a requisição HTTP chega e é filtrada
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("alunos/cadastrar").hasRole("ADMIN")
                                .requestMatchers("/alunos").authenticated()
                )
                .httpBasic(Customizer.withDefaults()); //Autenticação básica
        return http.build();
    }

    //Usuários em memória para testes
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        UserDetails aluno = User.builder()
                .username("aluno")
                .password(encoder.encode("1234"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("142536"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(aluno, admin);
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
