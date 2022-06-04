package com.conture.apiusuario.controller;

import com.conture.apiusuario.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
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
	@DisplayName("Verificando se o resultado irá retornar 201")
	void adicionarUsuario201() {
		when(mensagemGrupoRepository.findByIdMensagemGrupo(1)).thenReturn(new MensagemGrupoRequest());
		MensagemGrupoRequest  m = mensagemGrupoRepository.findByIdMensagemGrupo(1);
		ResponseEntity response = controller.adicionarPergunta(m);
		assertEquals(201, response.getStatusCodeValue());
	}

	@Test
	void login() {
	}

	@Test
	void logoff() {
	}

	@Test
	void existsUsuarioLogado() {
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
