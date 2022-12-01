package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwBaseFurtos;

@Repository
@Transactional
public interface ViewBaseFurtosRepository extends PagingAndSortingRepository<VwBaseFurtos, Integer> {
    @Query("select vw from VwBaseFurtos vw WHERE data like concat(?1,'%')")
    List<VwBaseFurtos> findTeste(String data);
}
