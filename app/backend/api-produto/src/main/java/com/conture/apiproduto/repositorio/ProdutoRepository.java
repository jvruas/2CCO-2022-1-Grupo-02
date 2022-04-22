package com.conture.apiproduto.repositorio;

import com.conture.apiproduto.entity.ProdutoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoDoacao, Long>{
	ProdutoDoacao findByIdProdutoDoacaoAndFkDoador(Long idProdutoDoacao, Long fkDoador);

	List<ProdutoDoacao> findByFkCategoriaProduto(Long fkCategoriaProduto);

	List<ProdutoDoacao> findByMarca(String marca);

	List<ProdutoDoacao> findByNome(String nome);

	List<ProdutoDoacao> findByFkDoadorAndStatus(Long fkDoador, String status);

	ProdutoDoacao findByIdDonatarioAndStatus(Long fkDoador, String status);

	ProdutoDoacao findByMatch(Long fkDoador, Long idProdutoDoacao);

	int countByVisualizacao(Long fkDoador, Long idProdutoDoacao);

	void deleteByFkDoadorAndIdProdutoDoacao(Long fkDoador, Long idProdutoDoacao);

	ProdutoDoacao deleteByMatch(Long fkDoador, Long fkDonatario , Long idProdutoDoacao);

//	@Query("update ")
//	@Transactional
//	@Modifying

}
