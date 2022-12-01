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

	@Column(name = "Assedio")
    private Integer assedio;

	@Column(name = "Discurso_de_odio")
    private Integer odio;

	@Column(name = "Informacoes_do_perfil")
    private Integer perfil;

	@Column(name = "Nudez_ou_atividade_sexual")
    private Integer nudez;

	@Column(name = "Produto_falso")
    private Integer produtoFalso;

	@Column(name = "Spam")
    private Integer spam;

	@Column(name = "data")
    private String data;

	public VwReporteTipoReporte(){

	}

    public VwReporteTipoReporte(String id, Integer assedio, Integer odio, Integer perfil, Integer nudez,
            Integer produtoFalso, Integer spam, String data) {
        this.id = id;
        this.assedio = assedio;
        this.odio = odio;
        this.perfil = perfil;
        this.nudez = nudez;
        this.produtoFalso = produtoFalso;
        this.spam = spam;
        this.data = data;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAssedio() {
		return assedio;
	}

	public void setAssedio(Integer assedio) {
		this.assedio = assedio;
	}

	public Integer getOdio() {
		return odio;
	}

	public void setOdio(Integer odio) {
		this.odio = odio;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public Integer getNudez() {
		return nudez;
	}

	public void setNudez(Integer nudez) {
		this.nudez = nudez;
	}

	public Integer getProdutoFalso() {
		return produtoFalso;
	}

	public void setProdutoFalso(Integer produtoFalso) {
		this.produtoFalso = produtoFalso;
	}

	public Integer getSpam() {
		return spam;
	}

	public void setSpam(Integer spam) {
		this.spam = spam;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	
}
