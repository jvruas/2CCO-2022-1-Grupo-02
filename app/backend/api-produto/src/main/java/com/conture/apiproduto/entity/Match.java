package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Match {


	@NotNull
	@NotBlank
	private Long fkDoador;

	@NotNull
	@NotBlank
	private Long fkProdutoDoacao;

	@NotNull
	@NotBlank
	@Id
	private Long fkDonatario;

	@NotBlank
	@NotNull
	@Positive
	@Max(100)
	private Double matchPorcentagem;

	@PastOrPresent
	private LocalDateTime dataInteresse;

	@NotBlank
	@NotNull
	@Size(min = 1,max = 1 )
	private String status;

	// Getters
	public Long getFkDoador() { return fkDoador; }



	public Long getFkDonatario() { return fkDonatario; }

	public Double getMatchPorcentagem() { return matchPorcentagem; }

	public LocalDateTime getDataInteresse() { return dataInteresse; }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//Setters
	public void setFkDoador(Long fkDoador) { this.fkDoador = fkDoador; }



	public void setFkDonatario(Long fkDonatario) { this.fkDonatario = fkDonatario; }

	public void setMatchPorcentagem(Double matchPorcentagem) { this.matchPorcentagem = matchPorcentagem; }

	public void setDataInteresse(LocalDateTime dataInteresse) { this.dataInteresse = dataInteresse; }

	public Long getFkProdutoDoacao() {
		return fkProdutoDoacao;
	}

	public void setFkProdutoDoacao(Long fkProdutoDoacao) {
		this.fkProdutoDoacao = fkProdutoDoacao;
	}
}
