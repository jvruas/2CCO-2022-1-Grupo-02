package com.conture.apiusuario.utility.datastructure;

import com.conture.apiusuario.utility.datastructure.Node;

public class LinkedList<T> {
	private Node<T> head;
	private int size;

	public LinkedList() {
		this.head = new Node(null);
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void add(T element) {
		Node<T> node = new Node(element);

		node.setNext(this.head.getNext());
		this.head.setNext(node);

		this.size++;
	}

	public boolean remove(T element) {
		if (this.isEmpty()) {
			return false;
		}

		Node<T> previousNode = this.head;
		Node<T> currentNode = this.head.getNext();

		while (currentNode != null) {
			if (currentNode.getElement().equals(element)) {
				previousNode.setNext(currentNode.getNext());
				this.size--;

				return true;
			}

			previousNode = currentNode;
			currentNode = currentNode.getNext();
		}

		return false;
	}

	public T get(int index) {
		if (this.isEmpty() || index < 0 || index >= this.size) {
			return null;
		}

		Node<T> currentNode = this.head.getNext();

		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}

		return currentNode.getElement();
	}

	public T get(T element) {
		if (this.isEmpty()) {
			return null;
		}

		Node<T> currentNode = this.head.getNext();

		while (currentNode != null) {
			if (currentNode.getElement().equals(element)) {
				return currentNode.getElement();
			}

			currentNode = currentNode.getNext();
		}

		return null;
	}
}
