package br.edu.cursosifpb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AlunoResponseDTO {

    private String nome;
    private Date dataNascimento;
    private String email_pessoal;
    private String telefone;
    private String matricula;

}