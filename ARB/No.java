package aula08_arvorerubronegra;

public class No {
	
	private int dado;
	private No pai;
	private No direito;
	private No esquerdo;
	private Cor cor;
	
	public No() {
		dado = 0;
		esquerdo = null;
		direito = null;
		pai = null;
		cor = Cor.PRETO;
	}
	
	public No (int dado) {
		this.dado = dado;
		esquerdo = null;
		direito = null;
		pai = null;
		cor = Cor.VERMELHO;
	}
	
	public int getDado() {
		return dado;
	}
	public void setDado(int dado) {
		this.dado = dado;
	}
	public No getPai() {
		return pai;
	}
	public void setPai(No pai) {
		this.pai = pai;
	}
	public No getDireito() {
		return direito;
	}
	public void setDireito(No direito) {
		this.direito = direito;
	}
	public No getEsquerdo() {
		return esquerdo;
	}
	public void setEsquerdo(No esquerdo) {
		this.esquerdo = esquerdo;
	}
	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
	}

}
