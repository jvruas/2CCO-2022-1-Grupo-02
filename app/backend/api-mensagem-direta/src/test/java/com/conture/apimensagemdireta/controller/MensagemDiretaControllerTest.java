package com.conture.apimensagemdireta.controller;

import com.conture.apimensagemdireta.api.rest.usuario.UsuarioClient;
import com.conture.apimensagemdireta.dto.request.MensagemRequest;
import com.conture.apimensagemdireta.dto.response.MensagemResponse;
import com.conture.apimensagemdireta.entity.ChatDireto;
import com.conture.apimensagemdireta.entity.Mensagem;
import com.conture.apimensagemdireta.repository.ChatDiretoRepository;
import com.conture.apimensagemdireta.repository.MensagemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
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
	UsuarioClient usuarioClient;
    @MockBean
    private MensagemRepository mensagemRepository;

	@MockBean
	private ChatDiretoRepository repositoryChatDireto;

	ChatDireto c = new ChatDireto(

	);

	private ChatDireto createChat() {
		ChatDireto chat = new ChatDireto();
	chat.setIdChatDireto(1);
	chat.setFkUsuarioDestinatario(1);
	chat.setFkUsuarioRemetente(1);
	return chat;
	}
	private MensagemRequest createMensagem(){
		MensagemRequest mensagemRequest = new MensagemRequest(1,1,"Este é um teste");

		return mensagemRequest;
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
    @DisplayName("Retorna 204 e false quando não é encontrada nenhuma mensagem não visualizada")
    void existeMensagemVisualizadaRetorna204() {

		when(repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(2, 1)).thenReturn(Optional.of(new ChatDireto()));
		ResponseEntity<List<MensagemResponse>> response = mensagemDiretaController.listarMensagens (1,2);

		assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Retorna 200 e true quando é encontrada mensagens não visualizada")
    void existeMensagemNaoVisualizadaRetorna200() {
		MensagemRequest m = createMensagem();
		ResponseEntity response = mensagemDiretaController.existeMensagemNaoVisualizada(m.getFkUsuarioDestinatario());
		assertEquals(200, response.getStatusCodeValue());
    }


}
