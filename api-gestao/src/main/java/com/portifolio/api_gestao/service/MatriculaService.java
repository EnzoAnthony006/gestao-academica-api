package com.portifolio.api_gestao.service;

import com.portifolio.api_gestao.exception.ResourceNotFoundException;
import com.portifolio.api_gestao.model.Aluno;
import com.portifolio.api_gestao.model.Curso;
import com.portifolio.api_gestao.model.Matricula;
import com.portifolio.api_gestao.model.StatusMatricula;
import com.portifolio.api_gestao.repository.AlunoRepository;
import com.portifolio.api_gestao.repository.CursoRepository;
import com.portifolio.api_gestao.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public MatriculaService( MatriculaRepository matriculaRepository,
                             AlunoRepository alunoRepository,
                             CursoRepository cursoRepository
    ) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }
   public Matricula matricular ( Long idAluno, Long idCurso) throws IllegalAccessException {

       Aluno aluno = alunoRepository.findById(idAluno)
               .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

       Curso curso = cursoRepository.findById(idCurso)
               .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

       if (matriculaRepository.existsByAlunoAndCurso(aluno, curso)) {
           throw new IllegalAccessException("Aluno já matriculado neste curso");
       }
       Matricula matricula = new Matricula();
       matricula.setAluno(aluno);
       matricula.setCurso(curso);
       matricula.setStatus(StatusMatricula.ATIVA);

       return matriculaRepository.save(matricula);
   }
    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll();
   }
    public void cancelarMatricula(Long id) {
        Matricula matricula = (Matricula) matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrada"));

        matricula.setStatus(StatusMatricula.CANCELADA);
        matriculaRepository.save(matricula);

   }

}
