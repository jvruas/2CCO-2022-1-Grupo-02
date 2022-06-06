package com.conture.apimensagemdireta.controller;

import com.conture.apimensagemdireta.api.rest.usuario.UsuarioClient;
import com.conture.apimensagemdireta.api.rest.usuario.UsuarioResposta;
import com.conture.apimensagemdireta.dto.request.MensagemRequest;
import com.conture.apimensagemdireta.dto.response.MensagemResponse;
import com.conture.apimensagemdireta.entity.ChatDireto;
import com.conture.apimensagemdireta.entity.Mensagem;
import com.conture.apimensagemdireta.repository.ChatDiretoRepository;
import com.conture.apimensagemdireta.repository.MensagemRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/mensagem-direta")
public class MensagemDiretaController {

	@Autowired
	private ChatDiretoRepository repositoryChatDireto;

	@Autowired
	private MensagemRepository repositoryMensagem;

	@Autowired
	private UsuarioClient usuarioClient;

	@PostMapping
	public ResponseEntity<Integer> adicionarMensagem(@RequestBody @Valid MensagemRequest mensagemRequest) {

		if(mensagemRequest.getFkUsuarioRemetente() == mensagemRequest.getFkUsuarioDestinatario()){
			return status(400).build();
		}

		try {
			Integer fkRemetente = this.usuarioClient.getIdUsuarioLogado(mensagemRequest.getFkUsuarioRemetente());
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(401).build();
		}

		try {
			Optional<UsuarioResposta> usuarioFkDestinatario = this.usuarioClient.getUsuarioById(mensagemRequest.getFkUsuarioDestinatario());
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(404).build();
		}

		Mensagem mensagem = new Mensagem();

		Optional<ChatDireto> chatDireto = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(mensagemRequest.getFkUsuarioRemetente(), mensagemRequest.getFkUsuarioDestinatario());

		if (chatDireto.isEmpty()) {
			chatDireto = Optional.of(new ChatDireto());

			chatDireto.get().setFkUsuarioRemetente(mensagemRequest.getFkUsuarioRemetente());
			chatDireto.get().setFkUsuarioDestinatario(mensagemRequest.getFkUsuarioDestinatario());

			this.repositoryChatDireto.save(chatDireto.get());
		}

		mensagem.setFkChatDireto(chatDireto.get());
		mensagem.setMensagem(mensagemRequest.getMensagem());

		repositoryMensagem.save(mensagem);
		return status(201).body(mensagem.getIdMensagem());
	}


	@GetMapping
	public ResponseEntity<List<MensagemResponse>> listarMensagens(@RequestParam @NotNull @Min(1) Integer fkUsuarioRemetente,
																  @RequestParam @NotNull @Min(1) Integer fkUsuarioDonatario) {

		try {
			Optional<UsuarioResposta> usuarioFkRemetente = this.usuarioClient.getUsuarioById(fkUsuarioRemetente);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(404).build();
		}

		try {
			Optional<UsuarioResposta> usuarioFkDestinatario = this.usuarioClient.getUsuarioById(fkUsuarioDonatario);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(404).build();
		}

		try {
			Integer fkDonatario = this.usuarioClient.getIdUsuarioLogado(fkUsuarioDonatario);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(401).build();
		}

		Optional<ChatDireto> chatDireto1 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(fkUsuarioRemetente, fkUsuarioDonatario);
		Optional<ChatDireto> chatDireto2 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(fkUsuarioDonatario, fkUsuarioRemetente);

		if (chatDireto1.isEmpty() && chatDireto2.isEmpty()) {
			return status(404).build();
		}

		List<Mensagem> mensagens = this.repositoryMensagem.acharPorFkChatDiretoOrderByDataDesc(chatDireto1.get(), chatDireto2.get());
		List<MensagemResponse> mensagemResponse = new ArrayList<>();

		if (mensagens.isEmpty()) {
			return status(204).build();
		} else {
			for (Mensagem m : mensagens) {
				if (m.getFkChatDireto().getFkUsuarioRemetente() == fkUsuarioRemetente) {
					m.setVisualizado(true);
					repositoryMensagem.save(m);
				}
				mensagemResponse.add(m.converterMsgResponse(m));
			}
			return status(200).body(mensagemResponse);
		}
	}

	@GetMapping("/nao-visualizado")
	public ResponseEntity existeMensagemNaoVisualizada(@RequestParam @NotNull @Min(1) Integer fkUsuarioDestinatario) {

		try {
			Optional<UsuarioResposta> usuarioFkDestinatario = this.usuarioClient.getUsuarioById(fkUsuarioDestinatario);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(404).build();
		}

		try {
			Integer fkRemetente = this.usuarioClient.getIdUsuarioLogado(fkUsuarioDestinatario);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(401).build();
		}

		List<ChatDireto> chats = repositoryChatDireto.findByFkUsuarioRemetente(fkUsuarioDestinatario);

		List<Mensagem> mensagemFalse = new ArrayList<>();

		for (Integer i = 0; i < chats.size() - 1; i++) {
			mensagemFalse = repositoryMensagem.findByFkChatDiretoAndVisualizado(chats.get(i), false);

			if (mensagemFalse.isEmpty()) {
				return status(204).build();
			}
		}

		return status(200).build();
	}

}
