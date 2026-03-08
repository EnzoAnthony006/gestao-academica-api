package com.portifolio.api_gestao.repository;

import com.portifolio.api_gestao.model.Aluno;
import com.portifolio.api_gestao.model.Curso;
import com.portifolio.api_gestao.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    boolean existsByAlunoAndCurso(Aluno aluno, Curso curso);

    List<Matricula> findByAlunoId(Long alunoId);

    List<Matricula> findByCursoId(Long cursoId);
}