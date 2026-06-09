package br.edu.cursosifpb.controller;
import br.edu.cursosifpb.dto.AlunoDTO;
import br.edu.cursosifpb.model.Aluno;
import br.edu.cursosifpb.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    //Injeção de dependência
    @Autowired
    private AlunoService alunoService;

    //Listar os Alunos
    @GetMapping("/listar")
    public List<Aluno> listarAlunos(){
        return alunoService.listarAlunos();
    }

    //Versão com o ResponseEntity
    @GetMapping
    public ResponseEntity<List<Aluno>> listar(){
        return ResponseEntity.status(200).body(alunoService.listarAlunos());
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AlunoDTO alunodto){
        Aluno aluno_response = alunoService.cadastrarAluno(alunodto);
        if (aluno_response != null){
            return ResponseEntity.status(200).body(aluno_response);
        }else{
            return ResponseEntity.status(404).body("Aluno não cadastrado");
        }
    }


    //Remover Alunos
    @DeleteMapping("/remover/{id}")
    public void deleteAluno(@PathVariable long id){
        alunoService.removerAluno(id);
    }

    //Remover alunos com o ResponseEntity
    @DeleteMapping
    public ResponseEntity<?> removerAluno(@PathVariable long id){
        Boolean removido = alunoService.removerAluno(id);
        if(removido){
            return ResponseEntity.status(200).body("Aluno com o id " + id + "removido com Sucesso!");
        }else{
            return ResponseEntity.status(404).body("Aluno não encontrado para o id: " + id);
        }
    }


    @PutMapping("/editar/{id}")
    //Editar Aluno
    public void editarAluno(@PathVariable long id, @RequestBody Aluno alunoAtualizado){
        alunoService.editarAluno(id,alunoAtualizado);
    }
}
