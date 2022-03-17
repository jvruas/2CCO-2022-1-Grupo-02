package com.example.conture.controller;

import com.example.conture.domain.Mensagem;
import com.example.conture.domain.MensagemDireta;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/mensagem-direta")
public class MensagemDiretaController {

    List<MensagemDireta> mensagens = new ArrayList<>();

    @PostMapping
    public String cadastrar(@RequestBody MensagemDireta mensagem) {
        mensagens.add(mensagem);

        return "Cadastrado com sucesso";
    }

    @GetMapping
    public String exibirTodos() {
        return this.mensagens.stream()
                             .map( MensagemDireta::exibirNotificacao)
                             .reduce("",  (a, b) -> (a + b) + ("\n" + "-".repeat(35) + "\n") );
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable int id) {
        for (MensagemDireta m : mensagens) {
            if (m.getIdMensagem().equals(id)) {
                mensagens.remove(m);
                return "Mensagem removida com sucesso";
            }
        }
        return "Mensagem não encontrada";
    }

    @GetMapping("/{id}")
    public String exibirPorID(@PathVariable int id) {
        for (MensagemDireta mensagem : this.mensagens) {
            if (mensagem.getIdMensagem().equals(id)) {
                return ("-".repeat(30) + "\n" + mensagem.exibirNotificacao());
            }
        }
        return null;
    }

}
