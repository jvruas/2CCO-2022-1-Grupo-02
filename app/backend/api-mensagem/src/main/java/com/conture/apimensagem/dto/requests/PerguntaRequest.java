package com.conture.apimensagem.dto.requests;

import javax.validation.constraints.*;

public class PerguntaRequest {

	@Positive
	@NotNull
	private Integer fkDonatario;

	@NotBlank
	@Size(min = 3, max = 100)
	private String mensagem;

	@Positive
	@NotNull
	private Integer fkDoador;

	@Positive
	@NotNull
	private Integer fkProdutoDoacao;

	public PerguntaRequest(
			Integer fkDonatario,
		   	String mensagem,
			Integer fkDoador,
			Integer fkProdutoDoacao
	) {
		this.fkDonatario = fkDonatario;
		this.mensagem = mensagem;
		this.fkDoador = fkDoador;
		this.fkProdutoDoacao = fkProdutoDoacao;
	}

	public Integer getFkDonatario() { return fkDonatario; }

	public String getMensagem() { return mensagem; }

	public Integer getFkDoador() { return fkDoador; }

	public Integer getFkProdutoDoacao() { return fkProdutoDoacao; }
}
