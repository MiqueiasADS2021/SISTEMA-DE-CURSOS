package br.edu.cursosifpb.controller;

import br.edu.cursosifpb.model.Matricula;
import br.edu.cursosifpb.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    //Criando os endpoints

    //Listar as Matriculas
    @GetMapping("/listar/{id}")
    public Optional<Matricula> listarMatriculas(@PathVariable Long id) {
        return matriculaService.buscarMatriculaPorId(id);
    }

    @PostMapping("/matricular")
    public Matricula realizarMatricula(
            @RequestParam Long alunoId,
            @RequestParam Long cursoId,
            @RequestParam int matricula
    ) {
        return matriculaService.realizarMatricula(alunoId,cursoId,matricula);
    }
}
