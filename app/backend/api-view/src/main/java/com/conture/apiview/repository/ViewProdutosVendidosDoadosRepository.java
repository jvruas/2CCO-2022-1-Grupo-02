package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwProdutosVendidosDoados;


@Repository
@Transactional
public interface ViewProdutosVendidosDoadosRepository extends PagingAndSortingRepository<VwProdutosVendidosDoados, Integer>{
    @Query("select vw from VwProdutosVendidosDoados vw WHERE data_de_doacao like concat(?1,'%')")
    List<VwProdutosVendidosDoados> findTeste(String data);
}
