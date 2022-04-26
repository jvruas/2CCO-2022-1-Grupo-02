package com.conture.apiproduto.utility;

import com.conture.apiproduto.entity.ProdutoDoacao;

import java.util.List;
import java.util.Optional;

public class SearchProdutoCategoriaIterator<T> implements Iterator<T>{
	private List<ProdutoDoacao> collection;
	private int iterationState;
	private Long categoria;

	public SearchProdutoCategoriaIterator(List<T> collection, Long categoria) {
		if (!(collection.get(0) instanceof ProdutoDoacao)) {
			throw new IllegalArgumentException();
		}

		this.collection = (List<ProdutoDoacao>) collection;
		this.categoria = categoria;
		this.iterationState = 0;
	}
	@Override
	public boolean hasNext() {
		 if (this.iterationState == collection.size()) return false;

		 if (this.collection.get(iterationState + 1) == null) return false;

		for (int i = this.iterationState; i < this.collection.size(); i++) {
			ProdutoDoacao produtoDoacao = this.collection.get(i);
			if (produtoDoacao.getFkCategoriaProduto().equals(categoria)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public T getNext() {
		for (int i = this.iterationState; i < this.collection.size(); i++) {
			ProdutoDoacao produtoDoacao = this.collection.get(i);
			if (produtoDoacao.getFkCategoriaProduto().equals(categoria)) {
				return (T) produtoDoacao;
			}
			this.iterationState++;
		}

		return null;
	}

}
