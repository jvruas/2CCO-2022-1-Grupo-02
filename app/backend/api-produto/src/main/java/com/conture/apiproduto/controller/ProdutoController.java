package com.conture.apiproduto.controller;

import com.conture.apiproduto.entity.MatchEntity;
import com.conture.apiproduto.entity.PreferenciaDonatarioEntity;
import com.conture.apiproduto.entity.ProdutoDoacaoEntity;
import com.conture.apiproduto.repositorio.CategoriaProdutoRepository;
import com.conture.apiproduto.repositorio.MatchRepository;
import com.conture.apiproduto.repositorio.PreferenciaDonatarioRepository;
import com.conture.apiproduto.repositorio.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/produtos")
public class
ProdutoController {

        @Autowired
        private ProdutoRepository produtoRepository;

        @Autowired
        private PreferenciaDonatarioRepository preferenciaRepository;

        @Autowired
        private MatchRepository matchRepository;

         @Autowired
         private CategoriaProdutoRepository categoriaRepository;

        @PostMapping("/adicionar-produto")
        public ResponseEntity adicionarProduto(@RequestBody @Valid ProdutoDoacaoEntity produto) {
            produtoRepository.save(produto);
            return ResponseEntity.status(201).build();
        }

        @PostMapping("/adicionar-preferencia")
        public ResponseEntity adicionarPreferenciaDonatario(@RequestBody @Valid PreferenciaDonatarioEntity preferencia){
            preferenciaRepository.save(preferencia);
            return ResponseEntity.status(201).build();
        }

        @PostMapping("/adicionar-match")
        public ResponseEntity adicionarMatch(@RequestBody @Valid MatchEntity match){
            matchRepository.save(match);
            return  ResponseEntity.status(201).build();
        }
        @GetMapping("/pesquisar/{idProduto}/{idDoador}")
        public ResponseEntity pesquisarProdutoId(@PathVariable Long idProduto, @PathVariable Long idDoador){
          ProdutoDoacaoEntity lista =  produtoRepository.findByProdutoAndDoador(idProduto,idDoador);
            return ResponseEntity.status(200).body(lista);

        }

        @GetMapping("/listar-categorias")
        public  ResponseEntity listarCategorias(){
            List<ProdutoDoacaoEntity> lista = produtoRepository.findByCategoriaNotEquals();
            return  ResponseEntity.status(201).body(lista);
        }

        @GetMapping("/listar-marca/{categoria}")
        public ResponseEntity listarProdutoMarca(@PathVariable String categoria){
           ProdutoDoacaoEntity lista = produtoRepository.findByMarca(categoria);
            return ResponseEntity.status(201).body(lista);
        }

        @GetMapping("/listar-nome/{nome}")
        public ResponseEntity listarProdutoNome(@PathVariable String nome){
            ProdutoDoacaoEntity lista = produtoRepository.findByNome(nome);
            return  ResponseEntity.status(201).body(lista);
        }

        @GetMapping("/listar-produto-doador/{idDoador}/{status}")
        public ResponseEntity listarStatusProdutoDoador(@PathVariable int idDoador, @PathVariable String status){
            ProdutoDoacaoEntity lista = produtoRepository.findByIdDoadorandStatus(idDoador, status);
            return  ResponseEntity.status(201).body(lista);
        }


    @GetMapping("/listar-produto-donatario/{idDonatario}/{status}")
    public ResponseEntity listarStatusProdutoDonatario(@PathVariable int idDonatario, @PathVariable String status){
        ProdutoDoacaoEntity lista = produtoRepository.findByIdDonatarioandStatus(idDonatario, status);
        return  ResponseEntity.status(201).body(lista);
    }

    @GetMapping("/")
    public ResponseEntity

}
