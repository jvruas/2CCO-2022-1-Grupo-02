package com.conture.apimensagem.repository;

import com.conture.apimensagem.entidade.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
	@Query("select m from Mensagem m where m.fkChatDireto = ?1 or m.fkChatDireto = ?2 ORDER BY m.data DESC")
	List<Mensagem> acharPorFkChatDiretoOrderByDataDesc(Long fkChatDireto1, Long fkChatDireto2);

	@Query("update Mensagem m set m.mensagem = ?2, m.data = ?3 where m.idMensagem = ?1")
	@Transactional
	@Modifying
	void updateMensagem(Long idMensagem, String mensagem, Date data);
}
