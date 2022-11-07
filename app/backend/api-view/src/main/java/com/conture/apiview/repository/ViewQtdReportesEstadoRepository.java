package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwQtdReportesEstado;

@Repository
@Transactional
public interface ViewQtdReportesEstadoRepository extends PagingAndSortingRepository<VwQtdReportesEstado, Integer>{
    @Query("SELECT vw FROM VwQtdReportesEstado vw WHERE data like concat(?1,'%')")
    List<VwQtdReportesEstado> findTeste(String data);
}
