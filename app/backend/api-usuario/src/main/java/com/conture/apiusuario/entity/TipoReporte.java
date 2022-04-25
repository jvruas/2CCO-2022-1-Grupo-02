package com.conture.apiusuario.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class TipoReporte {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoReporte;

	@NotBlank
	@Size(max = 45, message = "O tipo de reporte deve ter no máximo 12 letras")
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
