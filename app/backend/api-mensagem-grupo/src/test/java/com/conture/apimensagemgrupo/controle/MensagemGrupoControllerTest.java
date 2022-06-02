package com.conture.apimensagemgrupo.controle;

import com.conture.apimensagemgrupo.dto.requests.MensagemGrupoRequest;
import com.conture.apimensagemgrupo.entidade.MensagemGrupo;
import com.conture.apimensagemgrupo.repository.MensagemGrupoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {MensagemGrupoController.class})
class MensagemGrupoControllerTest {


	@Autowired
	MensagemGrupoController controller;

	@MockBean
	private MensagemGrupoRepository mensagemGrupoRepository;


//	@Test
//	@DisplayName("Verificando se irá voltar status 204")
//	void listarMensagensString() {
//		MensagemGrupo m1 = mock(MensagemGrupo.class);
//		List<MensagemGrupo> resposta = List.of(m1);
//
//
//
//		assertEquals(204, resposta.getStatusCodeValue());
//		assertNull(resposta.getBody());
//
//
//	}


	@Test
	@DisplayName("Verificando se irá voltar status 201")
	void adicionarMensagemIraRetornar201() {
		when(mensagemGrupoRepository.findByIdMensagemGrupo(1)).thenReturn(new MensagemGrupoRequest());
		MensagemGrupoRequest  m = mensagemGrupoRepository.findByIdMensagemGrupo(1);
		ResponseEntity response = controller.adicionarPergunta(m);
		assertEquals(201, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Verificando se irá voltar status 401")
	void adicionarMensagemIraRetornar401() {
		when(mensagemGrupoRepository.findByIdMensagemGrupoAndFkProdutoDoacao(1,1)).thenReturn(new MensagemGrupo());
		List<MensagemGrupo>  m = mensagemGrupoRepository.findByIdMensagemGrupoAndFkProdutoDoacao(1,2);
		ResponseEntity response = controller.adicionarPergunta(m);
		assertEquals(401, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("Verificando se irá voltar status 200")
	void listarSeIraRetornar200() {
		MensagemGrupo m1 = mock(MensagemGrupo.class);
		List<MensagemGrupo> resposta = List.of(m1);
		when(mensagemGrupoRepository.acharMensagemPergunta(1)).thenReturn(resposta);
		ResponseEntity<List<Object>> response = controller.listarMensagens(1);

		assertEquals(200, response.getStatusCodeValue());
	}


	@Test
	@DisplayName("Verificando se irá voltar status 204")
	void listarProdutoNaoExistente() {
		MensagemGrupo m1 = mock(MensagemGrupo.class);
		List<MensagemGrupo> resposta = List.of(m1);

		ResponseEntity<List<Object>> response = controller.listarMensagens(5);

		assertEquals(204, response.getStatusCodeValue());

	}

}
