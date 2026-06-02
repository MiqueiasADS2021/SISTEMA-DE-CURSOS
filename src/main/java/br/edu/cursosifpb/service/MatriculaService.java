package br.edu.cursosifpb.service;

import br.edu.cursosifpb.model.Aluno;
import br.edu.cursosifpb.model.Curso;
import br.edu.cursosifpb.model.Matricula;
import br.edu.cursosifpb.repository.AlunoRepository;
import br.edu.cursosifpb.repository.CursoRepository;
import br.edu.cursosifpb.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Cadastrar matrícula
    public Matricula realizarMatricula(Long alunoId, Long cursoId, int matriculaSeq) {
        // Verifica se o aluno existe
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        // Verifica se o curso existe
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        // Verifica se o aluno já está matriculado no curso
        Optional<Matricula> matriculaExistente = matriculaRepository
                .findByAlunoAndCurso(aluno, curso);
        if (matriculaExistente.isPresent()) {
            throw new RuntimeException("Aluno já matriculado nesse curso");
        }

        // Cria a matrícula
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setCurso(curso);

        // Gera o número da matrícula baseado no ano + sequência
        int matriculaGerada = Year.now().getValue() + matriculaSeq;
        matricula.setMatricula(matriculaGerada);

        // Salva no banco
        return matriculaRepository.save(matricula);
    }

    // Buscar matrícula por ID
    public Optional<Matricula> buscarMatriculaPorId(Long id) {
        return matriculaRepository.findById(id);
    }

    // Remover matrícula
    public boolean removerMatricula(Long id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Atualizar matrícula completa
    public Optional<Matricula> editarMatricula(Long id, Matricula matriculaAtualizada) {
        if (matriculaRepository.existsById(id)) {
            matriculaAtualizada.setId(id);
            return Optional.of(matriculaRepository.save(matriculaAtualizada));
        }
        return Optional.empty();
    }

    // Atualizar matrícula parcialmente (PATCH)
    public Optional<Matricula> editarMatriculaParcial(Long id, Matricula matriculaAtualizada) {
        Optional<Matricula> matriculaOptional = matriculaRepository.findById(id);
        if (matriculaOptional.isPresent()) {
            Matricula matricula = matriculaOptional.get();

            // Atualiza apenas se os campos vierem preenchidos
            if (matriculaAtualizada.getAluno() != null) {
                matricula.setAluno(matriculaAtualizada.getAluno());
            }
            if (matriculaAtualizada.getCurso() != null) {
                matricula.setCurso(matriculaAtualizada.getCurso());
            }
            if (matriculaAtualizada.getMatricula() != 0) {
                matricula.setMatricula(matriculaAtualizada.getMatricula());
            }

            return Optional.of(matriculaRepository.save(matricula));
        }
        return Optional.empty();
    }
}