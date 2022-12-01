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
@Subselect("select uuid() as id, vw.* from vw_base_furtos vw")
@Table(name = "vw_base_furtos")
public class VwBaseFurtos implements Serializable{
    @JsonIgnore
	@Id
	private String id;

    @Column(name = "qt_furtos")
    private Integer qtFurtos;

    @Column(name = "data")
    private String data;

    public VwBaseFurtos(){

    }

    public VwBaseFurtos(String id, Integer qtFurtos, String data) {
        this.id = id;
        this.qtFurtos = qtFurtos;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQtFurtos() {
        return qtFurtos;
    }

    public void setQtProdutos(Integer qtFurtos) {
        this.qtFurtos = qtFurtos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
