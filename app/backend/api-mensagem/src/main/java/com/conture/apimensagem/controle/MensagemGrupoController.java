package com.conture.apimensagem.controle;

import com.conture.apimensagem.dto.requests.PerguntaRequest;
import com.conture.apimensagem.entidade.Pergunta;
import com.conture.apimensagem.repository.MensagemGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mensagem-grupo")
public class MensagemGrupoController {

    @Autowired
    private MensagemGrupoRepository mensagemGrupoRepository;


    @PostMapping("/grupo/pergunta")
    public ResponseEntity adicionarPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest) {
        Pergunta pergunta = new Pergunta();

        pergunta.setFkDonatario(perguntaRequest.getFkDonatario());
        pergunta.setMensagem(perguntaRequest.getMensagem());
        pergunta.setFkDoador(perguntaRequest.getFkDoador());
        pergunta.setFkProdutoDoacao(perguntaRequest.getFkProdutoDoacao());
        mensagemGrupoRepository.save(pergunta);
        return ResponseEntity.status(201).build();
    }


    @GetMapping("/grupo")
    public ResponseEntity listarMensagemGrupo(
            @RequestParam Long fkDoador,
            @RequestParam Long fkProdutoDoacao
    ) {
        List<Pergunta> perguntaList = this.mensagemGrupoRepository.findByFkDoadorAndFkProdutoDoacaoOrderByDataAsc(fkDoador, fkProdutoDoacao);

        if (perguntaList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<List<Object>> groupChatList = new ArrayList();

        for (int i = 0; i < perguntaList.size(); i++) {
            List<Object> topicList = new ArrayList();

            topicList.add(perguntaList.get(i));

            List<Pergunta> respostaList = this.mensagemGrupoRepository.findByIdPerguntaOrderByDataDesc(perguntaList.get(i).getIdPergunta());

            if (respostaList.isEmpty()) {
                continue;
            }

            topicList.add(respostaList);

            groupChatList.add(topicList);
        }

        return ResponseEntity.status(200).body(groupChatList);
    }


    @DeleteMapping("/grupo/resposta/{idPergunta}")
    public ResponseEntity removerResposta(@PathVariable Long idPergunta){
        List<Pergunta> pergunta = mensagemGrupoRepository.findByIdPergunta(idPergunta);
        if (pergunta.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        int perg ;
        for (Pergunta p : pergunta) {
            this.mensagemGrupoRepository.deleteByIdPergunta(p.getIdPergunta());
        }


        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/grupo/resposta/{idPergunta}")
    public ResponseEntity updateResposta(
            @PathVariable Long idPergunta,
            @RequestParam String mensagem
    ) {
        if (!this.mensagemGrupoRepository.existsByIdPergunta(idPergunta)){
            return ResponseEntity.status(404).build();
        }

        this.mensagemGrupoRepository.updateMensagemResposta(idPergunta, mensagem, Date.from(Instant.now()));

        return ResponseEntity.status(200).build();
    }



}
