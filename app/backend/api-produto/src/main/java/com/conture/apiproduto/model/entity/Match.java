package com.conture.apiproduto.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMatch;

	@PositiveOrZero
	@DecimalMax("100")
	@DecimalMin("0")
	private Double matchPorcentagem;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInteresse;

	@NotNull
	private boolean status;

	@NotNull
	private boolean visualizado;

	@NotNull
	@ManyToOne
	private ProdutoDoacao produtoDoacao;

	@NotNull
	@Positive
	private Integer fkDonatario;


	public Match() {
	}

	private Match(
			Integer fkProdutoDoacao,
			Integer fkDonatario
	) {
		this.setProdutoDoacao(fkProdutoDoacao);
		this.fkDonatario = fkDonatario;
	}


	public static Match fromPattern(
			Integer fkProdutoDoacao,
			Integer fkDonatario
	) {
		return new Match(fkProdutoDoacao, fkDonatario);
	}

	public Integer getIdMatch() {
		return idMatch;
	}

	public Double getMatchPorcentagem() {
		return matchPorcentagem;
	}

	public Date getDataInteresse() {
		return dataInteresse;
	}

	public boolean isStatus() {
		return status;
	}

	public boolean isVisualizado() {
		return visualizado;
	}

	public ProdutoDoacao getProdutoDoacao() {
		return produtoDoacao;
	}

	public Integer getFkDonatario() {
		return fkDonatario;
	}

	public void setIdMatch(Integer idMatch) {
		this.idMatch = idMatch;
	}

	public void setMatchPorcentagem(Double matchPorcentagem) {
		this.matchPorcentagem = matchPorcentagem;
	}

	public void setDataInteresse(Date dataInteresse) {
		this.dataInteresse = dataInteresse;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setVisualizado(boolean visualizado) {
		this.visualizado = visualizado;
	}

	public void setProdutoDoacao(Integer fkProdutoDoacao) {
		this.produtoDoacao = ProdutoDoacao.fromPattern(fkProdutoDoacao);
	}

	public void setFkDonatario(Integer fkDonatario) {
		this.fkDonatario = fkDonatario;
	}
}
