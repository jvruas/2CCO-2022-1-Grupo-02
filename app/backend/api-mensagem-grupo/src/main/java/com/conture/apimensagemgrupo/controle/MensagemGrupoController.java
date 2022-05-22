package com.conture.apimensagemgrupo.controle;

import com.conture.apimensagemgrupo.dto.requests.MensagemGrupoRequest;
import com.conture.apimensagemgrupo.entidade.MensagemGrupo;
import com.conture.apimensagemgrupo.repository.MensagemGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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

			mensagemPrincipal.setIdMensagemGrupo(mensagem.getFkMensagemPrincipal());

			mensagemGrupo.setFkMensagemPrincipal(mensagemPrincipal);
		}

		mensagemGrupoRepository.save(mensagemGrupo);

		return ResponseEntity.status(201).build();
    }


	@GetMapping
	public ResponseEntity listarMensagens(@RequestParam Integer fkProdutoDoacao){
		List<MensagemGrupo> listaMensagem = this.mensagemGrupoRepository.acharMensagemProduto(fkProdutoDoacao);
		if (listaMensagem.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(listaMensagem);
	}

//    @GetMapping("/grupo")
//    public ResponseEntity listarMensagemGrupo(
//            @RequestParam Integer fkDoador,
//            @RequestParam Integer fkProdutoDoacao
//    ) {
//        List<Pergunta> perguntaList = this.mensagemGrupoRepository.findByFkDoadorAndFkProdutoDoacaoOrderByDataAsc(fkDoador, fkProdutoDoacao);
//
//        if (perguntaList.isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }
//
//
//
//        List<List<Object>> groupChatList = new ArrayList();
//
//        for (int i = 0; i < perguntaList.size(); i++) {
//            List<Object> topicList = new ArrayList();
//
//            topicList.add(perguntaList.get(i));
//
//            List<Pergunta> respostaList = this.mensagemGrupoRepository.findByIdPerguntaOrderByDataDesc(perguntaList.get(i).getIdPergunta());
//
//            if (respostaList.isEmpty()) {
//                continue;
//            }
//
//            topicList.add(respostaList);
//
//            groupChatList.add(topicList);
//        }
//
//        return ResponseEntity.status(200).body(groupChatList);
//    }





}
