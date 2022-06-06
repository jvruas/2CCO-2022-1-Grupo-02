package com.conture.apiproduto.model.dto.response;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class AvaliacaoResponse {
	@NotNull
	@Max(5)
	@Min(1)
	private Integer valor;

	@Size(max = 300)
	private String comentario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@NotNull
	@Positive
	private Integer fkDonatario;


	public AvaliacaoResponse(
			Integer valor,
			String comentario,
			Date data,
			Integer fkDonatario
	) {
		this.valor = valor;
		this.comentario = comentario;
		this.data = data;
		this.fkDonatario = fkDonatario;
	}


	public Integer getValor() {
		return valor;
	}

	public String getComentario() {
		return comentario;
	}

	public Date getData() {
		return data;
	}

	public Integer getFkDonatario() {
		return fkDonatario;
	}
}
