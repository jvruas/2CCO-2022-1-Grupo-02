package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class MatchEntity {


	@NotNull
	@NotBlank
	private int fkDoador;

	@NotNull
	@NotBlank
	private int fkProduto;

	@NotNull
	@NotBlank
	private int fkDonatario;

	@NotBlank
	@NotNull
	@Positive
	@Max(100)
	private Double matchPorcentagem;

	@PastOrPresent
	private LocalDateTime dataInteresse;

}
