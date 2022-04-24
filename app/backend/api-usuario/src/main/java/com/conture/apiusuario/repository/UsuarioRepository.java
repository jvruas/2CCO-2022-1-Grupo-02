package com.conture.apiusuario.repository;

import com.conture.apiusuario.entity.Usuario;
import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /*@Query("select new com.conture.apiusuario.resposta.UsuarioLogado(u.id_usuario, u.nome, u.sobrenome, u.genero," +
            "u.data_nascimento, u.estado_civil, u.cep, u.data_cadastro, u.escolaridade, u.fk_situacao_atual" +
            "from Usuario u")*/
    // List<UsuarioLogado> listaUsuario();

    UsuarioLogadoResponse findByEmailAndSenha(String email, String senha);

	Usuario findByIdUsuario(Long idUsuario);

	List<Usuario> findByNome(String nome);

	@Modifying
	@Transactional
	void deleteByIdUsuario(Long idUsuario);

	boolean existsByIdUsuario(Long idUsuario);
}
