package com.conture.apiproduto.repository;

import com.conture.apiproduto.model.dto.response.MatchResponse;
import com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse;
import com.conture.apiproduto.model.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Integer> {
	boolean existsByProdutoDoacaoIdProdutoDoacaoAndFkDonatario(Integer idProdutoDoacao, Integer fkDonatario);

	Optional<Match> findTop1ByStatusFalseAndVisualizadoFalseAndProdutoDoacaoIdProdutoDoacaoAndFkDonatarioOrderByIdMatchDesc(Integer idProdutoDoacao, Integer fkDonatario);

	@Query("select m.idMatch from Match m where m.fkDonatario = ?2 and m.status = false and m.produtoDoacao.idProdutoDoacao = ?1 and m.produtoDoacao.removido = false and m.produtoDoacao.status = false")
	Optional<Integer> getIdMatchByIdProdutoAndIdDonatario(Integer idProdutoDoacao, Integer idDonatario);

	@Query("select new com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse(m.produtoDoacao.nome, m.produtoDoacao.marca, m.produtoDoacao.modelo, m.produtoDoacao.descricao, m.produtoDoacao.defeito, m.produtoDoacao.entrega, m.produtoDoacao.quantidadeVisualizacao, m.produtoDoacao.dataCriacao, m.produtoDoacao.dataConclusao, m.produtoDoacao.status, m.produtoDoacao.categoriaProduto.nome, m.produtoDoacao.fkDoador) from Match m where m.status = false and m.produtoDoacao.status = false and m.produtoDoacao.removido = false and m.fkDonatario = ?1 order by m.dataInteresse desc")
	List<ProdutoDoacaoResponse> getAllByStatusAndamento(Integer fkDonatario);

	@Query("select new com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse(m.produtoDoacao.nome, m.produtoDoacao.marca, m.produtoDoacao.modelo, m.produtoDoacao.descricao, m.produtoDoacao.defeito, m.produtoDoacao.entrega, m.produtoDoacao.quantidadeVisualizacao, m.produtoDoacao.dataCriacao, m.produtoDoacao.dataConclusao, m.produtoDoacao.status, m.produtoDoacao.categoriaProduto.nome, m.produtoDoacao.fkDoador) from Match m where m.status = true and m.produtoDoacao.status = true and m.produtoDoacao.removido = false and m.fkDonatario = ?1 order by m.produtoDoacao.dataConclusao desc")
	List<ProdutoDoacaoResponse> getAllByStatusRecebido(Integer fkDonatario);

	@Query("select new com.conture.apiproduto.model.dto.response.MatchResponse(m.matchPorcentagem, m.dataInteresse, m.fkDonatario) from Match m where m.produtoDoacao.idProdutoDoacao = ?1 and m.status = false and m.produtoDoacao.status = false and m.produtoDoacao.removido = false order by m.matchPorcentagem desc, m.dataInteresse asc")
	List<MatchResponse> getAllMatchResponseByStatusFalseAndIdProduto(Integer idProdutoDoacao);

	@Query("update Match m set m.visualizado = true where m.produtoDoacao.idProdutoDoacao = ?1")
	@Transactional
	@Modifying
	void updateAllStatusByIdProduto(Integer idProdutoDoacao);


	@Query("select count(m) from Match m where m.produtoDoacao.idProdutoDoacao = ?1 and m.produtoDoacao.removido = false")
	Long countByIdProduto(Integer idProdutoDoacao);

	@Query("select count(m) from Match m where m.produtoDoacao.fkDoador = ?1 and m.visualizado = false and m.produtoDoacao.removido = false and m.produtoDoacao.status = false")
	Long countByVisualizadoFalseAndIdDoador(Integer idDoador);


	@Query("update Match m set m.status = true where m.idMatch = ?1")
	@Transactional
	@Modifying
	void updateStatusTrueById(Integer idMatch);
}
