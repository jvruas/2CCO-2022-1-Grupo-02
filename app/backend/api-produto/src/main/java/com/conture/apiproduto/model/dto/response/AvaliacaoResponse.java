package com.conture.apiproduto.model.dto.response;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class AvaliacaoResponse {
	@NotNull
	@Positive
	private Integer fkDonatario;

	@Size(max = 45)
	private String nome;

	@Size(max = 2)
	@Pattern(regexp = "^[a-z|A-Z]+$")
	private String uf;

	@NotNull
	@Max(5)
	@Min(1)
	private Integer valor;

	@Size(max = 300)
	private String comentario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private byte[] perfilImage;


	public AvaliacaoResponse(
			Integer valor,
			String comentario,
			Date data,
			Integer fkDonatario
	) {
		this.valor = valor;
		this.comentario = comentario;
		this.data = data;
		this.fkDonatario = fkDonatario;
	}


	public Integer getValor() {
		return valor;
	}

	public String getComentario() {
		return comentario;
	}

	public Date getData() {
		return data;
	}

	public Integer getFkDonatario() {
		return fkDonatario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public byte[] getPerfilImage() {
		return perfilImage;
	}

	public void setPerfilImage(byte[] perfilImage) {
		this.perfilImage = perfilImage;
	}
}
