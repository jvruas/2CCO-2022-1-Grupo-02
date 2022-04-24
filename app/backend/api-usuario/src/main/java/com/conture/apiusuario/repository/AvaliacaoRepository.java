package com.conture.apiusuario.repository;

import com.conture.apiusuario.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

	@Override
	List<Avaliacao> findAll();

}
