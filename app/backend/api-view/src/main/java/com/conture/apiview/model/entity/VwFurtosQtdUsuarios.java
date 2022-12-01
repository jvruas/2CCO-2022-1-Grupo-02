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
@Subselect("select uuid() as id, vw.* from vw_furtos_por_qt_usuarios vw")
@Table(name = "vw_furtos_por_qt_usuarios")
public class VwFurtosQtdUsuarios implements Serializable{
    @JsonIgnore
	@Id
	private String id;

    @Column(name = "qt_usuarios")
    private Integer qtUsuarios;

    @Column(name = "data")
    private String data;

    public VwFurtosQtdUsuarios(){

    }

    public VwFurtosQtdUsuarios(String id, Integer qtUsuarios, String data) {
        this.id = id;
        this.qtUsuarios = qtUsuarios;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQtUsuarios() {
        return qtUsuarios;
    }

    public void setQtUsuarios(Integer qtUsuarios) {
        this.qtUsuarios = qtUsuarios;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
}
