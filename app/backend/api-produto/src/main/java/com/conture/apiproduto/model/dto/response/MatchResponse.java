package com.conture.apiproduto.model.dto.response;

import com.conture.apiproduto.model.entity.ProdutoDoacao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class MatchResponse {
	@PositiveOrZero
	@DecimalMax("100")
	@DecimalMin("0")
	private Double matchPorcentagem;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInteresse;

	@NotNull
	@Positive
	private Integer fkDonatario;


	public MatchResponse(
			Double matchPorcentagem,
			Date dataInteresse,
			Integer fkDonatario
	) {
		this.matchPorcentagem = matchPorcentagem;
		this.dataInteresse = dataInteresse;
		this.fkDonatario = fkDonatario;
	}


	public Double getMatchPorcentagem() {
		return matchPorcentagem;
	}

	public Date getDataInteresse() {
		return dataInteresse;
	}

	public Integer getFkDonatario() {
		return fkDonatario;
	}
}
