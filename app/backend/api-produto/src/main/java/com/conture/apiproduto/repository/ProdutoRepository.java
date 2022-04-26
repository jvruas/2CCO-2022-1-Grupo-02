package com.conture.apiproduto.repository;

import com.conture.apiproduto.entity.ProdutoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoDoacao, Long>{
	ProdutoDoacao findByIdProdutoDoacaoAndFkDoador(Long idProdutoDoacao, Long fkDoador);

	List<ProdutoDoacao> findByFkCategoriaProduto(Long fkCategoriaProduto);

	List<ProdutoDoacao> findByMarca(String marca);

	List<ProdutoDoacao> findByNomeContainsIgnoreCase(String nome);


	List<ProdutoDoacao> findByFkDoadorAndStatus(Long fkDoador, boolean status);

	List<ProdutoDoacao> findByFkDoador(Long fkDoador);

	@Modifying
	@Transactional
	void deleteByFkDoadorAndIdProdutoDoacao(Long fkDoador, Long idProdutoDoacao);



	@Transactional
	@Modifying
	@Query("update ProdutoDoacao p set p.status = true where p.fkDoador = ?1 and p.idProdutoDoacao = ?2")
	void updateProdutoDoacaoSetStatus(Long fkDoador, Long idProdutoDoacao);


	@Query("update ProdutoDoacao p set p.quantidadeVisualizacao = ?3 where p.fkDoador = ?1 and p.idProdutoDoacao = ?2")
	@Transactional
	@Modifying
	void updateProdutoDoacaoSetQuantidadeVisualizacao(Long fkDoador, Long idProdutoDoacao, int quantidadeVisualizacao);

//	@Query("update ")
//	@Transactional
//	@Modifying

}
