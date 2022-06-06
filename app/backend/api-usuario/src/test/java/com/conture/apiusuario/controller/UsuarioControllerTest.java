package com.conture.apiusuario.controller;

import com.conture.apiusuario.dto.request.UsuarioCadastroRequest;
import com.conture.apiusuario.dto.request.UsuarioLoginRequest;
import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;
import com.conture.apiusuario.entity.Usuario;
import com.conture.apiusuario.repository.*;
import com.conture.apiusuario.utility.GerenciadorUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UsuarioController.class})

class UsuarioControllerTest {

	@Autowired
	UsuarioController controller;

	@MockBean
	private AvaliacaoRepository avaliacaoRepository;
	@MockBean
	private ReporteRepository reporteRepository;
	@MockBean
	private SituacaoAtualRepository situacaoAtualRepository;
	@MockBean
	private TipoReporteRepository tipoReporteRepository;
	@MockBean
	private UsuarioRepository usuarioRepository;
	@MockBean
	private ImagemUsuarioRepository imagemUsuarioRepository;
	@MockBean
	private DesligamentoContaRepository desligamentoContaRepository;


	@Test
	@DisplayName("Verificando se o resultado irá retornar 404")
	void loginRetorno404() {

		when(usuarioRepository.getByEmailAndSenha("teste@hotmail.com", "teste1234")).thenReturn(Optional.ofNullable(null));
		ResponseEntity<UsuarioLogadoResponse> response = controller.login(mock(UsuarioLoginRequest.class));

		assertEquals(404, response.getStatusCodeValue());
	}


	@Test
	@DisplayName("Verificando se o resultado irá retornar 201")
	void loginRetorno200() {

		when(usuarioRepository.getByEmailAndSenha("teste@hotmail.com", "teste1234")).thenReturn((Optional<UsuarioLogadoResponse>) Optional.of(mock(UsuarioLoginRequest.class)));
		ResponseEntity<UsuarioLogadoResponse> response = controller.login(mock(UsuarioLoginRequest.class));

		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Vericando se usuário está deslogao retorna 404")
	void logoffRetorna404() {
		Usuario u = mock(Usuario.class);
		Optional<UsuarioLogadoResponse> usuarioLogado = (GerenciadorUsuario.buscaUsuarioLogado(u.getIdUsuario()));


		List<Usuario> listaMock = List.of(u);
		when(repository.findAll()).thenReturn(listaMock);
		ResponseEntity<UsuarioLogadoResponse> response = controller.login(mock(UsuarioLoginRequest.class));
		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Verificando se lista de usuários vai voltar vazia e retornar 404")
	void existsUsuarioLogado404() {
		when(usuarioRepository.("teste@hotmail.com", "teste1234")).thenReturn(Optional.ofNullable(null));

		ResponseEntity<UsuarioLogadoResponse> response = controller.login(mock(UsuarioLoginRequest.class));

		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	void reportarUsuario() {
	}

	@Test
	void deletarUsuario() {
	}

	@Test
	void pesquisarUsuarioNome() {
	}

	@Test
	void pesquisarUsuarioId() {
	}

	@Test
	void pesquisarUsuarioEmail() {
	}

	@Test
	void listarTiposReporte() {
	}

	@Test
	void listarSituacaoAtual() {
	}

	@Test
	void atualizarSenha() {
	}

	@Test
	void atualizarPerfil() {
	}

	@Test
	void adicionarImagem() {
	}

	@Test
	void atualizarImagem() {
	}

	@Test
	void getImagem() {
	}

	@Test
	void deletarImagem() {
	}
}
