package com.conture.apimensagemgrupo.controle;

import com.conture.apimensagemgrupo.entidade.MensagemGrupo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {MensagemGrupoController.class})
class MensagemGrupoControllerTest {

	@Autowired
	MensagemGrupoController controller;



	@Test
	@DisplayName("Verificando se irá voltar status 204")
	void listarMensagensString() {


		ResponseEntity<List<List<String>>> resposta = controller.listarMensagens(a);

		assertEquals(204, resposta.getStatusCodeValue());
		assertNull(resposta.getBody());


	}


	@Test
	@DisplayName("Verificando se irá voltar status 404")
	void listarMensagensInteiras() {


		ResponseEntity<List<List<String>>> resposta = controller.listarMensagens(1);

		assertEquals(200, resposta.getStatusCodeValue());
		assertNull(resposta.getBody());


	}

}
