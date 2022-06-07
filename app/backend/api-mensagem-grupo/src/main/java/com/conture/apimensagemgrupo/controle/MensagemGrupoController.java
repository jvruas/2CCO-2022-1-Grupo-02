package com.conture.apimensagemgrupo.controle;

import com.conture.apimensagemgrupo.dto.requests.MensagemGrupoRequest;
import com.conture.apimensagemgrupo.dto.response.MensagemGrupoResponse;
import com.conture.apimensagemgrupo.entidade.MensagemGrupo;
import com.conture.apimensagemgrupo.repository.MensagemGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/mensagem-grupo")
public class MensagemGrupoController {

    @Autowired
    private MensagemGrupoRepository mensagemGrupoRepository;


    @PostMapping()
    public ResponseEntity adicionarPergunta(@RequestBody @Valid MensagemGrupoRequest mensagem) {
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

	@GetMapping
	public ResponseEntity<List<Object>> listarMensagens(@RequestParam Integer fkProdutoDoacao){
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
