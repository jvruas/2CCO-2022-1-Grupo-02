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
import java.util.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/mensagem-direta")
@CrossOrigin(allowedHeaders = "*")
public class MensagemDiretaController {

	@Autowired
	private ChatDiretoRepository repositoryChatDireto;

	@Autowired
	private MensagemRepository repositoryMensagem;

	@Autowired
	private UsuarioClient usuarioClient;


	@PostMapping("/chat")
	public ResponseEntity adicionarChat(
			@RequestParam @NotNull @Min(1) Integer idDoador,
			@RequestParam @NotNull @Min(1) Integer idDonatario
	) {
		if (idDoador == idDonatario) {
			return status(403).build();
		}

		try {
			Optional<UsuarioResposta> usuariofkRemetente = this.usuarioClient.getUsuarioById(idDoador);
			Optional<UsuarioResposta> usuarioFkDestinatario = this.usuarioClient.getUsuarioById(idDonatario);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(404).build();
		}

		Optional<ChatDireto> chatDireto1 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(idDoador, idDonatario);
		Optional<ChatDireto> chatDireto2 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(idDonatario, idDoador);

		if (chatDireto1.isPresent() && chatDireto2.isPresent()) {
			return status(409).build();
		}

		if (chatDireto1.isEmpty()) {
			chatDireto1 = Optional.of(new ChatDireto());

			chatDireto1.get().setFkUsuarioRemetente(idDoador);
			chatDireto1.get().setFkUsuarioDestinatario(idDonatario);

			this.repositoryChatDireto.save(chatDireto1.get());
		}

		if (chatDireto2.isEmpty()) {
			chatDireto2 = Optional.of(new ChatDireto());

			chatDireto2.get().setFkUsuarioRemetente(idDonatario);
			chatDireto2.get().setFkUsuarioDestinatario(idDoador);

			this.repositoryChatDireto.save(chatDireto2.get());
		}

		return status(201).build();
	}

