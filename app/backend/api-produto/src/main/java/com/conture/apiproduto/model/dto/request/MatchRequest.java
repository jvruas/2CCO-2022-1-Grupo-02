package com.conture.apiproduto.model.dto.request;

import com.conture.apiproduto.model.entity.ProdutoDoacao;

import javax.persistence.*;
import javax.validation.constraints.*;

public class MatchRequest {
	@NotNull
	private boolean visualizado;

	@NotNull
	@ManyToOne
	private ProdutoDoacao produtoDoacao;

	@NotNull
	@Positive
	private Integer fkDonatario;
}
