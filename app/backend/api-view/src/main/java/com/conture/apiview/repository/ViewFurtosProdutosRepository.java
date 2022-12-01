package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwFurtosProdutos;

@Repository
@Transactional
public interface ViewFurtosProdutosRepository extends PagingAndSortingRepository<VwFurtosProdutos, Integer> {
    @Query("select vw from VwFurtosProdutos vw WHERE data like concat(?1,'%')")
    List<VwFurtosProdutos> findTeste(String data);
}
