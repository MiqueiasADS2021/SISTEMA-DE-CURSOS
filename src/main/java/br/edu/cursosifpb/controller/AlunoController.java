package br.edu.cursosifpb.controller;
import br.edu.cursosifpb.model.Aluno;
import br.edu.cursosifpb.model.Curso;
import br.edu.cursosifpb.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    //Injeção de dependência
    @Autowired
    private AlunoRepository alunoRepository;

    //Criando os endpoints

    //Listar os Alunos
    @GetMapping
    public List<Aluno> listarAlunos(){
        return alunoRepository.findAll();
    }

    @PostMapping("/cadastrar")
    //Cadastra Alunos
    public void cadastrarAluno(@RequestBody Aluno a){
        alunoRepository.save(a);
        return;
    }

    @DeleteMapping("/remover/{id}")
    //Remover Alunos
    public void deleteAluno(@PathVariable long id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
        }
    }

    @DeleteMapping("/remover")
    //Remover Alunos
    public void deleteAlunoversao02(@RequestParam long id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
        }
    }

    @PutMapping("/editar/{id}")
    //Editar Aluno
    public void editarAluno(@PathVariable long id, @RequestBody Aluno alunoAtualizado){
        if(alunoRepository.existsById(id)){
            alunoAtualizado.setId(id);
            alunoRepository.save(alunoAtualizado);
        }
    }
}
