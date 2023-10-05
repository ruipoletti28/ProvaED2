package aula08_arvorerubronegra;

public class ArvoreRN {

	private static final String ERRO_AO_INSERIR_VALOR = "Erro ao inserir. Valor ja existe na arvore.";
	private No raiz;
	private No nulo;

	public ArvoreRN() {
		nulo = new No();
		raiz = nulo;
	}

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public No getNulo() {
		return nulo;
	}

	public void setNulo(No nulo) {
		this.nulo = nulo;
	}

	void rotacaoEsquerda(No noReferenciaX) {

		No noReferenciaY = noReferenciaX.getDireito();

		noReferenciaX.setDireito(noReferenciaY.getEsquerdo());
		if (noReferenciaY.getEsquerdo() != nulo) {
			noReferenciaY.getEsquerdo().setPai(noReferenciaX);
		}

		noReferenciaY.setPai(noReferenciaX.getPai());

		if (noReferenciaX.getPai() == nulo) {
			raiz = noReferenciaY;
		} else {
			if (noReferenciaX == noReferenciaX.getPai().getEsquerdo()) {
				noReferenciaX.getPai().setEsquerdo(noReferenciaY);
			} else {
				noReferenciaX.getPai().setDireito(noReferenciaY);
			}
		}
		noReferenciaY.setEsquerdo(noReferenciaX);
		noReferenciaX.setPai(noReferenciaY);
	}

	void rotacaoDireita(No noReferenciaY) {

		No noReferenciaX = noReferenciaY.getEsquerdo();

		noReferenciaY.setEsquerdo(noReferenciaX.getDireito());
		if (noReferenciaX.getDireito() != nulo) {
			noReferenciaX.getDireito().setPai(noReferenciaY);
		}

		noReferenciaX.setPai(noReferenciaY.getPai());

		if (noReferenciaY.getPai() == nulo) {
			raiz = noReferenciaX;
		} else {
			if (noReferenciaY == noReferenciaY.getPai().getEsquerdo()) {
				noReferenciaY.getPai().setEsquerdo(noReferenciaX);
			} else {
				noReferenciaY.getPai().setDireito(noReferenciaX);
			}
		}
		noReferenciaX.setDireito(noReferenciaY);
		noReferenciaY.setPai(noReferenciaX);
	}

	void inserir(int novoValor) {

		No noAnterior = nulo;
		No noAtual = raiz;

		while (noAtual != nulo) {

			noAnterior = noAtual;

			if (novoValor < noAtual.getDado()) {
				noAtual = noAtual.getEsquerdo();
			} else if (novoValor > noAtual.getDado()) {
				noAtual = noAtual.getDireito();
			} else {
				throw new IllegalArgumentException(ERRO_AO_INSERIR_VALOR);
			}
		}

		No novoNo = new No(novoValor);
		novoNo.setPai(noAnterior);
		novoNo.setDireito(nulo);
		novoNo.setEsquerdo(nulo);
		novoNo.setCor(Cor.VERMELHO);

		if (raiz == nulo) {
			raiz = novoNo;
		} else {
			if (novoValor < noAnterior.getDado()) {
				noAnterior.setEsquerdo(novoNo);
			} else {
				noAnterior.setDireito(novoNo);
			}
		}

		garantirPropriedadesRubroNegras(novoNo);
	}

	private void garantirPropriedadesRubroNegras(No noReferenciaX) {

		No noReferenciaU; // tio

		while (noReferenciaX.getPai().getCor() == Cor.VERMELHO) {

			if (noReferenciaX.getPai() == noReferenciaX.getPai().getPai().getEsquerdo()) {
				noReferenciaU = noReferenciaX.getPai().getPai().getDireito();
				noReferenciaX = aplicarCasosRecolorirRotacionar(noReferenciaX, noReferenciaU); //caso 1, 2 e 3
			} else {
				noReferenciaU = noReferenciaX.getPai().getPai().getEsquerdo();
				noReferenciaX = aplicarCasosRecolorirRotacionarEspelho(noReferenciaX, noReferenciaU); // caso 1, 4, 5
			}
		}

		raiz.setCor(Cor.PRETO);
	}

	private No aplicarCasosRecolorirRotacionarEspelho(No noReferenciaX, No noReferenciaU) {
		
		if (noReferenciaU.getCor() == Cor.VERMELHO) {  
			noReferenciaU.setCor(Cor.PRETO);					//caso 1
			noReferenciaX.getPai().setCor(Cor.PRETO);
			noReferenciaX.getPai().getPai().setCor(Cor.VERMELHO);
			noReferenciaX = noReferenciaX.getPai().getPai();
		}
		else {  // tio preto , pai direito
			
			if (noReferenciaX == noReferenciaX.getPai().getEsquerdo()) {  // caso 4
				noReferenciaX = noReferenciaX.getPai();
				rotacaoDireita(noReferenciaX);
			}
			noReferenciaX.getPai().setCor(Cor.PRETO);                 //caso 5
			noReferenciaX.getPai().getPai().setCor(Cor.VERMELHO);
			rotacaoEsquerda(noReferenciaX.getPai().getPai());
			
		}
		
		return noReferenciaX;
	}

	private No aplicarCasosRecolorirRotacionar(No noReferenciaX, No noReferenciaU) {
		
		if (noReferenciaU.getCor() == Cor.VERMELHO) {  
			noReferenciaU.setCor(Cor.PRETO);					//caso 1
			noReferenciaX.getPai().setCor(Cor.PRETO);
			noReferenciaX.getPai().getPai().setCor(Cor.VERMELHO);
			noReferenciaX = noReferenciaX.getPai().getPai();
		}
		else {  // tio preto , pai esquerdo
			
			if (noReferenciaX == noReferenciaX.getPai().getDireito()) {  // caso 2
				noReferenciaX = noReferenciaX.getPai();
				rotacaoEsquerda(noReferenciaX);
			}
			noReferenciaX.getPai().setCor(Cor.PRETO);                 //caso 3
			noReferenciaX.getPai().getPai().setCor(Cor.VERMELHO);
			rotacaoDireita(noReferenciaX.getPai().getPai());
			
		}
		
		return noReferenciaX;
	}
	
	public void imprimirInOrder() {
		percorrerInOrder(raiz, "   ");
	}

	private void percorrerInOrder(No noReferencia, String espaco) {
		
		if (noReferencia != null) {
			percorrerInOrder(noReferencia.getEsquerdo(), "     " + espaco);
			System.out.println(espaco + noReferencia.getDado() + "," + noReferencia.getCor() );
			percorrerInOrder(noReferencia.getDireito(), "     " + espaco);
			
		}
		
	}
	
	public No buscarNoPorValor (int valorBuscado) {
		
		No noAtual = raiz;
		
		while (noAtual != null) {
			if (valorBuscado == noAtual.getDado()) {
				return noAtual;
			}
			else if (valorBuscado < noAtual.getDado()) {
				noAtual = noAtual.getEsquerdo();
			}
			else {
				noAtual = noAtual.getDireito();
			}	
		}
		
		return nulo;
		
	}
	
	
	
	
	
	
	
	
	
	


}
