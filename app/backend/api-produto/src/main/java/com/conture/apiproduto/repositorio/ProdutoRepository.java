package com.conture.apiproduto.repositorio;

import com.conture.apiproduto.entity.ProdutoDoacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoDoacaoEntity, Long>{

    ProdutoDoacaoEntity findByProdutoAndDoador(Long idProduto, Long idoador);

    List<ProdutoDoacaoEntity> findByCategoriaNotEquals();

    ProdutoDoacaoEntity findByMarca(String marca);
    ProdutoDoacaoEntity findByNome(String nome);
    ProdutoDoacaoEntity findByIdDoadorandStatus(int idDoador, String status);
    ProdutoDoacaoEntity findByIdDonatarioandStatus(int idDonatario, String status);
    ProdutoDoacaoEntity findByMatch(int idDoador, int idProduto);



}
