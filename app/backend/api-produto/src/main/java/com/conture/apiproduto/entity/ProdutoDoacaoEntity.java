package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class ProdutoDoacaoEntity {

	@NotNull
	@NotBlank
	private int fkDoador;

	@Id
	@NotNull
	@NotBlank
	private int idDoacao;


	@NotBlank
	@NotNull
	@Min(2)
	private String nome;

	@NotBlank
	@NotNull
	private String marca;

	@NotBlank
	@NotNull
	private String modelo;

	@NotBlank
	@NotNull
	@Min(10)
	private String descricao;


	private boolean defeito;

	private boolean status;

	private boolean entrega;

	@NotBlank
	@NotNull
	@Positive
	@Min(1)
	private Integer quantidadeVizualicao;



	@PastOrPresent
	private LocalDateTime dataCriacao;


	@PastOrPresent
	private LocalDateTime dataConclusao;

	@NotBlank
	@NotNull
	private int fkCategoriaProduto;
}
