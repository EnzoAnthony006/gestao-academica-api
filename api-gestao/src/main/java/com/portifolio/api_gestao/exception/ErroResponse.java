package com.portifolio.api_gestao.exception;

import java.time.LocalDateTime;

public class ErroResponse {

    private int status;
    private String mensagem;
    private String path;
    private LocalDateTime timestamp;

    public ErroResponse(int status, String mensagem, String path) {
        this.status = status;
        this.mensagem = mensagem;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() { return status; }
    public String getMensagem() { return mensagem; }
    public String getPath() { return path; }
    public LocalDateTime getTimestamp() { return timestamp; }
}