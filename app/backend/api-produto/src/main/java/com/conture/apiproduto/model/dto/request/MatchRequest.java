package com.conture.apiproduto.model.dto.request;

import com.conture.apiproduto.model.entity.ProdutoDoacao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

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
