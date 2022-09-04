package com.conture.apiusuario.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
public class Reporte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReporte;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date data;

	@NotNull
	@ManyToOne
	@JoinColumn(name="fk_reportador")
	private Usuario reportador;

	@NotNull
	@ManyToOne
	@JoinColumn(name="fk_reportado")
	private Usuario reportado;

	@NotNull
	@ManyToOne
	@JoinColumn(name="fk_tipo_reporte")
    private TipoReporte tipoReporte;


	public Reporte() {}

	private Reporte(
			Integer fkReportador,
			Integer fkReportado,
			Integer fkTipoReporte
	) {
		this.setReportador(fkReportador);
		this.setReportado(fkReportado);
		this.setTipoReporte(fkTipoReporte);
	}

	public static Reporte fromPattern(
			Integer fkReportador,
			Integer fkReportado,
			Integer fkTipoReporte
	) {
		return new Reporte(fkReportador, fkReportado, fkTipoReporte);
	}

	public Integer getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Integer idReporte) {
		this.idReporte = idReporte;
	}

	public Date getData() {
		return data;
	}

	public Usuario getReportador() {
		return reportador;
	}

	public void setReportador(Integer fkReportador) {
		this.reportador = Usuario.fromPattern(fkReportador);
	}

	public Usuario getReportado() {
		return reportado;
	}

	public void setReportado(Integer fkReportado) {
		this.reportado = Usuario.fromPattern(fkReportado);
	}

	public TipoReporte getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(Integer fkTipoReporte) {
		this.tipoReporte = TipoReporte.fromPattern(fkTipoReporte);
	}
}
