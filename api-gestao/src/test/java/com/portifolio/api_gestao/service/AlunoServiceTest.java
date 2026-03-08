package com.portifolio.api_gestao.service;

import com.portifolio.api_gestao.dto.AlunoRequestDTO;
import com.portifolio.api_gestao.dto.AlunoResponseDTO;
import com.portifolio.api_gestao.exception.ResourceNotFoundException;
import com.portifolio.api_gestao.model.Aluno;
import com.portifolio.api_gestao.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno;
    private AlunoRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João Silva");
        aluno.setEmail("joao@email.com");

        requestDTO = new AlunoRequestDTO();
        requestDTO.setNome("João Silva");
        requestDTO.setEmail("joao@email.com");
    }

    @Test
    void deveSalvarAlunoComSucesso() {
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

        AlunoResponseDTO response = alunoService.salvar(requestDTO);

        assertNotNull(response);
        assertEquals("João Silva", response.getNome());
        assertEquals("joao@email.com", response.getEmail());
        verify(alunoRepository, times(1)).save(any(Aluno.class));
    }

    @Test
    void deveListarTodosAlunos() {
        when(alunoRepository.findAll()).thenReturn(List.of(aluno));

        List<AlunoResponseDTO> alunos = alunoService.listarTodos();

        assertNotNull(alunos);
        assertEquals(1, alunos.size());
        assertEquals("João Silva", alunos.get(0).getNome());
    }

    @Test
    void deveBuscarAlunoPorIdComSucesso() {
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        AlunoResponseDTO response = alunoService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("João Silva", response.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoAlunoNaoEncontrado() {
        when(alunoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> alunoService.buscarPorId(99L));
    }

    @Test
    void deveDeletarAlunoComSucesso() {
        when(alunoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(alunoRepository).deleteById(1L);

        assertDoesNotThrow(() -> alunoService.deletar(1L));
        verify(alunoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarAlunoInexistente() {
        when(alunoRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> alunoService.deletar(99L));
        verify(alunoRepository, never()).deleteById(any());
    }

    @Test
    void deveAtualizarAlunoComSucesso() {
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

        AlunoRequestDTO novoDTO = new AlunoRequestDTO();
        novoDTO.setNome("João Atualizado");
        novoDTO.setEmail("joaoatualizado@email.com");

        AlunoResponseDTO response = alunoService.atualizar(1L, novoDTO);

        assertNotNull(response);
        verify(alunoRepository, times(1)).save(any(Aluno.class));
    }
}