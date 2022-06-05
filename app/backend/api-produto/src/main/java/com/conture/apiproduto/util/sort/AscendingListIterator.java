package com.conture.apiproduto.util.sort;

import java.util.List;

public class AscendingListIterator<T> implements Iterator<T> {
	private List<T> collection;
	private int currentPosition;

	public AscendingListIterator(
			List<T> collection
	) {
		this.collection = collection;
		this.currentPosition = 0;
	}

	@Override
	public boolean hasNext() {
		return this.currentPosition < this.collection.size();
	}

	@Override
	public T getNext() {
		if (!this.hasNext()) {
			return null;
		}

		return this.collection.get(this.currentPosition++);
	}
}
