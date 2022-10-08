package com.conture.apimensagemgrupo.controller;

import com.conture.apimensagemgrupo.api.rest.produto.ProdutoClient;
import com.conture.apimensagemgrupo.api.rest.produto.ProdutoResposta;
import com.conture.apimensagemgrupo.api.rest.usuario.UsuarioClient;
import com.conture.apimensagemgrupo.dto.requests.MensagemGrupoRequest;
import com.conture.apimensagemgrupo.dto.response.MensagemGrupoResponse;
import com.conture.apimensagemgrupo.entity.MensagemGrupo;
import com.conture.apimensagemgrupo.repository.MensagemGrupoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/mensagem-grupo")
@CrossOrigin(allowedHeaders = "*")
public class MensagemGrupoController {

    @Autowired
    private MensagemGrupoRepository mensagemGrupoRepository;

	@Autowired
	private UsuarioClient usuarioClient;

	@Autowired
	private ProdutoClient produtoClient;

    @PostMapping()
    public ResponseEntity adicionarPergunta(@RequestBody MensagemGrupoRequest mensagem) {

		try {
			Integer fkUsuario = this.usuarioClient.getIdUsuarioLogado(mensagem.getFkUsuario());
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(401).build();
		}

		try {
			Optional<ProdutoResposta> fkProduto = this.produtoClient.getProdutoById(mensagem.getFkProdutoDoacao());
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(404).build();
		}

		MensagemGrupo mensagemGrupo = new MensagemGrupo();


		mensagemGrupo.setMensagem(mensagem.getMensagem());
		mensagemGrupo.setData(mensagem.getData());
        mensagemGrupo.setFkUsuario(mensagem.getFkUsuario());
        mensagemGrupo.setFkProdutoDoacao(mensagem.getFkProdutoDoacao());



		if (mensagem.getFkMensagemPrincipal() != null) {
			MensagemGrupo mensagemPrincipal = new MensagemGrupo();

			List<MensagemGrupo> m = mensagemGrupoRepository.findByIdMensagemGrupoAndFkProdutoDoacao(mensagem.getFkMensagemPrincipal(),mensagem.getFkProdutoDoacao());
			if (m.isEmpty()){
				return status(401).build();
			}

			mensagemPrincipal.setIdMensagemGrupo(mensagem.getFkMensagemPrincipal());
			mensagemGrupo.setFkMensagemPrincipal(mensagemPrincipal);
		}

		mensagemGrupoRepository.save(mensagemGrupo);
		return status(201).build();
    }

	@GetMapping("/{fkProdutoDoacao}")
		public ResponseEntity<List<Object>> listarMensagens(@PathVariable Integer fkProdutoDoacao){

		try {
			Optional<ProdutoResposta> fkProduto = this.produtoClient.getProdutoById(fkProdutoDoacao);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}
			return status(404).build();
		}

		List<Object> listaGrupo = new ArrayList();
		List<MensagemGrupo> listaPergunta = this.mensagemGrupoRepository.acharMensagemPergunta(fkProdutoDoacao);
		if (listaPergunta.isEmpty()) {
			return status(204).build();
		}

		for (int i = 0; i < listaPergunta.size(); i++) {
            List<Object> topicList = new ArrayList();

            topicList.add(listaPergunta.get(i));

            List<MensagemGrupoResponse> listaResposta =
					this.mensagemGrupoRepository.acharMensagemResposta(fkProdutoDoacao, listaPergunta.get(i));

			if (listaResposta.isEmpty()) {
				listaGrupo.add(topicList);
			} else {
				topicList.add(listaResposta);
				listaGrupo.add(topicList);
			}
        }

		if (listaGrupo.isEmpty()){
			return status(204).build();
		}

		return status(200).body(listaGrupo);
	}
}
