package br.edu.cursosifpb.service;

import br.edu.cursosifpb.dto.AlunoDTO;
import br.edu.cursosifpb.dto.AlunoResponseDTO;
import br.edu.cursosifpb.model.Aluno;
import br.edu.cursosifpb.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    // Listar todos os alunos retornando DTO de response
    public List<AlunoResponseDTO> listarAlunos(){
        return alunoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // Cadastrar novo aluno
    public AlunoResponseDTO cadastrarAluno(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setEmail_pessoal(alunoDTO.getEmail_pessoal());


        Aluno salvo = alunoRepository.save(aluno);
        return converterParaDTO(salvo);
    }

    // Remover aluno por id
    public boolean removerAluno(Long id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Editar aluno
    public AlunoResponseDTO editarAluno(Long id, Aluno alunoDTO){
        return alunoRepository.findById(id)
                .map(alunoExistente -> {
                    alunoExistente.setNome(alunoDTO.getNome());
                    alunoExistente.setCpf(alunoDTO.getCpf());
                    alunoExistente.setDataNascimento(alunoDTO.getDataNascimento());
                    alunoExistente.setTelefone(alunoDTO.getTelefone());
                    alunoExistente.setEmail_pessoal(alunoDTO.getEmail_pessoal());
                    alunoExistente.setMatricula(alunoDTO.getMatricula());

                    Aluno atualizado = alunoRepository.save(alunoExistente);
                    return converterParaDTO(atualizado);
                }).orElse(null);
    }

    // Método auxiliar para converter entidade em DTO
    private AlunoResponseDTO converterParaDTO(Aluno aluno){
        return new AlunoResponseDTO(
                aluno.getNome(),
                aluno.getCpf(), // ou remover se for dado sensível
                aluno.getDataNascimento(),
                aluno.getEmail_pessoal(),
                aluno.getTelefone(),
                aluno.getMatricula()
        );
    }
}