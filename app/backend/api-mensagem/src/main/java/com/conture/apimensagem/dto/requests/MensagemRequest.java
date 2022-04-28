package com.conture.apimensagem.dto.requests;

import javax.validation.constraints.*;

public class MensagemRequest {
	@Positive
	@NotNull
	private Long fkUsuarioDestinatario;

	@Positive
	@NotNull
	private Long fkUsuarioRemetente;

	@NotBlank
	@Size(min = 3, max = 100)
	private String mensagem;

	public MensagemRequest(Long fkUsuarioDestinatario, Long fkUsuarioRemetente, String mensagem) {
		this.fkUsuarioDestinatario = fkUsuarioDestinatario;
		this.fkUsuarioRemetente = fkUsuarioRemetente;
		this.mensagem = mensagem;
	}

	public Long getFkUsuarioDestinatario() { return fkUsuarioDestinatario; }

	public Long getFkUsuarioRemetente() { return fkUsuarioRemetente; }

	public String getMensagem() { return mensagem; }
}
