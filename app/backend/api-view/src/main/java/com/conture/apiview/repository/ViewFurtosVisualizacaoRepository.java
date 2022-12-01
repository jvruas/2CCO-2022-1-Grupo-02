package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwFurtosVisualizacoes;

@Repository
@Transactional
public interface ViewFurtosVisualizacaoRepository extends PagingAndSortingRepository<VwFurtosVisualizacoes, Integer> {
    @Query("select vw from VwFurtosVisualizacoes vw WHERE data like concat(?1,'%')")
    List<VwFurtosVisualizacoes> findTeste(String data);
}
