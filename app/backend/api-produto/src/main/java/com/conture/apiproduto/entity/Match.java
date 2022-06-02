package com.conture.apiproduto.entity;

import com.conture.apiproduto.rest.usuario.UsuarioResposta;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Match {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;


	@ManyToOne(cascade=CascadeType.PERSIST)
	private UsuarioResposta fkDoador;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private ProdutoDoacao fkProdutoDoacao;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private UsuarioResposta fkDonatario;

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

	public UsuarioResposta getFkDoador() { return fkDoador; }

	public Double getMatchPorcentagem() { return matchPorcentagem; }

	public UsuarioResposta getFkDonatario() { return fkDonatario; }

	public Date getDataInteresse() {return this.dataInteresse;}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFkDoador(UsuarioResposta fkDoador) { this.fkDoador = fkDoador; }

	public void setFkDonatario(UsuarioResposta fkDonatario) {this.fkDonatario = fkDonatario;}

	public void setMatchPorcentagem(Double matchPorcentagem) { this.matchPorcentagem = matchPorcentagem; }

	public ProdutoDoacao getFkProdutoDoacao() {
		return fkProdutoDoacao;
	}

	public void setFkProdutoDoacao(ProdutoDoacao fkProdutoDoacao) {
		this.fkProdutoDoacao = fkProdutoDoacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
