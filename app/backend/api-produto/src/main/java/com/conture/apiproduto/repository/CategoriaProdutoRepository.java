package com.conture.apiproduto.repository;

import com.conture.apiproduto.entity.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {
	Optional<CategoriaProduto> findByNomeIgnoreCase(String nome);
}
