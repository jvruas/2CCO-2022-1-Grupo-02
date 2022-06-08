package com.conture.apiusuario.controller;

import com.conture.apiusuario.dto.request.UsuarioCadastroRequest;
import com.conture.apiusuario.dto.request.UsuarioLoginRequest;
import com.conture.apiusuario.dto.request.UsuarioSenhaRequest;
import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;
import com.conture.apiusuario.entity.TipoReporte;
import com.conture.apiusuario.entity.Usuario;
import com.conture.apiusuario.repository.*;
import com.conture.apiusuario.utility.GerenciadorUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UsuarioController.class})

class UsuarioControllerTest {

	@Autowired
	UsuarioController controller;

//	@MockBean
//	private AvaliacaoRepository avaliacaoRepository;
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

	private Usuario createUsuario(){
		Usuario u = new Usuario();
		u.setSituacaoAtual(1);
		u.setCep("08071080");
		u.setGenero("Masculino");
		u.setEstadoCivil("Solteiro");
		u.setTelefone("11973484561");
		u.setVerificado(false);
		u.setGrauEscolaridade("Ensino medio");
		u.setDataNascimento(new Date());
		u.setEmail("dfsjdfnkjdfs@gmail.com");
		u.setCpf("46002259880");
		u.setNome("Igor");
		u.setSobrenome("Sérgio");
		u.setRemovido(false);
		u.setSenha("teste1234");
		return u;
	}

	private UsuarioCadastroRequest createUsuarioLogoff(){
		UsuarioCadastroRequest u = new UsuarioCadastroRequest();
		u.setFkSituacaoAtual(1);
		u.setCep("08071080");
		u.setGenero("Masculino");
		u.setEstadoCivil("Solteiro");
		u.setTelefone("11973484561");
		u.setGrauEscolaridade("Ensino medio");
		u.setDataNascimento(new Date());
		u.setEmail("dfsjdfnkjdfs@gmail.com");
		u.setCpf("46002259880");
		u.setNome("Igor");
		u.setSobrenome("Sérgio");
		u.setSenha("teste1234");
		return u;
	}

	private UsuarioLogadoResponse createUsuarioLogado(){
		UsuarioLogadoResponse u = new UsuarioLogadoResponse(
				1,
				"teste@hotmail.com",
				"Igor",
				"Sérgio",
				"M",
				new Date(),
				"S",
				new Date(),
				"A",
				"46002259881",
				"D"
				);

		return u;
	}

	@Test
	@DisplayName("Verificando se o resultado ira retornar 404")
	void loginRetorno404() {

		when(usuarioRepository.getByEmailAndSenha("teste@hotmail.com", "teste1234")).thenReturn(Optional.ofNullable(null));
		ResponseEntity<UsuarioLogadoResponse> response = controller.login(mock(UsuarioLoginRequest.class));

		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Vericando se usuario está deslogado retorna 404")
	void logoffRetorna404() {
		Usuario u = mock(Usuario.class);
		Optional<UsuarioLogadoResponse> usuarioLogado = (GerenciadorUsuario.buscaUsuarioLogado(u.getIdUsuario()));
		ResponseEntity<Integer> usuario = controller.adicionarUsuario(mock(UsuarioCadastroRequest.class));
		when(usuarioRepository.findByIdUsuario(1)).thenReturn(mock(UsuarioCadastroRequest.class));
		ResponseEntity<UsuarioLogadoResponse> response = controller.login(mock(UsuarioLoginRequest.class));
		assertEquals(404, response.getStatusCodeValue());
	}


	@Test
	@DisplayName("Verificando se usuario deslogado retorna 404")
	void existsUsuarioLogado404() {
		Usuario u = mock(Usuario.class);
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(u.getIdUsuario());
		ResponseEntity<Integer> usuario = controller.adicionarUsuario(mock(UsuarioCadastroRequest.class));
		when(usuarioRepository.findByIdUsuario(1)).thenReturn(mock(UsuarioCadastroRequest.class));
		ResponseEntity response = controller.login(mock(UsuarioLoginRequest.class));
		assertEquals(404, response.getStatusCodeValue());
	}



	@Test
	@DisplayName("Verificando se ira retornar 404 quando passar como null")
	void deletarUsuario404() {
		when(usuarioRepository.getByEmailAndSenha("teste@hotmail.com", "teste1234")).thenReturn(Optional.ofNullable(null));
		ResponseEntity response = controller.deletarUsuario(mock(UsuarioLoginRequest.class),"S");
		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Verificar se ira retornar 404 caso lista esteja vazia")
	void atualizarSenha404() {
		when(usuarioRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		ResponseEntity response = controller.atualizarSenha(mock(UsuarioSenhaRequest.class));

		assertEquals(404, response.getStatusCodeValue());
	}






	@Test
	@DisplayName("Verificar se ira retornar 200 caso retorne um usuario pela pesquisa do email")
	void pesquisarUsuarioIdPeloEmailRetorna200() {


		when(usuarioRepository.getByEmail("teste@hotmail.com")).thenReturn(Optional.of(1));
		ResponseEntity<Integer> response = controller.pesquisarUsuarioEmail("teste@hotmail.com");
		assertEquals(200, response.getStatusCodeValue());

	}

	@Test
	@DisplayName("Verificar se ira retornar 404 caso retorne um usuario pela pesquisa do email")
	void pesquisarUsuarioIdPeloEmailRetorna404() {


		when(usuarioRepository.getByEmail("teste@hotmail.com")).thenReturn(Optional.of(1));
		ResponseEntity<Integer> response = controller.pesquisarUsuarioEmail("testi@hotmail.com");
		assertEquals(404, response.getStatusCodeValue());

	}

	@Test
	@DisplayName("Verificar se ira retornar 200 caso encontre um cpf")
	void pesquisarUsuarioCpfRetorna200() {

		when(usuarioRepository.getByCpf("46002259881")).thenReturn(Optional.of(1));
		ResponseEntity<Integer> response = controller.pesquisarUsuarioCpf("46002259881");
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Verificar se ira retornar 404 caso não encontre um cpf")
	void pesquisarUsuarioCpfRetorna404() {

		when(usuarioRepository.getByCpf("46002259881")).thenReturn(Optional.of(1));
		ResponseEntity<Integer> response = controller.pesquisarUsuarioCpf("46002259882");
		assertEquals(404, response.getStatusCodeValue());
	}

}
