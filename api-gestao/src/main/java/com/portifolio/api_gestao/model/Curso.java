package com.portifolio.api_gestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Carga horária é obrigatória")
    private Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    private NivelCurso nivel;

    private boolean ativo;

    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "curso")
    private List<Matricula> matriculas;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(Integer cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public NivelCurso getNivel() { return nivel; }
    public void setNivel(NivelCurso nivel) { this.nivel = nivel; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public List<Matricula> getMatriculas() { return matriculas; }
    public void setMatriculas(List<Matricula> matriculas) { this.matriculas = matriculas; }
}