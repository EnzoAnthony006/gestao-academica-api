package com.portifolio.api_gestao.dto;

import com.portifolio.api_gestao.model.NivelCurso;
import java.time.LocalDateTime;

public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Integer cargaHoraria;
    private NivelCurso nivel;
    private boolean ativo;
    private LocalDateTime dataCriacao;

    public CursoResponseDTO(Long id, String nome, String descricao, Integer cargaHoraria,
                            NivelCurso nivel, boolean ativo, LocalDateTime dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.nivel = nivel;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public Integer getCargaHoraria() { return cargaHoraria; }
    public NivelCurso getNivel() { return nivel; }
    public boolean isAtivo() { return ativo; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
}