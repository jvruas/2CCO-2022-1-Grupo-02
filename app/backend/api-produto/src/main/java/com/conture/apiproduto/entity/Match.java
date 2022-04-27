package com.conture.apiproduto.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Match {

	@NotNull
	@Positive
	private Long fkDoador;

	@NotNull
	@Positive
	private Long fkProdutoDoacao;


	@Id
	@Positive
	@NotNull
	private Long fkDonatario;

	@NotNull
	@Positive
	@Max(100)
	@Min(0)
	private Double matchPorcentagem;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInteresse;

	@NotBlank
	@NotNull
	@Size(min = 1,max = 1 )
	private String status;

	public Long getFkDoador() { return fkDoador; }

	public Double getMatchPorcentagem() { return matchPorcentagem; }

	public Long getFkDonatario() { return fkDonatario; }

	public Date getDataInteresse() {return this.dataInteresse;}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFkDoador(Long fkDoador) { this.fkDoador = fkDoador; }

	public void setFkDonatario(Long fkDonatario) {this.fkDonatario = fkDonatario;}

	public void setMatchPorcentagem(Double matchPorcentagem) { this.matchPorcentagem = matchPorcentagem; }

	public Long getFkProdutoDoacao() {
		return fkProdutoDoacao;
	}

	public void setFkProdutoDoacao(Long fkProdutoDoacao) {
		this.fkProdutoDoacao = fkProdutoDoacao;
	}
}
