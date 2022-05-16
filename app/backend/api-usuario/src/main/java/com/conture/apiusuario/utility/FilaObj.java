package com.conture.apiusuario.utility;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class FilaObj<T> {
	// Atributos
	private int tamanho;
	private T[] fila;

	// Construtor
	public FilaObj(int capacidade) {
		fila = (T[]) new Object[capacidade];
		tamanho = 0;
	}

	// Métodos

	@JsonIgnore
	/* Método isEmpty() - retorna true se a fila está vazia e false caso contrário */
	public boolean isEmpty() {
		if (getTamanho()==0){
			return true;
		}
		return false;
	}

	@JsonIgnore
	/* Método isFull() - retorna true se a fila está cheia e false caso contrário */
	public boolean isFull() {
		if (getTamanho()==fila.length){
			return true;
		}
		return false;
	}

	/* Método insert - recebe um elemento e insere esse elemento na fila
					   no índice tamanho, e incrementa tamanho
					   Retornar IllegalStateException caso a fila esteja cheia
	 */
	public void insert(T info) {
		if (!isFull()) {
			fila[tamanho++] = info;
		} else {
			throw new IllegalStateException();
		}
	}

	/* Método peek - retorna o primeiro elemento da fila, sem removê-lo */
	public T peek() {
		return fila[0];
	}

	/* Método poll - remove e retorna o primeiro elemento da fila, se a fila não estiver
	   vazia. Quando um elemento é removido, a fila "anda", e tamanho é decrementado
	 */
	public T poll() {
		if (!isEmpty()){
			T[] aux = (T[]) new Object[tamanho];
			for (int i = 0; i<tamanho; i++){
				aux[i] = fila[i];
			}
			for (int i = 1; i<tamanho; i++){
				fila[i-1]=aux[i];
			}
			tamanho--;
			return aux[0];
		}
		return null;
	}

	/* Método exibe() - exibe o conteúdo da fila */
	public void exibe() {
		if (!isEmpty()){
			System.out.println(fila);
		}
	}

	@JsonIgnore
	public int getTamanho(){
		return tamanho;
	}

	public T[] getFila() {

		T[] filaReturn = (T[]) new Object[tamanho];
		if(!isEmpty()){
			for (int i = 0; i < getTamanho(); i++) {
				filaReturn[i] = fila[i];
			}
		}
		return filaReturn;
	}
}
