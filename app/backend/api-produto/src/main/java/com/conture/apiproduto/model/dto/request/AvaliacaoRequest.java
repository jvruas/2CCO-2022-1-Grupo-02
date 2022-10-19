package com.conture.apiproduto.model.dto.request;

import javax.validation.constraints.*;

public class AvaliacaoRequest {
	@NotNull
	@Max(5)
	@Min(0)
	private Integer valor;

	@Size(max = 300)
	private String comentario;


	public Integer getValor() {
		return valor;
	}

	public String getComentario() {
		return comentario;
	}
}
