package com.conture.apiproduto.repositorio;

import com.conture.apiproduto.entity.CategoriaProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProdutoEntity , Long> {
}
