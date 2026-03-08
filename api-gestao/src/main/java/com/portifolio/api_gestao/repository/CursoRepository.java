package com.portifolio.api_gestao.repository;

import com.portifolio.api_gestao.model.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository  extends CrudRepository<Curso, Long> {
}
