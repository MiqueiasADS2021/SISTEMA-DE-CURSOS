package br.edu.cursosifpb.controller;

import br.edu.cursosifpb.model.Aluno;
import br.edu.cursosifpb.model.Curso;
import br.edu.cursosifpb.model.Matricula;
import br.edu.cursosifpb.repository.AlunoRepository;
import br.edu.cursosifpb.repository.CursoRepository;
import br.edu.cursosifpb.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //Criando os endpoints

    //Listar as Matriculas
    @GetMapping("/listar")
    public List<Matricula> listarMatriculas() {
        return matriculaRepository.findAll();
    }

    @PostMapping("/matricular")
    public void realizarMatricula(
            @RequestParam Long alunoId,
            @RequestParam Long cursoId,
            @RequestParam int matricula
    ) {
        // Buscar aluno e curso pelo ID
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        // Criar a matrícula
        Matricula matriculaObj = new Matricula();
        matriculaObj.setAluno(aluno);
        matriculaObj.setCurso(curso);

        // Gerar número da matrícula
        int matricula_n =  java.time.Year.now().getValue() + matricula;
        matriculaObj.setMatricula(matricula_n);

        // Salvar no banco
        matriculaRepository.save(matriculaObj);
    }
}
