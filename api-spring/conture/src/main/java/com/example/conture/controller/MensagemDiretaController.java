package com.example.conture.controller;

import com.example.conture.domain.MensagemDireta;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mensagem-direta")
public class MensagemDiretaController {

    List<MensagemDireta> mensagens = new ArrayList<>();

    @PostMapping
    public String cadastrar(@RequestBody MensagemDireta mensagem) {
        mensagens.add(mensagem);
        return "Mensagem cadastrada com sucesso!";
    }

    @GetMapping
    public String exibirTodos() {
        if (this.mensagens.isEmpty()) {
            return "Nenhuma nova mensagem direta";
        }

        return this.mensagens.stream()
                             .map(MensagemDireta::exibirNotificacao)
                             .reduce("",  (a, b) -> ("-".repeat(35) + "\n") + (a + b) + "\n");
    }

    @GetMapping("/{id}")
    public String exibirPorID(@PathVariable int id) {
        for (MensagemDireta mensagem : this.mensagens) {
            if (mensagem.getIdMensagem().equals(id)) {
                return ("-".repeat(35) + "\n" + mensagem.exibirNotificacao() + "\n" + "-".repeat(35));
            }
        }
        return "Mensagem não encontrada";
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
}
