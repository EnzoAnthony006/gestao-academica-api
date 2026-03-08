package com.portifolio.api_gestao.controller;

import com.portifolio.api_gestao.dto.AlunoRequestDTO;
import com.portifolio.api_gestao.dto.AlunoResponseDTO;
import com.portifolio.api_gestao.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public ResponseEntity<AlunoResponseDTO> criarAluno(@Valid @RequestBody AlunoRequestDTO dto) {
        return ResponseEntity.ok(alunoService.salvar(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<AlunoResponseDTO> buscarAluno(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(@PathVariable Long id,
                                                           @Valid @RequestBody AlunoRequestDTO dto) {
        return ResponseEntity.ok(alunoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}