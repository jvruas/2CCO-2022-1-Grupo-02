package com.conture.apiproduto.util;

import com.conture.apiproduto.model.entity.ProdutoDoacao;

import java.util.List;

public class SearchProdutoMarcaIterator<T> implements Iterator<T> {
	private List<ProdutoDoacao> collection;
	private int iterationState;
	private String marca;

	public SearchProdutoMarcaIterator(List<T> collection, String marca)  {
		if (!(collection.get(0) instanceof  ProdutoDoacao)) {
			throw new IllegalArgumentException();
		}
		this.collection = (List<ProdutoDoacao>) collection;
		this.marca = marca;
		this.iterationState = 0;
	}

	@Override
	public boolean hasNext() {
		return this.iterationState < this.collection.size();
	}

	@Override
	public T getNext() {
		for (int i = this.iterationState; i < this.collection.size(); i++) {
			this.iterationState++;

			ProdutoDoacao produtoDoacao = this.collection.get(i);
			if (produtoDoacao.getMarca().equals(this.marca)) {
				return (T) produtoDoacao;
			}
		}
		return null;
	}
}
