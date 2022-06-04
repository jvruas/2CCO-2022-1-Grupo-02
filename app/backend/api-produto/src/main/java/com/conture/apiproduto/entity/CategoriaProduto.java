package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class CategoriaProduto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoriaProduto;

	@NotNull
	@NotBlank
	@Size(max = 45)
	private String nome;


	public CategoriaProduto() {
	}

	private CategoriaProduto(Integer idCategoriaProduto) {
		this.idCategoriaProduto = idCategoriaProduto;
	}


	public static CategoriaProduto fromPattern(Integer idCategoriaProduto) {
		return new CategoriaProduto(idCategoriaProduto);
	}

	public Integer getIdCategoriaProduto() {
		return idCategoriaProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setIdCategoriaProduto(Integer idCategoriaProduto) {
		this.idCategoriaProduto = idCategoriaProduto;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
