package com.conture.apiusuario.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ReporteRequest {

	// Atributos
	@NotNull
	@Positive
	private Long fkReportador;

	//@Id
	@NotNull
	@Positive
	private Long fkReportado;

	@NotNull
	@Positive
	private Long fkTipoReporte;

	// Getters
	public Long getFkReportador() {
		return fkReportador;
	}

	public Long getFkReportado() {
		return fkReportado;
	}

	public Long getFkTipoReporte() {
		return fkTipoReporte;
	}
}
