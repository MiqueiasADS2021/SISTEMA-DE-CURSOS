package br.edu.cursosifpb.controller;
import br.edu.cursosifpb.model.Aluno;
import br.edu.cursosifpb.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    //Injeção de dependência
    private AlunoService alunoService;

    //Listar os Alunos
    @GetMapping
    public List<Aluno> listarAlunos(){
        return alunoService.listarAlunos();
    }

    //Cadastra Alunos
    @PostMapping("/cadastrar")
    public Aluno cadastrarAluno(@RequestBody Aluno aluno){
        return alunoService.cadastrarAluno(aluno);
    }

    //Remover Alunos
    @DeleteMapping("/remover/{id}")
    public void deleteAluno(@PathVariable long id){
        alunoService.removerAluno(id);
    }

    @PutMapping("/editar/{id}")
    //Editar Aluno
    public void editarAluno(@PathVariable long id, @RequestBody Aluno alunoAtualizado){
        alunoService.editarAluno(id,alunoAtualizado);
    }
}
