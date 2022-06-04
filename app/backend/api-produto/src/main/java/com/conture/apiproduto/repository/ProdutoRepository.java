package com.conture.apiproduto.repository;

import com.conture.apiproduto.entity.ProdutoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoDoacao, Integer>{

	Optional<ProdutoDoacao> findTop1ByRemovidoFalseAndStatusFalseOrderByIdProdutoDoacaoDesc();

	@Query("select case when count(p) = 1 then true else false end from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.removido = false and p.status = false")
	boolean hasById(Integer idProdutoDoacao);

	@Query("select case when count(p) = 1 then true else false end from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.fkDoador = ?2 and p.removido = false and p.status = false")
	boolean hasByIdAndFkDoador(Integer idProdutoDoacao, Integer idDoador);

	@Query("select case when count(p) = 1 then true else false end from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.imagemPrincipal <> null and p.removido = false and p.status = false")
	boolean imagemPrincipalIsNotNull(Integer idProdutoDoacao);

//	Optional<ProdutoDoacao> findByIdProdutoDoacaoAndUsuarioIdUsuario(Long idProdutoDoacao, Integer idUsuario);
//
//	@Query("SELECT p FROM ProdutoDoacao p WHERE lower(p.nome) LIKE lower(concat('%', ?1, '%'))")
//	List<ProdutoDoacao> acharPeloNomeIgnoreCase(String nome);
//
//
//	List<ProdutoDoacao> findByUsuarioIdUsuarioAndStatus(Long fkDoador, boolean status);
//
//	List<ProdutoDoacao> findByUsuarioIdUsuario(Long fkDoador);
//
//	@Modifying
//	@Transactional
//	void deleteByUsuarioIdUsuarioAndIdProdutoDoacao(Long fkDoador, Long idProdutoDoacao);
//
//	@Transactional
//	@Modifying
//	@Query("update ProdutoDoacao p set p.status = true, p.dataConclusao = ?3 where p.fkDoador = ?1 and p.idProdutoDoacao = ?2")
//	void updateProdutoDoacaoConcluido(Long fkDoador, Long idProdutoDoacao, Date timeStampConclusao);
//
////	@Query("update ProdutoDoacao p set p.quantidadeVisualizacao = ?3 where p.usuarioIdUsuario = ?1 and p.idProdutoDoacao = ?2")
////	@Transactional
////	@Modifying
////	void updateProdutoDoacaoSetQuantidadeVisualizacao(Long fkDoador, Long idProdutoDoacao, int quantidadeVisualizacao);

	@Query("update ProdutoDoacao p set p.imagemPrincipal = ?2 where p.idProdutoDoacao = ?1 and p.removido = false and p.status = false")
	@Transactional
	@Modifying
	void updateProdutoImagemPrincipal(Integer idProdutoDoacao, byte[] imagemPrincipal);
}
