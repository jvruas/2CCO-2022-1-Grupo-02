package com.conture.apiusuario.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_tipo_reporte")
public class TipoReporte {

    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoReporte;

    private String nome;

    // Getters e Setters
    public Long getIdTipoReporte() {
        return idTipoReporte;
    }

    public void setIdTipoReporte(Long idTipoReporte) {
        this.idTipoReporte = idTipoReporte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
