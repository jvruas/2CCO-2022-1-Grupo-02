package com.conture.apiusuario.repository;

import com.conture.apiusuario.entity.SituacaoAtual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SituacaoAtualRepository extends JpaRepository<SituacaoAtual, Integer> {

	@Override
	List<SituacaoAtual> findAll();
}
