package com.conture.apimensagemdireta.controller;

import com.conture.apimensagemdireta.entity.Mensagem;
import com.conture.apimensagemdireta.repository.MensagemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {MensagemDiretaController.class})
class MensagemDiretaControllerTest {

    @Autowired
    MensagemDiretaController mensagemDiretaController;

    @MockBean
    private MensagemRepository mensagemRepository;


    @Test
    @DisplayName("")
    void adicionarMensagem() {
    }

    @Test
    @DisplayName("Retorna 400 quando não encontra nenhum chat")
    void listarMensagensRetorna400() {
    }

    @Test
    @DisplayName("Retorna 204 quando não encontra nenhuma mensagem")
    void listarMensagensRetorna204() {
    }

   /* @Test
    @DisplayName("Retorna 200 quando encontra mensagem")
    void listarMensagensRetorna200() {
        Mensagem m = mock(Mensagem.class);
        List<Mensagem> resposta = List.of(m);
        when(mensagemRepository.acharPorFkChatDiretoOrderByDataDesc(m)).thenReturn(resposta);
        ResponseEntity<List<Object>> response =  mensagemDiretaController.listarMensagens(1);

        assertEquals(200, response.getStatusCodeValue());
    }*/

    @Test
    @DisplayName("Retorna 204 e false quando não é encontrada nenhuma mensagem não visualizada")
    void existeMensagemNaoVisualizadaRetorna204() {
    }

    @Test
    @DisplayName("Retorna 200 e true quando é encontrada mensagens não visualizada")
    void existeMensagemNaoVisualizadaRetorna200() {
    }
}