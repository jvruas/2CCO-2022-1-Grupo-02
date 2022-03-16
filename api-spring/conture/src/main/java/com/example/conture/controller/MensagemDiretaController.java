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

        return "Cadastrado com sucesso";
    }

    @GetMapping
    public List<MensagemDireta> exibirTodos() {
        return this.mensagens;
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
    public MensagemDireta exibirPorID(@PathVariable int id) {
        for (MensagemDireta m : mensagens) {
            if (m.getIdMensagem().equals(id)) {
                return m;
            }
        }
        return null;
    }

}
