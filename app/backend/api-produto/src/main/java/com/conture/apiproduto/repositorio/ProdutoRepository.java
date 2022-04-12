package com.conture.apiproduto.repositorio;

import com.conture.apiproduto.entity.ProdutoDoacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoDoacaoEntity, Long>{

}
