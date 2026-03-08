package com.portifolio.api_gestao.dto;

import com.portifolio.api_gestao.model.NivelCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CursoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Carga horária é obrigatória")
    private Integer cargaHoraria;

    private NivelCurso nivel;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(Integer cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public NivelCurso getNivel() { return nivel; }
    public void setNivel(NivelCurso nivel) { this.nivel = nivel; }
}