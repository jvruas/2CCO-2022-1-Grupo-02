package com.conture.apiproduto.repositorio;

import com.conture.apiproduto.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
	List<Match> findByFkDoadorAndFkProdutoDoacao(Long fkDoador, Long fkProdutoDoacao);

	Long countByFkDoadorAndFkProdutoDoacao(Long fkDoador, Long fkProdutoDoacao);
}
