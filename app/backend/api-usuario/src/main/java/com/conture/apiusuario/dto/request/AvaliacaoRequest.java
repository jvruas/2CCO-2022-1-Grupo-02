package com.conture.apiusuario.dto.request;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AvaliacaoRequest {
	@NotNull
	@Positive
	private Long fkDonatario;

	@NotNull
	@Positive
	private Long fkDoador;

	@NotNull
	@Positive
	private Long valor;

	@Size(max = 300, message = "O comentario deve ter no máximo 300 letras")
	private String comentario;


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
