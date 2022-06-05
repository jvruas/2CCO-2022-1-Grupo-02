package com.conture.apiproduto.repository;

import com.conture.apiproduto.model.entity.ImagemProdutoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagemProdutoDoacaoRepository extends JpaRepository<ImagemProdutoDoacao, Integer> {
	int countByProdutoDoacaoIdProdutoDoacao(Integer idProdutoDoacao);

	Optional<ImagemProdutoDoacao> findTop1ByProdutoDoacaoIdProdutoDoacaoOrderByIdImagemProdutoDoacaoDesc(Integer idProdutoDoacao);
}
