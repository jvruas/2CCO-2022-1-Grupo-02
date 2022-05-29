package com.conture.apiusuario.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class TipoReporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoReporte;

	@NotBlank
	@Size(max = 45, message = "O tipo de reporte deve ter no máximo 45 letras")
    private String nome;

	public TipoReporte() {}

	private TipoReporte(Integer idTipoReporte) {
		this.idTipoReporte = idTipoReporte;
	}

	public static TipoReporte fromPattern(Integer idTipoReporte) {
		return new TipoReporte(idTipoReporte);
	}

	public Integer getIdTipoReporte() {
        return idTipoReporte;
    }

    public void setIdTipoReporte(Integer idTipoReporte) {
        this.idTipoReporte = idTipoReporte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
