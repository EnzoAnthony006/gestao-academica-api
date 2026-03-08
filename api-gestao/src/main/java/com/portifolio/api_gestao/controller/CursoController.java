package com.portifolio.api_gestao.controller;

import com.portifolio.api_gestao.dto.CursoRequestDTO;
import com.portifolio.api_gestao.dto.CursoResponseDTO;
import com.portifolio.api_gestao.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CursoResponseDTO> criarCurso(@Valid @RequestBody CursoRequestDTO dto) {
        return ResponseEntity.ok(cursoService.salvar(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<List<CursoResponseDTO>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'ALUNO')")
    public ResponseEntity<CursoResponseDTO> buscarCurso(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CursoResponseDTO> atualizarCurso(@PathVariable Long id,
                                                           @Valid @RequestBody CursoRequestDTO dto) {
        return ResponseEntity.ok(cursoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}