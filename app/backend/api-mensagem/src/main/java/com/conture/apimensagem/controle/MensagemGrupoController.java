package com.conture.apimensagem.controle;

import com.conture.apimensagem.dto.requests.MensagemGrupoListarRequest;
import com.conture.apimensagem.dto.requests.MensagemGrupoRequest;
import com.conture.apimensagem.dto.requests.PerguntaRequest;
import com.conture.apimensagem.entidade.MensagemGrupo;
import com.conture.apimensagem.entidade.Pergunta;
import com.conture.apimensagem.repository.MensagemGrupoRepository;
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
		mensagemGrupoRepository.save(mensagemGrupo);

		return ResponseEntity.status(201).build();
    }


	@GetMapping
	public ResponseEntity<List<MensagemGrupo>> listarMensagens(@RequestBody MensagemGrupoListarRequest mensagem){
	MensagemGrupo mensagemGrupo = new MensagemGrupo();


	mensagem.setInicio();
	mensagem.setQuantidade(50);




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
