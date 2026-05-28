package br.edu.cursosifpb.controller;

import br.edu.cursosifpb.model.Curso;
import br.edu.cursosifpb.repository.CursoRepository;
import br.edu.cursosifpb.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCurso(@RequestBody Curso curso) {
        cursoService.cadastrarCurso(curso);
        return ResponseEntity.status(201).body("Curso cadastrado com sucesso!");
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