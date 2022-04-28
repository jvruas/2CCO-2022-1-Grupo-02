package com.conture.apimensagem.repository;

import com.conture.apimensagem.entidade.ChatDireto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatDiretoRepository extends JpaRepository<ChatDireto, Long> {
	Optional<ChatDireto> findByFkUsuarioRemetenteAndFkUsuarioDestinatario(Long fkUsuarioRemetente, Long fkUsuarioDestinatario);
}
