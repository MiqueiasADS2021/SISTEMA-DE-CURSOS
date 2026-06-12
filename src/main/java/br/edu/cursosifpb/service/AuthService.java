package br.edu.cursosifpb.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    //Injeção de dependência para ter acesso aos usuários já existentes
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String SECRET_KEY = "alquimiasistemas@cod-chave-secreta-jwt-2026";

    public String login(String username, String password){
        UserDetails user = inMemoryUserDetailsManager.loadUserByUsername(username);

        if(passwordEncoder.matches(password,user.getPassword())){
            return Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("roles", user.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                    .compact();
        }else{
            throw new RuntimeException("Senha Inválida");
        }
    }
}
