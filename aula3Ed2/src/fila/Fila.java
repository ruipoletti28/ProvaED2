package fila;

import listaligada.ListaLigada;

public class Fila  {
	
	private ListaLigada fila = new ListaLigada();
	
	public void adicionar(Object novoElemento) {
		fila.adicionaNoFinal(novoElemento);
	}
	
	public void remover() {
		fila.removeDoComeco();
	}
	
	public boolean ehVazia() {
		return fila.ehVazia();
	}
	
	public Object poll() {
		if (ehVazia()) {
			return null;
		}
		else {
			Object primeiro_fila = fila.pegaPrimeiro();
			fila.removeDoComeco();
			return primeiro_fila;
		}
	}
	
	public boolean contem(Object elementoBuscado) {
		return fila.contem(elementoBuscado);
	}
	
	public int pegaTamanho() {
		return fila.pegaTotalElementos();
	}
	
}






