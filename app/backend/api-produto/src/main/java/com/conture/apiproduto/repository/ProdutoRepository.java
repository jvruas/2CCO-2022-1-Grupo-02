package com.conture.apiproduto.repository;

import com.conture.apiproduto.entity.ProdutoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoDoacao, Long>{
	Optional<ProdutoDoacao> findByIdProdutoDoacaoAndUsuarioIdUsuario(Long idProdutoDoacao, Integer idUsuario);

	@Query("SELECT p FROM ProdutoDoacao p WHERE lower(p.nome) LIKE lower(concat('%', ?1, '%'))")
	List<ProdutoDoacao> acharPeloNomeIgnoreCase(String nome);


	List<ProdutoDoacao> findByUsuarioIdUsuarioAndStatus(Long fkDoador, boolean status);

	List<ProdutoDoacao> findByUsuarioIdUsuario(Long fkDoador);

	@Modifying
	@Transactional
	void deleteByUsuarioIdUsuarioAndIdProdutoDoacao(Long fkDoador, Long idProdutoDoacao);

//	@Transactional
//	@Modifying
//	@Query("update ProdutoDoacao p set p.status = true, p.dataConclusao = ?3 where p.fkDoador = ?1 and p.idProdutoDoacao = ?2")
//	void updateProdutoDoacaoConcluido(Long fkDoador, Long idProdutoDoacao, Date timeStampConclusao);

//	@Query("update ProdutoDoacao p set p.quantidadeVisualizacao = ?3 where p.usuarioIdUsuario = ?1 and p.idProdutoDoacao = ?2")
//	@Transactional
//	@Modifying
//	void updateProdutoDoacaoSetQuantidadeVisualizacao(Long fkDoador, Long idProdutoDoacao, int quantidadeVisualizacao);
}
