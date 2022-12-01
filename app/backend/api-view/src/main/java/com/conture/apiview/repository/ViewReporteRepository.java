package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwReporteTipoReporte;

@Repository
@Transactional
public interface ViewReporteRepository extends PagingAndSortingRepository<VwReporteTipoReporte, Integer>{
    @Query("select vw from VwReporteTipoReporte vw WHERE data like concat(?1,'%')")
    List<VwReporteTipoReporte> findTeste(String data);

}
