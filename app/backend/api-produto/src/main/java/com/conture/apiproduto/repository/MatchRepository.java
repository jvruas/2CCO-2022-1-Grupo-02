package com.conture.apiproduto.repository;

import com.conture.apiproduto.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
	List<Match> findByFkDoadorAndFkProdutoDoacao(Long fkDoador, Long fkProdutoDoacao);

	Long countByFkDoadorAndFkProdutoDoacao(Long fkDoador, Long fkProdutoDoacao);

	@Transactional
	@Modifying
	void deleteByFkDoadorAndFkProdutoDoacaoAndFkDonatario(Long fkDoador, Long fkProdutoDoacao, Long fkDonatario);

	Match findByFkDoadorAndFkProdutoDoacaoAndFkDonatario(Long fkDoador, Long fkProdutoDoacao, Long fkDonatario);

//	@Transactional
//	@Modifying
//	@Query("update Match m set m.status = true where m.fkDoador = ?1 and m.fkProdutoDoacao = ?2 and m.fkDonatario = ?3")
//	void updateMatchSetStatus(Long fkDoador, Long fkProdutoDoacao, Long fkDonatario );
}
