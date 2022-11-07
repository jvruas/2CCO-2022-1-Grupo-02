package com.conture.apiview.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Immutable
@Subselect("select uuid() as id, vw.* from vw_todas_datas vw")
public class VwDatas {
    @JsonIgnore
	@Id
	private String id;

    @Column(name = "datas")
    private String datas;

    public VwDatas() {
    }

    public VwDatas(String id, String datas) {
        this.id = id;
        this.datas = datas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    
}
