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
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{3}-\\d{2}",
            message = "Insira um CPF no formato adequado ***-***-***-**")
    private String cpf;
    @Pattern(regexp = "[A-Z]+", message="Deve conter apenas letras maiúsculas")
    private String codigo;
    @Past(message = "A data de nascimento deve ser no passado")
    private Date dataNascimento;
    @Email(message = "Insira um e-mail válido!")
    private String email_pessoal;
    private String telefone;

}
