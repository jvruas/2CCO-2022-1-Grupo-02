package com.conture.apimensagemdireta.repository;

import com.conture.apimensagemdireta.entity.ChatDireto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatDiretoRepository extends JpaRepository<ChatDireto, Integer> {
    Optional<ChatDireto> findByFkUsuarioRemetenteAndFkUsuarioDestinatario(Integer fkUsuarioRemetente, Integer fkUsuarioDestinatario);

    List<ChatDireto> findByFkUsuarioDestinatario(Integer fkUsuarioDestinatario);

	List<ChatDireto> findByFkUsuarioRemetente(Integer fkUsuarioRemetente);
}
