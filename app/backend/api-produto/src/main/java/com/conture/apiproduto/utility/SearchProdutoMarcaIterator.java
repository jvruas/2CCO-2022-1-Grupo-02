package com.conture.apiproduto.utility;

import com.conture.apiproduto.entity.ProdutoDoacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchProdutoMarcaIterator<T> implements Iterator<T> {
	private List<T> collection;
	private int iterationState;

	public SearchProdutoMarcaIterator(List<T> collection) throws IllegalArgumentException {
		if (!(collection instanceof  ProdutoDoacao)) {
			throw new IllegalArgumentException();
		}

		this.collection = collection;
		this.iterationState = 0;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Optional<T> getNext() {
		return null;
	}
}
