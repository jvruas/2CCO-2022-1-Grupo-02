package com.conture.apimensagem.dto.requests;

import javax.validation.constraints.*;

public class RespostaRequest {
	@Positive
	@NotNull
	private Long fkPergunta;

	@NotBlank
	@Size(min = 3, max = 100)
	private String mensagem;

	@Positive
	@NotNull
	private Long fkDoador;

	@Positive
	@NotNull
	private Long fkProdutoDoacao;

	public RespostaRequest(
			Long fkPergunta,
			String mensagem,
			Long fkDoador,
			Long fkProdutoDoacao
	) {
		this.fkPergunta = fkPergunta;
		this.mensagem = mensagem;
		this.fkDoador = fkDoador;
		this.fkProdutoDoacao = fkProdutoDoacao;
	}

	public Long getFkPergunta() { return fkPergunta; }

	public String getMensagem() { return mensagem; }

	public Long getFkDoador() { return fkDoador; }

	public Long getFkProdutoDoacao() { return fkProdutoDoacao; }
}
