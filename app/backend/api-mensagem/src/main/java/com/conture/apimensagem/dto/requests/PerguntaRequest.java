package com.conture.apimensagem.dto.requests;

import javax.validation.constraints.*;

public class PerguntaRequest {

	@Positive
	@NotNull
	private Long fkDonatario;

	@NotBlank
	@Size(min = 3, max = 100)
	private String mensagem;

	@Positive
	@NotNull
	private Long fkDoador;

	@Positive
	@NotNull
	private Long fkProdutoDoacao;

	public PerguntaRequest(
			Long fkDonatario,
		   	String mensagem,
			Long fkDoador,
			Long fkProdutoDoacao
	) {
		this.fkDonatario = fkDonatario;
		this.mensagem = mensagem;
		this.fkDoador = fkDoador;
		this.fkProdutoDoacao = fkProdutoDoacao;
	}

	public Long getFkDonatario() { return fkDonatario; }

	public String getMensagem() { return mensagem; }

	public Long getFkDoador() { return fkDoador; }

	public Long getFkProdutoDoacao() { return fkProdutoDoacao; }
}
