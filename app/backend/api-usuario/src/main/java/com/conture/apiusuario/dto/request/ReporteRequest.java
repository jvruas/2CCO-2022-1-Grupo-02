package com.conture.apiusuario.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ReporteRequest {

	// Atributos
	@NotNull
	@Positive
	private Integer fkReportador;

	//@Id
	@NotNull
	@Positive
	private Integer fkReportado;

	@NotNull
	@Positive
	private Integer fkTipoReporte;

	// Getters
	public Integer getFkReportador() {
		return fkReportador;
	}

	public Integer getFkReportado() {
		return fkReportado;
	}

	public Integer getFkTipoReporte() {
		return fkTipoReporte;
	}
}
