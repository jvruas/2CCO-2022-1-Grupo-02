package com.conture.apiproduto.model.entity;

import com.conture.apiproduto.model.dto.request.ProdutoDoacaoRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class ProdutoDoacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProdutoDoacao;

	@NotNull
	@NotBlank
	@Size(max = 60)
	private String nome;

	@NotNull
	@NotBlank
	@Size(max = 60)
	private String marca;

	@NotNull
	@NotBlank
	@Size(max = 60)
	private String modelo;

	@NotBlank
	@NotNull
	@Size(max = 255)
	private String descricao;

	private String bucketName;
	
	private String objectName;

	@NotNull
	private Boolean defeito;

	@NotNull
	private Boolean entrega;

	@NotNull
	@PositiveOrZero
	private int quantidadeVisualizacao;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;

	@NotNull
	private boolean status;

	@NotNull
	private boolean removido;

	@NotNull
	@ManyToOne
	@JoinColumn(name="fk_categoria_produto")
	private CategoriaProduto categoriaProduto;

	@NotNull
	@Positive
	private Integer fkDoador;


	public ProdutoDoacao() {}

	private ProdutoDoacao(Integer idProdutoDoacao) {
		this.idProdutoDoacao = idProdutoDoacao;
	}

	private ProdutoDoacao(ProdutoDoacaoRequest produtoDoacaoRequest) {
		this.nome = produtoDoacaoRequest.getNome();
		this.marca = produtoDoacaoRequest.getMarca();
		this.modelo = produtoDoacaoRequest.getModelo();
		this.descricao = produtoDoacaoRequest.getDescricao();
		this.defeito = produtoDoacaoRequest.getDefeito();
		this.entrega = produtoDoacaoRequest.getEntrega();
		this.setCategoriaProduto(produtoDoacaoRequest.getFkCategoriaProduto());
		this.fkDoador = produtoDoacaoRequest.getIdDoador();
	}

	private ProdutoDoacao(
			Integer fkDoador,
			String nome,
			String marca,
			String modelo,
			String descricao,
			Boolean defeito,
			Boolean entrega,
			CategoriaProduto categoriaProduto

	) {
		this.fkDoador = fkDoador;
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.descricao = descricao;
		this.defeito = defeito;
		this.entrega = entrega;
		this.categoriaProduto = categoriaProduto;
	}


	public static ProdutoDoacao fromPattern(Integer idProdutoDoacao) {
		return new ProdutoDoacao(idProdutoDoacao);
	}

	public static ProdutoDoacao fromPattern (ProdutoDoacaoRequest produtoDoacaoRequest) {
		return new ProdutoDoacao(produtoDoacaoRequest);
	}

	public static ProdutoDoacao fromPattern(
			Integer fkDoador,
			String nome,
			String marca,
			String modelo,
			String descricao,
			Boolean defeito,
			Boolean entrega,
			CategoriaProduto categoriaProduto

	) {
		return new ProdutoDoacao(
				fkDoador,
				nome,
				marca,
				modelo,
				descricao,
				defeito,
				entrega,
				categoriaProduto
		);
	}

	public Integer getIdProdutoDoacao() {
		return idProdutoDoacao;
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getBucketName() {
		return bucketName;
	}

	public String getObjectName() {
		return objectName;
	}

	public Boolean getDefeito() {
		return defeito;
	}

	public Boolean getEntrega() {
		return entrega;
	}

	public int getQuantidadeVisualizacao() {
		return quantidadeVisualizacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public boolean isStatus() {
		return status;
	}

	public boolean isRemovido() {
		return removido;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public Integer getFkDoador() {
		return fkDoador;
	}

	public void setIdProdutoDoacao(Integer idProdutoDoacao) {
		this.idProdutoDoacao = idProdutoDoacao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public void setDefeito(Boolean defeito) {
		this.defeito = defeito;
	}

	public void setEntrega(Boolean entrega) {
		this.entrega = entrega;
	}

	public void setQuantidadeVisualizacao(int quantidadeVisualizacao) {
		this.quantidadeVisualizacao = quantidadeVisualizacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setRemovido(boolean removido) {
		this.removido = removido;
	}

	public void setCategoriaProduto(Integer fkCategoriaProduto) {
		CategoriaProduto categoriaProduto = CategoriaProduto.fromPattern(fkCategoriaProduto);
		this.categoriaProduto = categoriaProduto;
	}

	public void setFkDoador(Integer fkDoador) {
		this.fkDoador = fkDoador;
	}
}
