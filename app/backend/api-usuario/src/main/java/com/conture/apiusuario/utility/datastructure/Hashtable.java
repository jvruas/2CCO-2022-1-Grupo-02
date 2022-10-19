package com.conture.apiusuario.utility.datastructure;

import com.conture.apiusuario.utility.datastructure.LinkedList;

public class Hashtable<T> {
	private LinkedList<T>[] table;

	public Hashtable(int hashtableSize) {
		this.table = new LinkedList[hashtableSize];
		this.setup();
	}

	private void setup() {
		for (int i = 0; i < this.table.length; i++) {
			this.table[i] = new LinkedList();
		}
	}

	public boolean isEmpty() {
		for (int i = 0; i < this.table.length; i++) {
			if (!this.table[i].isEmpty()) {
				return false;
			}
		}

		return true;
	}

	private int getHashCode(T element) {
		return element.hashCode() % this.table.length;
	}

	public void add(T element) {
		this.table[this.getHashCode(element)].add(element);
	}

	public T get(T element) {
		return this.table[this.getHashCode(element)].get(element);
	}

	public boolean remove(T element) {
		return this.table[this.getHashCode(element)].remove(element);
	}
}
