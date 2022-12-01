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
@Subselect("SELECT uuid() AS id, vw.* FROM vw_qtd_reportes_estado vw")
@Table(name = "vw_qtd_reportes_estado")
public class VwQtdReportesEstado {
    @JsonIgnore
    @Id
    private String id;

    @Column(name = "qt_reportes")
    private Integer qt_reportes;

    @Column(name = "uf")
    private String uf;

	@Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public VwQtdReportesEstado() {
    }

    public VwQtdReportesEstado(String id, Integer qt_reportes, String uf, Date data) {
        this.id = id;
        this.qt_reportes = qt_reportes;
        this.uf = uf;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQt_reportes() {
        return qt_reportes;
    }

    public void setQt_reportes(Integer qt_reportes) {
        this.qt_reportes = qt_reportes;
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
