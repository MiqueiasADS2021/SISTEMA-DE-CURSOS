package br.edu.cursosifpb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;
    @Column(nullable = false)
    private Date dataNascimento;
    @Column(nullable = false, unique = true)
    private String email_pessoal;
    @Column(nullable = false)
    private String email_academico = "eemail@alquimia.edu.brr";
    @Column(nullable = false, unique = false)
    private String telefone;
    @Column(nullable = false)
    private String matricula;

}