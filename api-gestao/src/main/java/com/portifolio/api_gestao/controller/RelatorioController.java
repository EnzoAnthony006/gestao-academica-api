package com.portifolio.api_gestao.controller;

import com.portifolio.api_gestao.dto.RelatorioDTO;
import com.portifolio.api_gestao.service.RelatorioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RelatorioDTO gerar() {
        return relatorioService.gerarRelatorio();
    }
}