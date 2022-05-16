package com.conture.apiproduto.utility;

import java.util.ArrayList;
import java.util.List;

public class PilhaObj<T> {
	private int top;
	private T[] stack;

	public PilhaObj(int lenght) {
		this.top = -1;
		this.stack = (T[]) new Object[lenght];
	}

	public int getTop() {
		return top;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean  isFull(){
		return stack.length -1 == top ;

	}

	public void  push (T obj){
		if(!isFull()){
			stack[++top] = obj;
		}else{
			System.out.println("Pilha cheia");
		}


	}
	public T  pop (){
		if(!isEmpty()){
			return stack[top--];
		}
		return null;
	}

	public T peek(){
		if (!isEmpty()) {
			return stack[top];

		}
		return null;
	}

	public void exibe() {

		if(!isEmpty()){
			for (int i = getTop(); i>=0  ; i--) {
				System.out.println(stack[i]);
			}
		}
	}

	public List<T> transformarEmLista() {
		List<T> lista = new ArrayList<>();
		if (top == 0) {
			return lista;
		}

		for (int i = top; i > -1; i--) {
			lista.add(stack[i]);
		}
		return lista;
	}

}
