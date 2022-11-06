package com.conture.apiproduto.repository;

import com.conture.apiproduto.model.entity.ImagemProdutoDoacao;
import com.conture.apiproduto.model.entity.ProdutoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImagemProdutoDoacaoRepository extends JpaRepository<ImagemProdutoDoacao, Integer> {
	int countByProdutoDoacaoIdProdutoDoacao(Integer idProdutoDoacao);

	Optional<ImagemProdutoDoacao> findTop1ByProdutoDoacaoIdProdutoDoacaoOrderByIdImagemProdutoDoacaoDesc(Integer idProdutoDoacao);

	@Query("select img from ImagemProdutoDoacao img where img.produtoDoacao.idProdutoDoacao = ?1")
	List<ImagemProdutoDoacao> getAllByIdProdutoDoacao(Integer idProdutoDoacao);
}
