package br.edu.cursosifpb.repository;

import br.edu.cursosifpb.model.Aluno;
import br.edu.cursosifpb.model.Curso;
import br.edu.cursosifpb.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    Optional<Matricula> findByAlunoAndCurso(Aluno aluno, Curso curso);
}
