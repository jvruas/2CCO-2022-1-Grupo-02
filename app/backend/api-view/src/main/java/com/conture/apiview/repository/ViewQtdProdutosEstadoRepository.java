package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwQtdDoacaoEstado;

@Repository
@Transactional
public interface ViewQtdProdutosEstadoRepository extends PagingAndSortingRepository<VwQtdDoacaoEstado, Integer>{
    @Query("SELECT vw FROM VwQtdDoacaoEstado vw WHERE data like concat(?1,'%')")
    List<VwQtdDoacaoEstado> findTeste(String data);
}
