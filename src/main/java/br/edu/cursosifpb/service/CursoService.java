package br.edu.cursosifpb.service;

import br.edu.cursosifpb.model.Curso;
import br.edu.cursosifpb.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listar(){
        return cursoRepository.findAll();
    }

    public List<Curso> buscarPorNome(String nome){
        return cursoRepository.buscarPorNome(nome);
    }

    public List<Curso> buscarPorCargaHorariaMinima(int cargaHoraria){
        return cursoRepository.buscarPorCargaHorariaMinima(cargaHoraria);
    }

    public Curso cadastrarCurso(Curso curso){
        List<Curso> cursosExistentes = cursoRepository.buscarPorNome(curso.getNome());
        if(!cursosExistentes.isEmpty()){
            throw new RuntimeException("Já existe um curso com esse nome " + curso.getNome());
        }
        return cursoRepository.save(curso);
    }

    public Optional<Curso> buscarCursoPorId(Long id){
        return cursoRepository.findById(id);
    }

    public boolean removerCurso(Long id){
        if(cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Optional<Curso> atualizarCurso(Long id, Curso curso){
        if(cursoRepository.existsById(id)){
            curso.setId(id);
            Curso curso_salvo = cursoRepository.save(curso);
            return Optional.of(curso_salvo);
        }
        return Optional.empty();
    }

    public Optional<Curso> atualizarCursoParcial(Long id, Curso cursoAtualizado){
        if(cursoRepository.existsById(id)){
            Optional<Curso> curso_atual = cursoRepository.findById(id);
            if(cursoAtualizado.getNome() != null){
                curso_atual.get().setNome(cursoAtualizado.getNome());
            }
            if(cursoAtualizado.getCargaHoraria() != null){
                curso_atual.get().setCargaHoraria(cursoAtualizado.getCargaHoraria());
            }

            Curso curso_salvo = cursoRepository.save(curso_atual.get());
            return Optional.of(curso_salvo);
        }
        return Optional.empty();
    }

}
