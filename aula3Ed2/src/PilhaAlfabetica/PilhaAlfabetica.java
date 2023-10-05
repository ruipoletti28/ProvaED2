package PilhaAlfabetica;

import listaligada.ListaLigada;
import pilha.Pilha;

public class PilhaAlfabetica {
	
	private ListaLigada pilha = new ListaLigada();
	
	public void push (Object novoElemento) {
		if (pilha.ehVazia() || novoElemento.toString().compareTo(pilha.pegaUltima().toString()) <= 0) {
            pilha.adicionaNoFinal(novoElemento);
        } else {
        	ListaLigada temp = new ListaLigada();
            while (!pilha.ehVazia() && novoElemento.toString().compareTo(pilha.pegaUltima().toString()) > 0) {
                temp.adicionaNoFinal(pilha.removeDoFinal());
            }
            pilha.adicionaNoFinal(novoElemento);
            while (!temp.ehVazia()) {
                pilha.adicionaNoFinal(temp.push().getValor()); // Correção aqui
            }
        }
	}
	
	public void pop() {
		pilha.removeDoFinal();
	}
	
	public Object top() { 
		return pilha.pegaUltima();
	}
	
	public int pegaTamanho() {
		return pilha.pegaTotalElementos();
	}
	
	public boolean contem(Object elementoBuscado) {
		return pilha.contem(elementoBuscado);
	}
	
}
