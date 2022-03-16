package com.example.conture.controller;
import com.example.conture.domain.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    List<Produto> produtos = new ArrayList<>();

    @PostMapping
    public String cadastrar(@RequestBody Produto novoPro){
       produtos.add(novoPro);
       return "Produto cadastrado com sucesso.";
    }
    @GetMapping
    public List<Produto> exibirTodos(){
        return produtos;
    }

    @PutMapping("/{id}")
    public String alterarStatus(@PathVariable int id) {

            for (Produto p : produtos){
                if (p.getIdProduto().equals(id)) {
                    if (p.getStatus().equals(true)) {
                        p.setStatus(false);
                    } else {
                        p.setStatus(true);
                    }
                }
            return "Produto atualizado com sucesso";
        }
        return "Produto não encontrado";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable int id) {

        for (Produto p : produtos) {
            if (p.getIdProduto().equals(id)) {
                produtos.remove(p);
                return "Produto removido com sucesso";
            }
        }
        return "Produto não encontrado";
    }

    @GetMapping("mostrar-id/{id}")
    public Produto exibirPorId(@PathVariable int id) {
        for (Produto p: produtos) {
            if (p.getIdProduto().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @GetMapping("mostrar-categoria/{categoria}")
    public List<Produto> exibirPorCategoria(@PathVariable String categoria) {
        List<Produto> produtosCat = new ArrayList<>();
        for (Produto p: produtos) {
            if (p.getCategoriaProduto().equalsIgnoreCase(categoria)) {
                produtosCat.add(p);
            }
        }
     return produtosCat;
    }

}
