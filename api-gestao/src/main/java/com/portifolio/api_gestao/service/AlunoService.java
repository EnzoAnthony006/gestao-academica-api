package com.portifolio.api_gestao.service;

import com.portifolio.api_gestao.dto.AlunoRequestDTO;
import com.portifolio.api_gestao.dto.AlunoResponseDTO;
import com.portifolio.api_gestao.exception.ResourceNotFoundException;
import com.portifolio.api_gestao.model.Aluno;
import com.portifolio.api_gestao.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoResponseDTO> listarTodos() {
        return ((List<Aluno>) alunoRepository.findAll())
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        return toResponseDTO(aluno);
    }

    public AlunoResponseDTO salvar(AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        return toResponseDTO(alunoRepository.save(aluno));
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        return toResponseDTO(alunoRepository.save(aluno));
    }

    public void deletar(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado");
        }
        alunoRepository.deleteById(id);
    }

    private AlunoResponseDTO toResponseDTO(Aluno aluno) {
        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula()
        );
    }
}