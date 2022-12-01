package com.conture.apiview.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Immutable
@Subselect("SELECT uuid() AS id, vw.* FROM vw_qtd_doacao_estado vw")
public class VwQtdDoacaoEstado {
    @JsonIgnore
    @Id
    private String id;
    
    @Column(name = "qt_produtos")
    private Integer qt_produtos;

    @Column(name = "uf")
    private String uf;

	@Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public VwQtdDoacaoEstado() {
    }

    public VwQtdDoacaoEstado(String id, Integer qt_produtos, String uf, Date data) {
        this.id = id;
        this.qt_produtos = qt_produtos;
        this.uf = uf;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQt_produtos() {
        return qt_produtos;
    }

    public void setQt_produtos(Integer qt_produtos) {
        this.qt_produtos = qt_produtos;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    
}

