package com.conture.apiproduto.repository;

import com.conture.apiproduto.entity.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {
	CategoriaProduto findByNome(String nome);
}
