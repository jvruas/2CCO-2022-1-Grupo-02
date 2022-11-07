package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwDoacaoHistorica;

@Repository
@Transactional
public interface ViewDoacaoReporitory extends PagingAndSortingRepository<VwDoacaoHistorica, Integer> {
    @Query("select vw from VwDoacaoHistorica vw WHERE mes like concat(?1,'%')")
    List<VwDoacaoHistorica> findTeste(String data);
}
