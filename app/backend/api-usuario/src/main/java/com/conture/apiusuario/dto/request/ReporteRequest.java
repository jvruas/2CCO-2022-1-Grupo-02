package com.conture.apiusuario.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ReporteRequest {
	@NotNull
	@Positive
	private Integer fkReportador;

	@NotNull
	@Positive
	private Integer fkReportado;

	@NotNull
	@Positive
	private Integer fkTipoReporte;


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
