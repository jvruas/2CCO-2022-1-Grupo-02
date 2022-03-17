package com.example.conture.controller;

import com.example.conture.domain.MensagemGrupo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mensagem-grupo")
public class MensagemGrupoController {

    List<MensagemGrupo> mensagens = new ArrayList();

    @PostMapping
    public String cadastrar(@RequestBody MensagemGrupo mensagem) {
        this.mensagens.add(mensagem);
        return "Mensagem cadastrada com sucesso!";
    }

    @GetMapping
    public String exibirTodos() {
        if (this.mensagens.isEmpty()) {
            return "Nenhuma nova mensagem de grupo";
        }

        return this.mensagens.stream()
                             .map(MensagemGrupo::exibirNotificacao)
                             .reduce("",  (a, b) -> (a + b) + ("\n" + "-".repeat(50) + "\n"));
    }

    @GetMapping("/{id}")
    public String exibirPorID(@PathVariable int id) {
        for (MensagemGrupo mensagem : this.mensagens) {
            if (mensagem.getIdMensagem().equals(id)) {
                return ("-".repeat(35) + "\n" + mensagem.exibirNotificacao() + "\n" + "-".repeat(50));
            }
        }
        return "Mensagem não encontrada";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable int id) {
        for (MensagemGrupo mensagem : this.mensagens) {
            if (mensagem.getIdMensagem().equals(id)) {
                this.mensagens.remove(mensagem);
                return "Mensagem removida com sucesso";
            }
        }
        return "Mensagem não encontrada";
    }
}
