package br.edu.cursosifpb.service;
import br.edu.cursosifpb.dto.AlunoDTO;
import br.edu.cursosifpb.model.Aluno;
import br.edu.cursosifpb.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlunoService {

    //Injeção de dependência
    @Autowired
    private AlunoRepository alunoRepository;

    //Criação dos metódos CRUD
    public List<Aluno> listarAlunos(){
        return alunoRepository.findAll();
    };

    public Aluno cadastrarAluno(AlunoDTO alunoDTO){
        Aluno aluno = alunoDTO.getNome()]
        return alunoRepository.save(aluno);
    }

    public Boolean removerAluno(Long id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
        }
        return null;
    }

    public void editarAluno(Long id, Aluno alunoAtualizado){
        if(alunoRepository.existsById(id)){
            alunoAtualizado.setId(id);
            alunoRepository.save(alunoAtualizado);
        }
    }


}
