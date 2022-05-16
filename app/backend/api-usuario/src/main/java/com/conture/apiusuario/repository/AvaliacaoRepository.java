package com.conture.apiusuario.repository;

import com.conture.apiusuario.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

	List<Avaliacao> findAllByFkDoador(Long fkDoador);
}
