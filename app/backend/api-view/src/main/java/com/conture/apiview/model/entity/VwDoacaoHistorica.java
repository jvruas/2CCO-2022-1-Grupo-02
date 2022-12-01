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

    @Column(name = "Headset")
    private Integer headset;

    @Column(name = "Mesa_Digitalizadora")
    private Integer mesaDigitalizadora;

    @Column(name = "Notebook")
    private Integer notebook;

    @Column(name = "Smartphone")
    private Integer smartphone;

    @Column(name = "Tablet")
    private Integer tablet;

    @Column(name = "DATA")
    private String ano;

    public VwDoacaoHistorica(){

    }

    public VwDoacaoHistorica(String id, Integer headset, Integer mesaDigitalizadora, Integer notebook,
            Integer smartphone, Integer tablet, String ano) {
        this.id = id;
        this.headset = headset;
        this.mesaDigitalizadora = mesaDigitalizadora;
        this.notebook = notebook;
        this.smartphone = smartphone;
        this.tablet = tablet;
        this.ano = ano;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHeadset() {
        return headset;
    }

    public void setHeadset(Integer headset) {
        this.headset = headset;
    }

    public Integer getMesaDigitalizadora() {
        return mesaDigitalizadora;
    }

    public void setMesaDigitalizadora(Integer mesaDigitalizadora) {
        this.mesaDigitalizadora = mesaDigitalizadora;
    }

    public Integer getNotebook() {
        return notebook;
    }

    public void setNotebook(Integer notebook) {
        this.notebook = notebook;
    }

    public Integer getSmartphone() {
        return smartphone;
    }

    public void setSmartphone(Integer smartphone) {
        this.smartphone = smartphone;
    }

    public Integer getTablet() {
        return tablet;
    }

    public void setTablet(Integer tablet) {
        this.tablet = tablet;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    

}
