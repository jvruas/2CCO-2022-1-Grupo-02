package com.example.contureproduto.controle;

import com.example.contureproduto.entidade.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    List<Produto> produtos = new ArrayList<>();

    public boolean idValido(Integer id)
    {
        return id >= 0 && id < produtos.size();
    }

    @PostMapping
    public String cadastrar(@RequestBody Produto novoPro){
       produtos.add(novoPro);
       return "Produto cadastrado com sucesso.";
    }

    @PutMapping("/{indice}")
    public String alterarStatus(@PathVariable int id, @PathVariable boolean status) {
        if (idValido(id)){
            for (Produto p : produtos){
                p.setStatus(status);
            }
            return "Produto atualizado com sucesso";
        }
        return "Produto não encontrado";
    }


    @DeleteMapping("/{indice}")
    public String removerPorIndice(@PathVariable Integer id) {
        if (idValido(id)){
            produtos.remove(id);
            return "Produto removido com sucesso";
        }
        return "Produto não encontrado";
    }

@GetMapping("/exibir")
    public List<Produto> exibirLista(){
        return produtos;
}

    @GetMapping("/exibir/{id}")
    public Produto exibirId(@PathVariable Integer id) {
        for (Produto h: produtos) {
            if (!(produtos.get(id).equals(id))) {
                return null;
            }
        }
        return produtos.get(id);
    }

    @GetMapping("/exibir/{categoria}")
    public List<Produto> exibirCategoria(@PathVariable String categoria) {
        for (Produto h: produtos) {
            if (h.getCategoriaProduto().equals(categoria)) {
                return produtos;
            }
        }
     return null;
    }









}
