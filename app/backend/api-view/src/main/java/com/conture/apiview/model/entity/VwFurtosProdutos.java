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
@Subselect("select uuid() as id, vw.* from vw_furtos_por_qt_produtos vw")
@Table(name = "vw_furtos_por_qt_produtos")
public class VwFurtosProdutos implements Serializable{
    @JsonIgnore
	@Id
	private String id;

    @Column(name = "qt_produtos")
    private Integer qtProdutos;

    @Column(name = "data")
    private String data;

    public VwFurtosProdutos(){

    }

    public VwFurtosProdutos(String id, Integer qtProdutos, String data) {
        this.id = id;
        this.qtProdutos = qtProdutos;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQtProdutos() {
        return qtProdutos;
    }

    public void setQtProdutos(Integer qtProdutos) {
        this.qtProdutos = qtProdutos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
}
