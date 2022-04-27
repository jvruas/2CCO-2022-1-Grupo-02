package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class CategoriaProduto {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long idCategoriaProduto;

	@NotNull
	@NotBlank
	@Size(min = 2 , max = 45)
	private String nome;

	public Long getIdCategoriaProduto() { return idCategoriaProduto; }

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = nome; }
}
