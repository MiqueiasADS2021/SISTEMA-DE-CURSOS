package br.edu.cursosifpb.controller;

import br.edu.cursosifpb.model.Curso;
import br.edu.cursosifpb.repository.CursoRepository;
import br.edu.cursosifpb.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
@Tag(
        name = "Cursos",
        description = "Endpoints responsáveis pelo gerenciamento dos cursos disponíveis no sistema."
)
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(
            summary = "Listar alunos",
            description = "Retorna todos os alunos cadastrados no sistema."
    )
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listar());
    }

    //Metodos Personalizaveis com o @Query
    @GetMapping("/buscar/nome")
    @Operation(
            summary = "Buscar por nome",
            description = "Retorna todos os alunos cadastrados de acordo com o nome inserido."
    )
    public ResponseEntity<?> buscarporNome(@RequestParam String nome){

        List<Curso> cursos = cursoService.buscarPorNome(nome);
        if(!cursos.isEmpty()){
            return ResponseEntity.status(200).body(cursos);
        }else{
            return ResponseEntity.status(404).body("Curso não encontrado para a String de Busca " + nome);
        }

    }

    //Metodos Personalizaveis com o @Query
    @GetMapping("/buscar/cargahoraria")
    public ResponseEntity<?> buscarPorCargaHorariaMinima(@RequestParam int cargaHoraria){
        List<Curso> cursos = cursoService.buscarPorCargaHorariaMinima(cargaHoraria);
        if(!cursos.isEmpty()){
            return ResponseEntity.status(200).body(cursos);
        }else{
            return ResponseEntity.status(404).body("Curso não encontrado para a carga Horária solicitada " + cargaHoraria);
        }
    }


    //Endpoint Simples
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCurso(@RequestBody Curso curso) {
        try{
            cursoService.cadastrarCurso(curso);
            return ResponseEntity.status(201).body("Curso Cadastrado Com Sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }

    }

    //Endpoint para cadastrar varios de uma vez
    @PostMapping("/cadastrar-lista")
    public ResponseEntity<?> cadastrarListaCursos(@RequestBody List<Curso> cursos) {
        // Lista para armazenar cursos que foram cadastrados com sucesso
        List<Curso> cadastrados = new ArrayList<>();

        for(Curso curso : cursos){
            try {
                Curso cursoRetornado = cursoService.cadastrarCurso(curso);
                if(cursoRetornado != null){
                    cadastrados.add(cursoRetornado);
                }
            } catch (RuntimeException e) {
                return ResponseEntity.status(409).body(e.getMessage());
            }
        }

        if(!cadastrados.isEmpty()){
            return ResponseEntity.status(201).body(cadastrados);
        } else {
            return ResponseEntity.status(400).body("Nenhum curso foi cadastrado. Verifique os dados e tente novamente.");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCurso(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.buscarCursoPorId(id);

        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        } else {
            return ResponseEntity.status(404).body("Curso não encontrado para o id: " + id);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable long id) {
        if (cursoService.buscarCursoPorId(id).isPresent()) {
            cursoService.removerCurso(id);
            return ResponseEntity.ok("Curso removido com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Curso não encontrado para o id: " + id);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCurso(@PathVariable long id, @RequestBody Curso cursoAtualizado) {
        if (cursoService.buscarCursoPorId(id).isPresent()) {
            cursoAtualizado.setId(id);
            cursoService.atualizarCurso(id,cursoAtualizado);
            return ResponseEntity.ok("Curso atualizado com sucesso!");
        } else {
            return ResponseEntity.status(404).body(
                    "Falha na atualização de curso! Verifique se o curso existe para o id: " + id
            );
        }
    }

    @PatchMapping("/editar/{id}")
    public ResponseEntity<?> editarCursoParcial(@PathVariable long id, @RequestBody Curso cursoAtualizado) {
        if(cursoService.buscarCursoPorId(id).isPresent()) {
            cursoService.atualizarCursoParcial(id,cursoAtualizado);
            return ResponseEntity.ok("Curso atualizado com sucesso!");
        }else{
            return ResponseEntity.status(404).body("Curso não encontrado para o id: " + id);
        }
    }
}