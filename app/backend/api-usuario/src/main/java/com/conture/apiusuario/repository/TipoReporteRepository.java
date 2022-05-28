package com.conture.apiusuario.repository;

import com.conture.apiusuario.entity.TipoReporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoReporteRepository extends JpaRepository<TipoReporte,Integer> {

	@Override
	List<TipoReporte> findAll();

	TipoReporte findByIdTipoReporte(Integer tipoReporte);
}
