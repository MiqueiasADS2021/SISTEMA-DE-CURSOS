package br.edu.cursosifpb.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AlunoDTO {

    @NotBlank(message = "O nome do aluno é obrigatório")
    @Size(min = 5, max = 100,message = "O nome deve possuir no minímo 05 caracteres (insira o nome Completo!)")
    private String nome;
    @Size(min = 11,max = 11, message = "Insira um CPF válido")
    private String cpf;
    @Past(message = "A data de nascimento deve ser no passado")
    private Date dataNascimento;
    @Email(message = "Insira um e-mail válido!")
    private String email_pessoal;
    private String telefone;

}
