package com.conture.apiusuario.repository;

import com.conture.apiusuario.entity.ImagemUsuario;
import com.conture.apiusuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ImagemUsuarioRepository extends JpaRepository<ImagemUsuario, Integer> {

	void deleteByUsuarioIdUsuario(Integer idUsuario);

	Integer countByUsuarioIdUsuario(Integer idUsuario);

	@Query("select i.idImagemUsuario from ImagemUsuario i where i.usuario = ?1 and i.tipoImagem = ?2")
	Optional<Integer> getImagemID(Usuario idUsuario, String tipoImagem);

	@Query("update ImagemUsuario i set i.imagemUsuario = ?2 where i.idImagemUsuario = ?1")
	@Modifying
	@Transactional
	void updateImagem(Integer idImagemUsuario, byte[] novaImagem);
}
