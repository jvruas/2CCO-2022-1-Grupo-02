package com.conture.apimensagemgrupo.repository;

import com.conture.apimensagemgrupo.entidade.MensagemGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensagemGrupoRepository extends JpaRepository<MensagemGrupo, Integer> {


	@Query("select m.mensagem, m.data from MensagemGrupo as m where m.fkProdutoDoacao =?1 order by m.data")
	List<String> acharMensagemProduto(Integer fkProdutoDoacao);



}

