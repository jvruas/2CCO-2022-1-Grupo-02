package com.conture.apimensagem.repository;

import com.conture.apimensagem.entidade.MensagemGrupo;
import com.conture.apimensagem.entidade.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface MensagemGrupoRepository extends JpaRepository<MensagemGrupo, Integer> {

    List<Pergunta> findByFkDoadorAndFkProdutoDoacaoOrderByDataAsc(Integer fkDoador, Integer fkProdutoDoacao);

	@Query("select top 50 mensagem from MensagemGrupo where fkProduto = ? ord");
	List<MensagemGrupo> findBy
    List<Pergunta> findByIdPerguntaOrderByDataDesc(Integer idPergunta);
    boolean existsByIdPergunta(Integer idPergunta);

    List<Pergunta> findByIdPergunta(Integer idPergunta);
    Pergunta deleteByIdPergunta(Integer IdPergunta);



    @Query("update Pergunta r set r.mensagem = ?2, r.data = ?3 where r.idPergunta = ?1")
    @Transactional
    @Modifying
    void updateMensagemResposta(Integer idPergunta, String mensagem, Date data);
}

