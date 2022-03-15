package com.example.conture.controller;

import com.example.conture.domain.MensagemGrupo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mensagem-grupo")
public class MensagemGrupoController {

        List<MensagemGrupo> mensagens = new ArrayList<>();


        @PostMapping
        public String cadastrar(@RequestBody MensagemGrupo mensagem) {
            mensagens.add(mensagem);
            return "Mensagem cadastrada com sucesso";
        }

        @GetMapping
        public List<MensagemGrupo> exibirTodos() {
            return mensagens;
        }

        @DeleteMapping("/{id}")
        public String deletar(@PathVariable int id) {
            for (MensagemGrupo m : mensagens) {
                if (m.getIdMensagem().equals(id)) {
                    mensagens.remove(m);
                    return "Mensagem removida com sucesso";
                }
            }
            return "Mensagem não encontrada";
        }


        @GetMapping("/{id}")
        public MensagemGrupo exibirPorID(@PathVariable int id) {
            for (MensagemGrupo m : mensagens) {
                if (m.getIdMensagem().equals(id)) {
                    return m;
                }
            }
            return null;
        }

    }
