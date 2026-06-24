package br.edu.cursosifpb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @Schema(
            description = "Nome de usuário utilizado no login",
            example = "admin"
    )
    private String username;

    @Schema(
            description = "Senha do usuário",
            example = "142536"
    )
    private String password;
}
