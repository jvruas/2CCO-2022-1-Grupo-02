package com.conture.apiproduto.entity;

import com.conture.apiproduto.rest.usuario.UsuarioResposta;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class ProdutoDoacao {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
private Long idProdutoDoacao;

@NotBlank
@NotNull
@Size(min = 2)
private String nome;

@NotBlank
@NotNull
private String marca;

@NotBlank
@NotNull
private String modelo;


@NotBlank
@NotNull
@Size(min = 5)
private String descricao;


private boolean defeito;

private boolean status;

private boolean entrega;



@NotNull
@PositiveOrZero
private int quantidadeVisualizacao;

@PastOrPresent
@CreationTimestamp
@Temporal(TemporalType.TIMESTAMP)
private Date dataCriacao;

@Temporal(TemporalType.TIMESTAMP)
private Date dataConclusao;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private UsuarioResposta usuario;
@ManyToOne
private CategoriaProduto fkCategoriaProduto;


	public ProdutoDoacao(UsuarioResposta usuario, String nome, String marca, String modelo, String descricao,
						 boolean defeito, boolean entrega, CategoriaProduto fkCategoriaProduto) {
		this.usuario = usuario;
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.descricao = descricao;
		this.defeito = defeito;
		this.entrega = entrega;
		this.fkCategoriaProduto = fkCategoriaProduto;
	}

	public ProdutoDoacao() {
	}


	public UsuarioResposta getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioResposta usuario) {
		this.usuario = usuario;
	}

	public Long getIdProdutoDoacao() { return idProdutoDoacao; }

public String getNome() { return nome; }

public String getMarca() { return marca; }

public String getModelo() { return modelo; }

public String getDescricao() { return descricao; }

public boolean isDefeito() { return defeito; }

public boolean isStatus() { return status; }

public boolean isEntrega() { return entrega; }

public int getQuantidadeVisualizacao() { return quantidadeVisualizacao; }

public Date getDataCriacao() { return dataCriacao; }

public Date getDataConclusao() { return dataConclusao; }

public CategoriaProduto getFkCategoriaProduto() { return fkCategoriaProduto; }


public void setNome(String nome) { this.nome = nome; }

public void setMarca(String marca) { this.marca = marca; }

public void setModelo(String modelo) { this.modelo = modelo; }

public void setDescricao(String descricao) { this.descricao = descricao; }

public void setDefeito(boolean defeito) { this.defeito = defeito; }

public void setStatus(boolean status) { this.status = status; }

public void setEntrega(boolean entrega) { this.entrega = entrega; }

public void setQuantidadeVisualizacao(int quantidadeVisualizacao) { this.quantidadeVisualizacao = quantidadeVisualizacao; }

public void setDataConclusao(Date dataConclusao) { this.dataConclusao = dataConclusao; }

public void setFkCategoriaProduto(CategoriaProduto fkCategoriaProduto) { this.fkCategoriaProduto = fkCategoriaProduto; }
}
