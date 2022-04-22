package com.conture.apiproduto.repositorio;

import com.conture.apiproduto.entity.ProdutoDoacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoDoacaoEntity, Integer>{

    ProdutoDoacaoEntity findByProdutoDoacaoAndDoador(int idProdutoDoacao, int fkDoador);
    List<ProdutoDoacaoEntity> findByCategoriaNotEquals();
    ProdutoDoacaoEntity findByMarca(String marca);
    ProdutoDoacaoEntity findByNome(String nome);
    ProdutoDoacaoEntity findByfkDoadorandStatus(int fkDoador, String status);
    ProdutoDoacaoEntity findByIdDonatarioandStatus(int idDonatario, String status);
    ProdutoDoacaoEntity findByMatch(int fkDoador, int idProdutoDoacao);
	int countByMatch(int fkDoador, int idProdutoDoacao);
	int countByVisualizacao(int fkDoador, int idProdutoDoacao);
	ProdutoDoacaoEntity deleteByFkDoadorAndProdutoDoacao(int fkDoador, int idProdutoDoacao);
	ProdutoDoacaoEntity deleteByMatch(int fkDoador,int fkDonatario ,int idProdutoDoacao);



}
