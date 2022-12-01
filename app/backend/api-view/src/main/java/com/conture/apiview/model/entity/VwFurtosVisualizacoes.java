package com.conture.apiview.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Immutable
@Subselect("select uuid() as id, vw.* from vw_furtos_por_qt_visualizacao vw")
@Table(name = "vw_furtos_por_qt_visualizacao")
public class VwFurtosVisualizacoes implements Serializable{
    @JsonIgnore
	@Id
	private String id;

    @Column(name = "qt_visualizacao")
    private Integer qtVisualizacao;

    @Column(name = "data")
    private String data;

    public VwFurtosVisualizacoes(){

    }

    public VwFurtosVisualizacoes(String id, Integer qtVisualizacao, String data) {
        this.id = id;
        this.qtVisualizacao = qtVisualizacao;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQtVisualizacao() {
        return qtVisualizacao;
    }

    public void setQtVisualizacao(Integer qtVisualizacao) {
        this.qtVisualizacao = qtVisualizacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
