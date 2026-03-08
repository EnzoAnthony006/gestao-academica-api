package com.portifolio.api_gestao.service;

import com.portifolio.api_gestao.dto.RelatorioDTO;
import com.portifolio.api_gestao.model.Aluno;
import com.portifolio.api_gestao.repository.AlunoRepository;
import com.portifolio.api_gestao.repository.CursoRepository;
import com.portifolio.api_gestao.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final MatriculaRepository matriculaRepository;

    public RelatorioService(AlunoRepository alunoRepository,
                            CursoRepository cursoRepository,
                            MatriculaRepository matriculaRepository) {

        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
        this.matriculaRepository = matriculaRepository;

    }
    public RelatorioDTO gerarRelatorio(){

        RelatorioDTO relatorio = new RelatorioDTO();

        relatorio.setTotalAlunos(alunoRepository.count());
        relatorio.setTotalCursos(cursoRepository.count());
        relatorio.setTotalMatriculas(matriculaRepository.count());

        return relatorio;
    }
}
