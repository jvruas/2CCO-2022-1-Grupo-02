package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwDatas;

@Repository
@Transactional
public interface ViewTodasDatasRepository  extends PagingAndSortingRepository<VwDatas, Integer>{
    @Query("select vw from VwDatas vw")
    List<VwDatas> findTeste();

}
