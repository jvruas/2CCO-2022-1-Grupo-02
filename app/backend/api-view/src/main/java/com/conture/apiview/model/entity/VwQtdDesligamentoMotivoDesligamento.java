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
@Subselect("select uuid() as id, vw.* from vw_qtd_desligamento_motivo_desligamento vw")
@Table(name = "vw_qtd_desligamento_motivo_desligamento")
public class VwQtdDesligamentoMotivoDesligamento implements Serializable{

	@JsonIgnore
	@Id
	private String id;

	@Column(name = "quantidade_desligamentos")
    private Integer quantidadeDesligamentos;

	@Column(name = "motivo")
    private String motivo;


	@Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;


	public VwQtdDesligamentoMotivoDesligamento(){

	}


	public VwQtdDesligamentoMotivoDesligamento(String id, Integer quantidadeDesligamentos, String motivo, Date data) {
		this.id = id;
		this.quantidadeDesligamentos = quantidadeDesligamentos;
		this.motivo = motivo;
		this.data = data;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getQuantidadeDesligamentos() {
		return quantidadeDesligamentos;
	}


	public void setQuantidadeDesligamentos(Integer quantidadeDesligamentos) {
		this.quantidadeDesligamentos = quantidadeDesligamentos;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

	
}
