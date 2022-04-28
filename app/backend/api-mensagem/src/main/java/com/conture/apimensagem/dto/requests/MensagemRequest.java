package com.conture.apimensagem.dto.requests;

import javax.validation.constraints.*;

public class MensagemRequest {
	@Positive
	@NotNull
	private Long fkChatDireto;

	@NotBlank
	@Size(min = 3, max = 100)
	private String mensagem;

	@Positive
	@NotNull
	private Long fkUsuarioDestinatario;

	@Positive
	@NotNull
	private Long fkUsuarioRemetente;

	public MensagemRequest(
			Long fkChatDireto,
		   	String mensagem,
			Long fkUsuarioDestinatario,
		   	Long fkUsuarioRemetente
	) {
		this.fkChatDireto = fkChatDireto;
		this.mensagem = mensagem;
		this.fkUsuarioRemetente = fkUsuarioRemetente;
		this.fkUsuarioDestinatario = fkUsuarioDestinatario;
	}

	public Long getFkChatDireto() { return fkChatDireto; }

	public String getMensagem() { return mensagem; }

	public Long getFkUsuarioDestinatario() { return fkUsuarioDestinatario; }

	public Long getFkUsuarioRemetente() { return fkUsuarioRemetente; }
}
