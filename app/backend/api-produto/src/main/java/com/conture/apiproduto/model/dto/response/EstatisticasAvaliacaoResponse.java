package com.conture.apiproduto.model.dto.response;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class EstatisticasAvaliacaoResponse {
	@NotNull
	@PositiveOrZero
	private double mediaAvaliacoes;

	@NotNull
	@PositiveOrZero
	private long quantidadeAvaliacoes;

	public EstatisticasAvaliacaoResponse(
			double mediaAvaliacoes,
			long quantidadeAvaliacoes
	) {
		this.mediaAvaliacoes = mediaAvaliacoes;
		this.quantidadeAvaliacoes = quantidadeAvaliacoes;
	}

	public double getMediaAvaliacoes() {
		return this.mediaAvaliacoes;
	}

	public long getQuantidadeAvaliacoes() {
		return this.quantidadeAvaliacoes;
	}
}
