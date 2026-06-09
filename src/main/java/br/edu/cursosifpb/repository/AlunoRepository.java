package br.edu.cursosifpb.repository;

import br.edu.cursosifpb.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByCpf(String cpf);
    List<Aluno> findByNome(String nome);
}
