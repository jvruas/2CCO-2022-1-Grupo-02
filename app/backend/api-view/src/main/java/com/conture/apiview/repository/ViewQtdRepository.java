package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwQtdDesligamentoMotivoDesligamento;

@Repository
@Transactional
public interface ViewQtdRepository extends PagingAndSortingRepository<VwQtdDesligamentoMotivoDesligamento, Integer>{  
    @Query("select vw from VwQtdDesligamentoMotivoDesligamento vw WHERE data like concat(?1,'%')")
    List<VwQtdDesligamentoMotivoDesligamento> findTeste(String data);
}
