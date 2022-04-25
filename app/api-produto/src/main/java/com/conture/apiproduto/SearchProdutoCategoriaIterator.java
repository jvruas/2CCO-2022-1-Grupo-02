package com.conture.apiproduto;

import com.conture.apiproduto.entity.ProdutoDoacao;
import org.springframework.context.annotation.Profile;

public class SearchProdutoCategoriaIterator implements Iterator{
	private ListaObj<ProdutoDoacao> collection;

	private int iterationState;

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Profile getNext() {
		return null;
	}



}
