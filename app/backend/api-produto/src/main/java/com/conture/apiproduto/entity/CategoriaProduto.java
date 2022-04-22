package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CategoriaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoriaProduto;

	@NotNull
	@NotBlank
	@Min(2)
	private String nome;

	// Getters
	public Long getIdCategoriaProduto() { return idCategoriaProduto; }

	public String getNome() { return nome; }

	// Setters
	public void setIdCategoriaProduto(Long idCategoriaProduto) { this.idCategoriaProduto = idCategoriaProduto; }

	public void setNome(String nome) { this.nome = nome; }
}
