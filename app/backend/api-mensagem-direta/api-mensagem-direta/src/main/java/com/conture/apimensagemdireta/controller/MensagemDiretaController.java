package com.conture.apimensagemdireta.controller;

import com.conture.apimensagemdireta.dto.request.MensagemRequest;
import com.conture.apimensagemdireta.dto.response.MensagemResponse;
import com.conture.apimensagemdireta.entity.ChatDireto;
import com.conture.apimensagemdireta.entity.Mensagem;
import com.conture.apimensagemdireta.repository.ChatDiretoRepository;
import com.conture.apimensagemdireta.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensagem-direta")
public class MensagemDiretaController {

    @Autowired
    private ChatDiretoRepository repositoryChatDireto;

    @Autowired
    private MensagemRepository repositoryMensagem;

    @PostMapping
    public ResponseEntity<Integer> adicionarMensagem(@RequestBody @Valid MensagemRequest mensagemRequest){
        Mensagem mensagem = new Mensagem();

        Optional<ChatDireto> chatDireto = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(mensagemRequest.getFkUsuarioRemetente(), mensagemRequest.getFkUsuarioDestinatario());

        if(chatDireto.isEmpty()){
            chatDireto = Optional.of(new ChatDireto());

            chatDireto.get().setFkUsuarioRemetente(mensagemRequest.getFkUsuarioRemetente());
            chatDireto.get().setFkUsuarioDestinatario(mensagemRequest.getFkUsuarioDestinatario());

            this.repositoryChatDireto.save(chatDireto.get());
        }

        mensagem.setFkChatDireto(chatDireto.get());
        mensagem.setMensagem(mensagemRequest.getMensagem());

        repositoryMensagem.save(mensagem);
        return ResponseEntity.status(201).body(mensagem.getIdMensagem());
    }


    @GetMapping
    public ResponseEntity<List<MensagemResponse>> listarMensagens(@RequestParam Integer fkUsuarioRemetente,
                                                                  @RequestParam Integer fkUsuarioDonatario){

        Optional<ChatDireto> chatDireto1 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(fkUsuarioRemetente, fkUsuarioDonatario);
        Optional<ChatDireto> chatDireto2 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(fkUsuarioDonatario, fkUsuarioRemetente);

        if (chatDireto1.isEmpty() && chatDireto2.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        List<Mensagem> mensagens = this.repositoryMensagem.acharPorFkChatDiretoOrderByDataDesc(chatDireto1.get(), chatDireto2.get());
        List<MensagemResponse> mensagemResponse = new ArrayList<>();

        if(mensagens.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            for(Mensagem m : mensagens){
                if(m.getFkChatDireto().getFkUsuarioRemetente() == fkUsuarioRemetente){
                    m.setVisualizado(true);
                    repositoryMensagem.save(m);
                }
                mensagemResponse.add(m.converterMsgResponse(m));
            }
            return ResponseEntity.status(200).body(mensagemResponse);
        }
    }

    @GetMapping("/nao-visualizado")
    public ResponseEntity<Boolean> existeMensagemNaoVisualizada(@RequestParam Integer fkUsuarioRemetente){

        List<ChatDireto> chats = repositoryChatDireto.findByFkUsuarioRemetente(fkUsuarioRemetente);

        List<Mensagem> mensagemFalse = new ArrayList<>();

        for(Integer i = 0; i < chats.size()-1; i++){
            mensagemFalse = repositoryMensagem.findByFkChatDiretoAndVisualizado(chats.get(i), false);

            if(mensagemFalse.isEmpty()){
                return ResponseEntity.status(204).body(false);
            }
        }

        return ResponseEntity.status(200).body(true);
    }

}
