package com.conture.apimensagemgrupo.repository;

import com.conture.apimensagemgrupo.dto.response.MensagemGrupoResponse;
import com.conture.apimensagemgrupo.entidade.MensagemGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensagemGrupoRepository extends JpaRepository<MensagemGrupo, Integer> {
	@Query("select new com.conture.apimensagemgrupo.dto.response.MensagemGrupoResponse(m.data, m.mensagem, m.fkUsuario) from MensagemGrupo as m where m.fkProdutoDoacao =?1 and m.fkMensagemPrincipal =?2")
	List<MensagemGrupoResponse> acharMensagemResposta(Integer fkProdutoDoacao, MensagemGrupo fkMensagemPrincipal);

	@Query("select m from MensagemGrupo as m where m.fkProdutoDoacao =?1 and m.fkMensagemPrincipal IS NULL")
	List<MensagemGrupo> acharMensagemPergunta(Integer fkProdutoDoacao);


	List<MensagemGrupo> findByIdMensagemGrupoAndFkProdutoDoacao(Integer idMensagemGrupo, Integer fkProdutoDoacao);

}
