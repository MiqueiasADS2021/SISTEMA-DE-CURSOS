package br.edu.cursosifpb.controller;

import br.edu.cursosifpb.model.Curso;
import br.edu.cursosifpb.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    //Injeção de dependencia
    @Autowired
    private CursoRepository cursoRepository;
    //Criando os endpoints

    //Listar os Cursos
    @GetMapping
    public List<Curso> listarCursos(){
        return cursoRepository.findAll();
    }

    @PostMapping("/cadastrar")
    //Cadastra Cursos
    public void cadastrarCurso(@RequestBody Curso c){
        cursoRepository.save(c);
        return;
    }

    @DeleteMapping("/remover/{id}")
    //Remover Curso
    public void deleteCurso(@PathVariable long id){
        if(cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
        }

    }

    @DeleteMapping("/remover")
    public void deleteCursoversao02(@RequestParam long id){
        if(cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
        }
    }

    @PutMapping("/editar/{id}")
    //Editar Curso
    public void editarCurso(@PathVariable long id, @RequestBody Curso cursoatualizado){
        if(cursoRepository.existsById(id)){
            cursoRepository.save(cursoatualizado);
        }

    }
}
