package com.conture.apiview.model.entity;

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
@Subselect("select uuid() as id, vw.* from vw_doacao_historica vw")
@Table(name = "vw_doacao_historica")
public class VwDoacaoHistorica {
    @JsonIgnore
	@Id
	private String id;

    @Column(name = "mes")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mes;

    @Column(name = "qtd_produtos")
    private Integer qtd_produtos;

    public VwDoacaoHistorica(){

    }

    public VwDoacaoHistorica(String id, Date mes, Integer qtd_produtos) {
        this.id = id;
        this.mes = mes;
        this.qtd_produtos = qtd_produtos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getMes() {
        return mes;
    }

    public void setMes(Date mes) {
        this.mes = mes;
    }

    public Integer getQtd_produtos() {
        return qtd_produtos;
    }

    public void setQtd_produtos(Integer qtd_produtos) {
        this.qtd_produtos = qtd_produtos;
    }

}
