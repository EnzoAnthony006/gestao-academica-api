package com.portifolio.api_gestao.service;

import com.portifolio.api_gestao.dto.CursoRequestDTO;
import com.portifolio.api_gestao.dto.CursoResponseDTO;
import com.portifolio.api_gestao.exception.ResourceNotFoundException;
import com.portifolio.api_gestao.model.Curso;
import com.portifolio.api_gestao.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public CursoResponseDTO salvar(CursoRequestDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        curso.setDescricao(dto.getDescricao());
        curso.setCargaHoraria(dto.getCargaHoraria());
        curso.setNivel(dto.getNivel());
        return toResponseDTO(cursoRepository.save(curso));
    }

    public List<CursoResponseDTO> listarTodos() {
        return ((List<Curso>) cursoRepository.findAll())
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CursoResponseDTO buscarPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + id));
        return toResponseDTO(curso);
    }

    public CursoResponseDTO atualizar(Long id, CursoRequestDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + id));
        curso.setNome(dto.getNome());
        curso.setDescricao(dto.getDescricao());
        curso.setNivel(dto.getNivel());
        curso.setCargaHoraria(dto.getCargaHoraria());
        return toResponseDTO(cursoRepository.save(curso));
    }

    public void deletar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Curso não encontrado com id: " + id);
        }
        cursoRepository.deleteById(id);
    }

    private CursoResponseDTO toResponseDTO(Curso curso) {
        return new CursoResponseDTO(
                curso.getId(),
                curso.getNome(),
                curso.getDescricao(),
                curso.getCargaHoraria(),
                curso.getNivel(),
                curso.isAtivo(),
                curso.getDataCriacao()
        );
    }
}