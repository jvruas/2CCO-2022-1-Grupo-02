package com.conture.apiproduto.utility;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class PilhaObj<T> {
	private int top;
	private T[] stack;

	public PilhaObj(int lenght) {
		this.top = -1;
		this.stack = (T[]) new Object[lenght];
	}

	@JsonIgnore
	public int getTop() {
		return top;
	}

	@JsonIgnore
	public boolean isEmpty() {
		return top == -1;
	}

	@JsonIgnore
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

	public T[] getPilha() {

		T[] pilhaReturn = (T[]) new Object[top + 1];
		if(!isEmpty()){
			int idx = 0;
			for (int i = getTop(); i>=0; i--) {
				pilhaReturn[idx++] = stack[i];
			}
		}
		return pilhaReturn;
	}


}
