package com.conture.apiproduto.utility;

import java.util.Optional;

public interface Iterator<T> {

	public boolean hasNext();

	public T getNext();
}
