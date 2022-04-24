package com.conture.apiusuario.dto.request;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AvaliacaoRequest {

	// Atributos
	@NotNull
	@Positive
	private Long fkDonatario;

	@NotNull
	@Positive
	private Long fkDoador;

	@NotNull
	@Positive
	private Long valor;

	private String comentario;

	// Getters
	public Long getFkDoador() {
		return fkDoador;
	}

	public Long getFkDonatario() {
		return fkDonatario;
	}

	public Long getValor() {
		return valor;
	}

	public String getComentario() {
		return comentario;
	}
}
