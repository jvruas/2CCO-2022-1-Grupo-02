package com.conture.apiview.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Immutable
@Subselect("select uuid() as id, vw.* from vw_reporte_tipo_reporte vw")
@Table(name = "vw_reporte_tipo_reporte")
public class VwReporteTipoReporte implements Serializable{

	@JsonIgnore
	@Id
	private String id;

	@Column(name = "total_reportes")
    private Integer totalReportes;

	@Column(name = "reporte")
    private String reporte;

	@Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;



	public VwReporteTipoReporte(){

	}



	public VwReporteTipoReporte(String id, Integer totalReportes, String reporte, Date data) {
		this.id = id;
		this.totalReportes = totalReportes;
		this.reporte = reporte;
		this.data = data;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Integer getTotalReportes() {
		return totalReportes;
	}



	public void setTotalReportes(Integer totalReportes) {
		this.totalReportes = totalReportes;
	}



	public String getReporte() {
		return reporte;
	}



	public void setReporte(String reporte) {
		this.reporte = reporte;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}

	
}
