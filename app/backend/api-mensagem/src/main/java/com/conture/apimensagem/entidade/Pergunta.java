package com.conture.apimensagem.entidade;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPergunta;

	@Positive
    @NotNull
    private Integer fkDonatario;

    @NotBlank
    @Size(min = 3, max = 100)
    private String mensagem;

	@PastOrPresent
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Positive
    @NotNull
    private Integer fkDoador;

	@Positive
    @NotNull
    private Integer fkProdutoDoacao;

	public Date getData() { return data; }

	public void setData(Date data) { this.data = data; }

	public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Integer idPergunta) {
        this.idPergunta = idPergunta;
    }

    public Integer getFkDonatario() {
        return fkDonatario;
    }

    public void setFkDonatario(Integer fkDonatario) {
        this.fkDonatario = fkDonatario;
    }

    public Integer getFkDoador() {
        return fkDoador;
    }

    public void setFkDoador(Integer fkDoador) {
        this.fkDoador = fkDoador;
    }

    public Integer getFkProdutoDoacao() {
        return fkProdutoDoacao;
    }

    public void setFkProdutoDoacao(Integer fkProdutoDoacao) {
        this.fkProdutoDoacao = fkProdutoDoacao;
    }
}
