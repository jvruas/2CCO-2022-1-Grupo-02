package com.conture.apiusuario.repository;

import com.conture.apiusuario.entity.Usuario;
import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<UsuarioLogadoResponse> findByEmailAndSenha(String email, String senha);

	Optional<UsuarioLogadoResponse> findByIdUsuario(Integer idUsuario);


	List<UsuarioLogadoResponse> findByNomeIgnoreCaseContainsOrderByNome(String nome);

	@Query("update Usuario u set u.email = null, u.senha = null, u.nome = null, u.sobrenome = null, u.cpf = null, u.genero = null, u.dataNascimento = null, u.estadoCivil = null, u.telefone = null, u.cep = null, u.grauEscolaridade = null, u.situacaoAtual = null, u.removido = true where u.idUsuario = ?1")
	@Modifying
	@Transactional
	void logicDelete(Integer idUsuario);

	@Query("update Usuario u set u.senha = ?2 where u.idUsuario = ?1")
	@Modifying
	@Transactional
	void updateSenha(Integer idUsuario, String novaSenha);

	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);

	@Query("Select u.idUsuario FROM Usuario u WHERE u.cpf = ?1")
	Integer getIdUserByCpf(String cpf);

}
