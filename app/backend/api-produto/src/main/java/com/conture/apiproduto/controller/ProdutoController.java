package com.conture.apiproduto.controller;

import com.conture.apiproduto.ListaObj;
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

		 ListaObj<ProdutoDoacaoEntity> lista = new ListaObj<>(10);

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
        @GetMapping("/pesquisar/{idProdutoDoacao}/{fkDoador}")
        public ResponseEntity pesquisarProdutoId(@PathVariable int idProdutoDoacao, @PathVariable int fkDoador){
          ProdutoDoacaoEntity lista =  produtoRepository.findByProdutoDoacaoAndDoador(idProdutoDoacao,fkDoador);
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

        @GetMapping("/listar-produto-doador/{fkDoador}/{status}")
        public ResponseEntity listarStatusProdutoDoador(@PathVariable int fkDoador, @PathVariable String status){
            ProdutoDoacaoEntity lista = produtoRepository.findByfkDoadorandStatus(fkDoador, status);
            return  ResponseEntity.status(201).body(lista);
        }


    @GetMapping("/listar-produto-donatario/{idDonatario}/{status}")
    public ResponseEntity listarStatusProdutoDonatario(@PathVariable int idDonatario, @PathVariable String status){
        ProdutoDoacaoEntity lista = produtoRepository.findByIdDonatarioandStatus(idDonatario, status);
        return  ResponseEntity.status(201).body(lista);
    }

    @GetMapping("/listar-match/{fkDoador}/{idProdutoDoacao}")
    public ResponseEntity listarMatch(@PathVariable int fkDoador, @PathVariable int idProdutoDoacao){
			ProdutoDoacaoEntity lista = produtoRepository.findByMatch(fkDoador,idProdutoDoacao);
			return  ResponseEntity.status(201).body(lista);
	}

	@GetMapping("/contar-match/{fkDoador}/{idProdutoDoacao}")
	public ResponseEntity contarMatch(@PathVariable int fkDoador, @PathVariable int idProdutoDoacao){
		int contador = produtoRepository.countByMatch(fkDoador, idProdutoDoacao);
		return  ResponseEntity.status(201).body(contador);
	}

	@DeleteMapping("/deletarProduto/{idProdutoDoacao}/{fkDoador}")
	public ResponseEntity deletarProduto(@PathVariable int idProdutoDoacao,@PathVariable int fkDoador){
			ProdutoDoacaoEntity produto = produtoRepository.deleteByFkDoadorAndProdutoDoacao(idProdutoDoacao, fkDoador);
			produtoRepository.save(produto);
			return ResponseEntity.status(201).build();

	}

	//@PatchMapping("/atualizarStatusDoador/")----------

	@DeleteMapping("/deletarMatch/{idProdutoDoacao}/{fkDonatario}/{fkDoador}")
	public ResponseEntity deletarMatch(@PathVariable int idProdutoDoacao,@PathVariable int fkDonatario,@PathVariable int fkDoador){
		ProdutoDoacaoEntity match = produtoRepository.deleteByMatch(idProdutoDoacao, fkDonatario,fkDoador);
		produtoRepository.save(match);
		return ResponseEntity.status(201).build();
	}

	@PutMapping("/atualizar-quantidade-visualizacao/{idProdutoDoacao}/{fkDoador}")
	public ResponseEntity atualizarQuantidadeVisualizacao(@PathVariable int idProdutoDoacao,@PathVariable int fkDoador){
			int visualizacao = produtoRepository.countByVisualizacao(idProdutoDoacao,fkDoador);
			visualizacao++;
			produtoRepository.save(visualizacao);
			return ResponseEntity.status(201).build();
	}

//	@PutMapping("atualizar-match")
//	public ResponseEntity atualizarMatch(){
//
//	}


}

