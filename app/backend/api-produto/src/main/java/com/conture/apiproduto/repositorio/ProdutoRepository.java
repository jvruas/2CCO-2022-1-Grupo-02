package com.conture.apiproduto.repositorio;

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

	List<ProdutoDoacao> findByNome(String nome);

	List<ProdutoDoacao> findByFkDoadorAndStatus(Long fkDoador, String status);

//	ProdutoDoacao findByIdDonatarioAndStatus(Long fkDoador, String status);

	List<ProdutoDoacao> findByFkDoador(Long fkDoador);

//	List<ProdutoDoacao> findByFkDonatario(Long fkDonatario);



	void deleteByFkDoadorAndIdProdutoDoacao(Long fkDoador, Long idProdutoDoacao);



	@Transactional
	@Modifying
	@Query("update ProdutoDoacao p set p.status = true where p.fkDoador = ?1 and p.idProdutoDoacao = ?2")
	void updateProdutoDoacaoSetStatus(Long fkDoador, Long idProdutoDoacao);


	@Transactional
	@Modifying
	@Query("update ProdutoDoacao p set p.quantidadeVizualicao = ?3 where p.fkDoador = ?1 and p.idProdutoDoacao = ?2")
	void updateProdutoDoacaoSetQuantidadeVisualizacao(Long fkDoador, Long idProdutoDoacao, int quantidadeVisualizacao);

//	@Query("update ")
//	@Transactional
//	@Modifying

}
