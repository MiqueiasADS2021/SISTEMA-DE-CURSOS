package br.edu.cursosifpb.repository;

import br.edu.cursosifpb.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c WHERE c.nome LIKE %:nome%")
    List<Curso> buscarPorNome(@Param("nome") String nome);

    @Query("SELECT c FROM Curso c WHERE c.nome LIKE %:nome%")
    List<Curso> buscarPorNomeIntegra(@Param("nome") String nome);

    @Query("SELECT c FROM Curso c WHERE c.cargaHoraria >= :cargaHorariaTotal")
    List<Curso> buscarPorCargaHorariaMinima (@Param("cargaHorariaTotal") int cargaHorariaTotal);
}
