package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Match {


	@NotNull
	@NotBlank
	private Long fkDoador;

	@NotNull
	@NotBlank
	private Long fkProduto;

	@NotNull
	@NotBlank
	private Long fkDonatario;

	@NotBlank
	@NotNull
	@Positive
	@Max(100)
	private Double matchPorcentagem;

	@PastOrPresent
	private LocalDateTime dataInteresse;

	// Getters
	public Long getFkDoador() { return fkDoador; }

	public Long getFkProduto() { return fkProduto; }

	public Long getFkDonatario() { return fkDonatario; }

	public Double getMatchPorcentagem() { return matchPorcentagem; }

	public LocalDateTime getDataInteresse() { return dataInteresse; }

	//Setters
	public void setFkDoador(Long fkDoador) { this.fkDoador = fkDoador; }

	public void setFkProduto(Long fkProduto) { this.fkProduto = fkProduto; }

	public void setFkDonatario(Long fkDonatario) { this.fkDonatario = fkDonatario; }

	public void setMatchPorcentagem(Double matchPorcentagem) { this.matchPorcentagem = matchPorcentagem; }

	public void setDataInteresse(LocalDateTime dataInteresse) { this.dataInteresse = dataInteresse; }
}
