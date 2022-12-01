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

	@Column(name = "Problemas_em_utilizar_o_site")
    private Integer problemasSite;

	@Column(name = "Nao_consigo_achar_os_eletronicos_que_preciso")
    private String naoAchoEletronico;

	@Column(name = "Outros")
    private String outros;


	@Column(name = "data")
    private String data;


	public VwQtdDesligamentoMotivoDesligamento(){

	}


    public VwQtdDesligamentoMotivoDesligamento(String id, Integer problemasSite, String naoAchoEletronico,
            String outros, String data) {
        this.id = id;
        this.problemasSite = problemasSite;
        this.naoAchoEletronico = naoAchoEletronico;
        this.outros = outros;
        this.data = data;
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getProblemasSite() {
		return problemasSite;
	}


	public void setProblemasSite(Integer problemasSite) {
		this.problemasSite = problemasSite;
	}


	public String getNaoAchoEletronico() {
		return naoAchoEletronico;
	}


	public void setNaoAchoEletronico(String naoAchoEletronico) {
		this.naoAchoEletronico = naoAchoEletronico;
	}


	public String getOutros() {
		return outros;
	}


	public void setOutros(String outros) {
		this.outros = outros;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	
	
}
