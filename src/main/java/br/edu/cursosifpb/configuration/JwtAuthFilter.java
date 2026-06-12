package br.edu.cursosifpb.configuration;

import br.edu.cursosifpb.configuration.SecurityConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

    private final String SECRET_KEY = "alquimiasistemas@cod-chave-secreta-jwt-2026";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Capturando o Token
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                Claims claims = Jwts.parserBuilder() //Criando o Parser do Token
                        .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)) //define a chave secreta para assinar e validar o token
                        .build() //finaliza a criação do parser
                        .parseClaimsJws(token) //valida o token, verifica a assinatura e expiração
                        .getBody(); //retorna o claims  do token (username,roles,expiração etc)

                String username = claims.getSubject(); //Capturando o Username
                List<GrantedAuthority> authorities = ((List<Map<String,String>>) claims.get("roles"))
                        .stream()
                        .map(r ->new SimpleGrantedAuthority(r.get("authority")))
                        .collect(Collectors.toList()); //Gerando uma lista de SimpleGrantedAuthority

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username,null,authorities);
                SecurityContextHolder.getContext().setAuthentication(auth); //Esse user está acessivel a todos os controllers

            }catch (Exception e){
                e.printStackTrace();
                SecurityContextHolder.clearContext();// se der errado, limpa o contexto
            }
        }
        filterChain.doFilter(request, response);
    }



}