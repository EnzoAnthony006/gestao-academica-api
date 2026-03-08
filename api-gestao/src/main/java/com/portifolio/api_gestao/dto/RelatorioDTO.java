package com.portifolio.api_gestao.dto;

public class RelatorioDTO {
    private Long totalAlunos;
    private Long totalCursos;
    private Long totalMatriculas;

    public Long getTotalAlunos() {
        return totalAlunos;
    }

    public void setTotalAlunos(Long totalAlunos) {
        this.totalAlunos = totalAlunos;
    }

    public Long getTotalCursos() {
        return totalCursos;
    }

    public void setTotalCursos(Long totalCursos) {
        this.totalCursos = totalCursos;
    }

    public Long getTotalMatriculas() {
        return totalMatriculas;
    }

    public void setTotalMatriculas(Long totalMatriculas) {
        this.totalMatriculas = totalMatriculas;
    }
}