	@PostMapping
	public ResponseEntity<Integer> adicionarMensagem(@RequestBody @Valid MensagemRequest mensagemRequest) {

		if (mensagemRequest.getFkUsuarioRemetente() == mensagemRequest.getFkUsuarioDestinatario()) {
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
	public ResponseEntity<List<MensagemResponse>> listarMensagens(@RequestParam @NotNull @Min(1) Integer fkUsuarioRemetente, @RequestParam @NotNull @Min(1) Integer fkUsuarioDonatario) {

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

		ChatDireto chatDireto1Value = null;
		ChatDireto chatDireto2Value = null;

		if (chatDireto1.isEmpty() && chatDireto2.isEmpty()) {
			return status(404).build();
		}

		try {
			chatDireto1Value = chatDireto1.get();
		} catch (NoSuchElementException ignored) {
		}

		try {
			chatDireto2Value = chatDireto2.get();
		} catch (NoSuchElementException ignored) {
		}

		List<Mensagem> mensagens = this.repositoryMensagem.acharPorFkChatDiretoOrderByDataDesc(chatDireto1Value, chatDireto2Value);
		List<MensagemResponse> mensagemResponse = new ArrayList<>();

		if (mensagens.isEmpty()) {
			return status(204).build();
		}

		for (Mensagem m : mensagens) {
			if (m.getFkChatDireto().getFkUsuarioRemetente() == fkUsuarioRemetente) {
				m.setVisualizado(true);
				repositoryMensagem.save(m);
			}
			mensagemResponse.add(m.converterMsgResponse(m, this.usuarioClient));
		}

		return status(200).body(mensagemResponse);
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

		List<ChatDireto> chats = repositoryChatDireto.findByFkUsuarioDestinatario(fkUsuarioDestinatario);

		List<Mensagem> mensagemFalse = new ArrayList<>();

		for (Integer i = 0; i < chats.size() - 1; i++) {
			mensagemFalse = repositoryMensagem.findByFkChatDiretoAndVisualizado(chats.get(i), false);

			if (mensagemFalse.isEmpty()) {
				return status(204).build();
			}
		}

		return status(200).build();
	}


	@GetMapping("/chat/all")
	public ResponseEntity<List<UsuarioResposta>> getAllChatsByRemetente(@RequestParam() @NotNull @Min(1) Integer fkUsuarioRemetente) {
		try {
			Optional<UsuarioResposta> usuarioFkRemetente = this.usuarioClient.getUsuarioById(fkUsuarioRemetente);
		} catch (FeignException response) {
			if (response.status() == -1) {
				return status(503).build();
			}

			return status(404).build();
		}

		List<ChatDireto> chatDiretoCollection = new ArrayList<>();

		chatDiretoCollection.addAll(this.repositoryChatDireto.findByFkUsuarioDestinatario(fkUsuarioRemetente));
		chatDiretoCollection.addAll(this.repositoryChatDireto.findByFkUsuarioRemetente(fkUsuarioRemetente));

		List<ChatDireto> chatDiretoResponseCollection = new ArrayList<>();

		for (int i = 0; i < chatDiretoCollection.size(); i++) {
			boolean isDuplicated = false;

			for (int j = i; j < chatDiretoCollection.size(); j++) {
				if (chatDiretoCollection.get(i).getFkUsuarioRemetente().equals(chatDiretoCollection.get(j).getFkUsuarioDestinatario())) {
					isDuplicated = true;
					break;
				}
			}

			if (!isDuplicated) {
				chatDiretoResponseCollection.add(chatDiretoCollection.get(i));
			}
		}

		List<UsuarioResposta> usuarioRespostaCollection = new ArrayList<>();

		for (ChatDireto chatDireto : chatDiretoResponseCollection) {
			if (chatDireto.getFkUsuarioRemetente() == fkUsuarioRemetente) {
				usuarioRespostaCollection.add(usuarioClient.getUsuarioById(chatDireto.getFkUsuarioDestinatario()).get());
				continue;
			}
			usuarioRespostaCollection.add(usuarioClient.getUsuarioById(chatDireto.getFkUsuarioRemetente()).get());
		}

		if (usuarioRespostaCollection.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(usuarioRespostaCollection);
	}

	@GetMapping("/chat")
	public ResponseEntity<UsuarioResposta> getChatByDestinatario(@RequestParam @NotNull @Min(1) Integer fkUsuarioRemetente, @RequestParam @NotNull @Min(1) Integer fkUsuarioDonatario) {
		try {
			Integer fkDonatario = this.usuarioClient.getIdUsuarioLogado(fkUsuarioRemetente);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(401).build();
		}

		try {
			Optional<UsuarioResposta> usuarioFkDestinatario = this.usuarioClient.getUsuarioById(fkUsuarioDonatario);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(404).build();
		}

		Optional<ChatDireto> chatDireto1 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(fkUsuarioRemetente, fkUsuarioDonatario);
		Optional<ChatDireto> chatDireto2 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(fkUsuarioDonatario, fkUsuarioRemetente);

		List<ChatDireto> chatCollection = new ArrayList<>(2);

		if (chatDireto1.isPresent()) {
			chatCollection.add(chatDireto1.get());
		}

		if (chatDireto2.isPresent()) {
			chatCollection.add(chatDireto2.get());
		}

		if (chatCollection.isEmpty()) {
			return status(204).build();
		}

		UsuarioResposta usuarioResponse = null;

		if (chatCollection.get(0).getFkUsuarioRemetente() == fkUsuarioRemetente) {
			usuarioResponse = usuarioClient.getUsuarioById(chatCollection.get(0).getFkUsuarioDestinatario()).get();
		} else {
			usuarioResponse = usuarioClient.getUsuarioById(chatCollection.get(0).getFkUsuarioRemetente()).get();
		}

		return status(200).body(usuarioResponse);
	}
}
