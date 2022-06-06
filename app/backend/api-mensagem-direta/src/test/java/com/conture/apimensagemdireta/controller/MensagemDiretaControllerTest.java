package com.conture.apimensagemdireta.controller;

import com.conture.apimensagemdireta.dto.request.MensagemRequest;
import com.conture.apimensagemdireta.dto.response.MensagemResponse;
import com.conture.apimensagemdireta.entity.ChatDireto;
import com.conture.apimensagemdireta.entity.Mensagem;
import com.conture.apimensagemdireta.repository.ChatDiretoRepository;
import com.conture.apimensagemdireta.repository.MensagemRepository;
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

@SpringBootTest(classes = {MensagemDiretaController.class})
class MensagemDiretaControllerTest {

    @Autowired
    MensagemDiretaController mensagemDiretaController;

    @MockBean
    private MensagemRepository mensagemRepository;

	@MockBean
	private ChatDiretoRepository repositoryChatDireto;

//    @Test
//    @DisplayName("")
//    void adicionarMensagemRetorna201() {
//		when(mensagemRepository.findByFkChatDiretoAndVisualizado(1,false).thenReturn(new MensagemRequest()));
//		MensagemRequest  m = mensagemRepository.findByFkChatDiretoAndVisualizado(1,false);
//		ResponseEntity response = mensagemDiretaController.adicionarMensagem(m);
//		assertEquals(201, response.getStatusCodeValue());
//    }

    @Test
    @DisplayName("Retorna 400 quando não encontra nenhum chat")
    void listarMensagensRetorna400() {
		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(1, 2)).thenReturn(Optional.ofNullable(null));
		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(2, 1)).thenReturn(Optional.ofNullable(null));
		ResponseEntity<List<MensagemResponse>> response = mensagemDiretaController.listarMensagens (1,1);

		assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Retorna 204 quando não encontra nenhuma mensagem")
    void listarMensagensRetorna204() {
		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(1, 2)).thenReturn(Optional.of(new ChatDireto()));
		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(2, 1)).thenReturn(Optional.of(new ChatDireto()));
		ResponseEntity<List<MensagemResponse>> response = mensagemDiretaController.listarMensagens (1,2);

		assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Retorna 200 quando encontra mensagem")
    void listarMensagensRetorna200() {
//      when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(1, 2)).thenReturn(Optional.of(new ChatDireto()));
//		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(2, 1)).thenReturn(Optional.of(new ChatDireto()));
//		ResponseEntity<List<MensagemResponse>> response = mensagemDiretaController.listarMensagens (1,2);
//
//		assertEquals(200, response.getStatusCodeValue());



		// aqui estamos criando um mock de AnimalEstimacao (parece, mas não é um AnimalEstimacao de verdade)
		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(1, 2)).thenReturn(Optional.of(new ChatDireto()));
		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(2, 1)).thenReturn(Optional.of(new ChatDireto()));
//		List<ChatDireto> listaMock = List.of(chat1, chat2);

// aqui estamos 'ensinando' ao mock de AnimalEstimacaoRepository o que ele deve fazer caso alguém, neste teste, invoque seu "findAll()"
// neste caso seu "findAll()" vai retornar uma lista com 2 elementos (o pet1 e o pet2)
		when(repositoryChatDireto.findAll()).thenReturn((List<ChatDireto>) mock(ChatDireto.class));

		ResponseEntity<List<MensagemResponse>>  resposta = mensagemDiretaController.listarMensagens(1,1);

		assertEquals(200, resposta.getStatusCodeValue());



    }

    @Test
    @DisplayName("Retorna 204 e false quando não é encontrada nenhuma mensagem não visualizada")
    void existeMensagemVisualizadaRetorna204() {
		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(1, 2)).thenReturn(Optional.of(new ChatDireto()));
		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(2, 1)).thenReturn(Optional.of(new ChatDireto()));
		ResponseEntity<List<MensagemResponse>> response = mensagemDiretaController.listarMensagens (1,2);

		assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Retorna 200 e true quando é encontrada mensagens não visualizada")
    void existeMensagemNaoVisualizadaRetorna200() {
		Mensagem m1 = mock(Mensagem.class);
		List<Mensagem> resposta = List.of(m1);
		ResponseEntity response = mensagemDiretaController.adicionarMensagem(mock(MensagemRequest.class));
		assertEquals(200, response.getStatusCodeValue());
    }


}
