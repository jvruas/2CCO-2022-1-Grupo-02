package com.conture.apimensagem.repository;

import com.conture.apimensagem.entidade.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
	List<Resposta> findByFkPerguntaOrderByDataDesc(Long fkPergunta);

	Optional<Resposta> findByIdResposta(Long idResposta);

	@Query("update Resposta r set r.mensagem = ?2, r.data = ?3 where r.idResposta = ?1")
	@Transactional
	@Modifying
	void updateMensagemResposta(Long idResposta, String mensagem, Date data);
}
