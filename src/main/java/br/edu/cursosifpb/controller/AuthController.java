package br.edu.cursosifpb.controller;

import br.edu.cursosifpb.dto.LoginRequestDTO;
import br.edu.cursosifpb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody LoginRequestDTO loginRequestDTO){
        try{
            String token = authService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
            return Map.of("token",token);
        }catch(Exception e){
            e.printStackTrace();
            return Map.of("token","tente novamente");
        }

    }
}
