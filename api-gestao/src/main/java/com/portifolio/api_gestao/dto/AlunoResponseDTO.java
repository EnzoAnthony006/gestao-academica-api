package com.portifolio.api_gestao.dto;

public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private Long matricula;

    public AlunoResponseDTO(Long id, String nome, String email, Long matricula) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Long getMatricula() { return matricula; }
}