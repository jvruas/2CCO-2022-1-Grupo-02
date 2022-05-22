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


	@Query("select m.mensagem, m.data from MensagemGrupo as m where m.fkProdutoDoacao =?1")
	List<MensagemGrupo> acharMensagemProduto(Integer fkProdutoDoacao);



}

