package com.portifolio.api_gestao.repository;

import com.portifolio.api_gestao.model.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
}
