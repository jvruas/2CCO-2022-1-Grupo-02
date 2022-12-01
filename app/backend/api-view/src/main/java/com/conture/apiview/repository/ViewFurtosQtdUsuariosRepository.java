package com.conture.apiview.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.conture.apiview.model.entity.VwFurtosQtdUsuarios;

@Repository
@Transactional
public interface ViewFurtosQtdUsuariosRepository extends PagingAndSortingRepository<VwFurtosQtdUsuarios, Integer> {
    @Query("select vw from VwFurtosQtdUsuarios vw WHERE data like concat(?1,'%')")
    List<VwFurtosQtdUsuarios> findTeste(String data);
}
