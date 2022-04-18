package com.conture.apiproduto.repositorio;

import com.conture.apiproduto.entity.CategoriaProdutoEntity;
import com.conture.apiproduto.entity.ProdutoDoacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProdutoEntity , Long> {

    List<ProdutoDoacaoEntity> findByCategoria(String categoria);
}
