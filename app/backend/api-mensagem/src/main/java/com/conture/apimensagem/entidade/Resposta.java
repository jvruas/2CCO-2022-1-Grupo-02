package com.conture.apimensagem.entidade;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResposta;

    @PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

    @NotBlank
    @Size(min = 3, max = 100)
    private String mensagem;

	@Positive
    @NotNull
    private Long fkPergunta;

	@Positive
    @NotNull
    private Long fkDoador;

	@Positive
    @NotNull
    private Long fkProdutoDoacao;

    public Long getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(Long idResposta) {
        this.idResposta = idResposta;
    }

	public Date getData() { return data; }

	public void setData(Date data) { this.data = data; }

	public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getFkPergunta() {
        return fkPergunta;
    }

    public void setFkPergunta(Long fkPergunta) {
        this.fkPergunta = fkPergunta;
    }

    public Long getFkDoador() {
        return fkDoador;
    }

    public void setFkDoador(Long fkDoador) {
        this.fkDoador = fkDoador;
    }

    public Long getFkProdutoDoacao() {
        return fkProdutoDoacao;
    }

    public void setFkProdutoDoacao(Long fkProdutoDoacao) {
        this.fkProdutoDoacao = fkProdutoDoacao;
    }
}
