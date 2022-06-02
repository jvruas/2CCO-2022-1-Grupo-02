package com.conture.apimensagemgrupo.dto.response;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

public class MensagemGrupoResponse {
	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@NotBlank
	@Size(min = 3, max = 100)
	private String mensagem;

	@Positive
	@NotNull
	private  Integer fkUsuario;

	public MensagemGrupoResponse(
			Date data,
			String mensagem,
			Integer fkUsuario
	) {
		this.data = data;
		this.mensagem = mensagem;
		this.fkUsuario = fkUsuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Date getData() {
		return data;
	}

	public Integer getFkUsuario() {
		return fkUsuario;
	}

}
