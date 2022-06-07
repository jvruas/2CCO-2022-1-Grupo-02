package com.conture.apiusuario.controller;

import com.conture.apiusuario.dto.request.UsuarioCadastroRequest;
import com.conture.apiusuario.dto.request.UsuarioLoginRequest;
import com.conture.apiusuario.dto.request.UsuarioSenhaRequest;
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

//	private UsuarioLogadoResponse createUsuarioLogado(){
//		UsuarioLogadoResponse u = new UsuarioLogadoResponse();
//		u.setSituacaoAtual("D");
//		u.setGenero("M");
//		u.setEstadoCivil("S");
//		u.setGrauEscolaridade("A");
//		u.setDataNascimento(new Date());
//		u.setEmail("teste@hotmail.com");
//		u.setCpf("46002259880");
//		u.setNome("Igor");
//		u.setSobrenome("Sérgio");
//
//		return u;
//	}

	@Test
	@DisplayName("Verificando se o resultado irá retornar 404")
	void loginRetorno404() {

		when(usuarioRepository.getByEmailAndSenha("teste@hotmail.com", "teste1234")).thenReturn(Optional.ofNullable(null));
		ResponseEntity<UsuarioLogadoResponse> response = controller.login(mock(UsuarioLoginRequest.class));

		assertEquals(404, response.getStatusCodeValue());
	}


//	@Test
//	@DisplayName("Verificando se o resultado irá retornar 201")
//	void loginRetorno201() {
//		Usuario u = createUsuarioLogado();
//		when(usuarioRepository.getByEmailAndSenha("teste@hotmail.com", "teste1234")).thenReturn(Optional.of(u)));
//		ResponseEntity<UsuarioLogadoResponse> response = controller.login(mock(UsuarioLoginRequest.class));
//		assertEquals(201, response.getStatusCodeValue());
//	}


	@Test
	@DisplayName("Vericando se usuário está deslogado retorna 404")
	void logoffRetorna404() {
		Usuario u = mock(Usuario.class);
		Optional<UsuarioLogadoResponse> usuarioLogado = (GerenciadorUsuario.buscaUsuarioLogado(u.getIdUsuario()));
		ResponseEntity<Integer> usuario = controller.adicionarUsuario(mock(UsuarioCadastroRequest.class));
		when(usuarioRepository.findByIdUsuario(1)).thenReturn(mock(UsuarioCadastroRequest.class));
		ResponseEntity<UsuarioLogadoResponse> response = controller.login(mock(UsuarioLoginRequest.class));
		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Vericando se usuário está deslogao retorna 200")
	void logoffRetorna200() {
		UsuarioCadastroRequest user= createUsuarioLogoff();
		when(usuarioRepository.findByIdUsuario(1)).thenReturn(user);
		ResponseEntity<UsuarioLogadoResponse> response = controller.logoff(1);
		assertEquals(200, response.getStatusCodeValue());
	}


	@Test
	@DisplayName("Verificando se usuário deslogado retorna 404")
	void existsUsuarioLogado404() {
		Usuario u = mock(Usuario.class);
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(u.getIdUsuario());
		ResponseEntity<Integer> usuario = controller.adicionarUsuario(mock(UsuarioCadastroRequest.class));
		when(usuarioRepository.findByIdUsuario(1)).thenReturn(mock(UsuarioCadastroRequest.class));
		ResponseEntity response = controller.login(mock(UsuarioLoginRequest.class));
		assertEquals(404, response.getStatusCodeValue());
	}


//	@Test
//	@DisplayName("Verificando se usuário está logado 200")
//	void existsUsuarioLogado200() {
//		Usuario u = new Usuario();
//		u.setSituacaoAtual(1);
//		u.setCep("08071080");
//		u.setGenero("Masculino");
//		u.setEstadoCivil("Solteiro");
//		u.setTelefone("11973484561");
//		u.setVerificado(false);
//		u.setGrauEscolaridade("Ensino medio");
//		u.setDataNascimento(new Date());
//		u.setEmail("dfsjdfnkjdfs@gmail.com");
//		u.setCpf("46002259880");
//		u.setNome("Igor");
//		u.setSobrenome("Sérgio");
//		u.setRemovido(false);
//		u.setSenha("teste1234");
//		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(u.getIdUsuario());
//		when(usuarioRepository.findByIdUsuario(1)).thenReturn(Optional.of(u));
//		ResponseEntity response = controller.login(mock(UsuarioLoginRequest.class));
//		assertEquals(200, response.getStatusCodeValue());
//	}


	@Test
	@DisplayName("Verificando se irá retornar 404 quando passar como null")
	void deletarUsuario404() {
		when(usuarioRepository.getByEmailAndSenha("teste@hotmail.com", "teste1234")).thenReturn(Optional.ofNullable(null));
		ResponseEntity response = controller.deletarUsuario(mock(UsuarioLoginRequest.class),"S");
		assertEquals(404, response.getStatusCodeValue());
	}

//	@Test
//	@DisplayName("Verificando se irá retornar 403 quando passar como null")
//	void deletarUsuario403() {
//		 Usuario usuario = new Usuario();
//		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(usuario.getIdUsuario());
//
//		when(usuarioRepository.getByEmailAndSenha(usuarioLogado.get().getEmail(),"dssdadsa")).thenReturn(Optional.ofNullable(null));
//
//		ResponseEntity response = controller.deletarUsuario(Optional.of(mock(UsuarioLogadoResponse)) ,"S");
//
//		assertEquals(403, response.getStatusCodeValue());
//
//	}

	@Test
	@DisplayName("Verificar se irá retornar 404 caso lista esteja vazia")
	void atualizarSenha404() {
		when(usuarioRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		ResponseEntity response = controller.atualizarSenha(mock(UsuarioSenhaRequest.class));

		assertEquals(404, response.getStatusCodeValue());
	}


	@Test
	@DisplayName("Verificar se irá retornar 403 caso Ids não sejam compativeis")
	void atualizarSenha403() {

		UsuarioSenhaRequest usuarioSenha = new UsuarioSenhaRequest();
		Optional<Usuario> usuario = this.usuarioRepository.findById(usuarioSenha.getIdUsuario());
		when(!usuario.get().getSenha().equals(usuarioSenha.getSenhaAtual()));
		ResponseEntity response = controller.atualizarSenha(mock(UsuarioSenhaRequest.class));
		assertEquals(403, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Verificar se irá retornar 409 caso lista esteja vazia")
	void atualizarSenha409() {
		UsuarioSenhaRequest usuarioSenha = new UsuarioSenhaRequest();
		usuarioSenha.setIdUsuario(1);
		usuarioSenha.setSenhaAtual("senha1234");
		usuarioSenha.setSenhaNova("senha12345");

		Optional<Usuario> usuario = this.usuarioRepository.findById(usuarioSenha.getIdUsuario());
		ResponseEntity response = controller.atualizarSenha(usuarioSenha);
		assertEquals(409, response.getStatusCodeValue());
	}


}
