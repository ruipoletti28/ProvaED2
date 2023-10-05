

public class ArvoreBinariaBusca {

	private static final String OPERACAO_INVALIDA_ARVORE_VAZIA = "Operacao invalida: arvore vazia";
	private No raiz;
	
	public ArvoreBinariaBusca() {
		raiz = null;
	}

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public boolean ehVazia() {
		return raiz == null;
	}

	public int pegaQuantidadeNos() {
		return pegaQuantidadeNosDoSubNo(raiz);
	}

	public int pegaQuantidadeNosDoSubNo(No noReferencia) {
		if (noReferencia == null) {
			return 0;
		} else {
			return pegaQuantidadeNosDoSubNo(noReferencia.getEsquerdo()) 
					+ 1 
					+ pegaQuantidadeNosDoSubNo(noReferencia.getDireito());
		}
	}

	public int pegaAlturaDoNo(No noReferencia) {
		if (ehVazia()) {
			return 0;
		} else {
			return pegaAlturaDoSubNo(noReferencia) - 1;
		}
	}

	private int pegaAlturaDoSubNo(No noReferencia) {
		if (noReferencia == null) {
			return 0;
		}
		int alturaDireita = pegaAlturaDoSubNo(noReferencia.getDireito()); // 2
		int alturaEsquerda = pegaAlturaDoSubNo(noReferencia.getEsquerdo()); // 0

		return 1 + Math.max(alturaDireita, alturaEsquerda);
	}

	public int pegaAlturaDaArvore() {
		return pegaAlturaDoNo(raiz);
	}

	public int pegaProfundidadeDoNo(No noReferencia) {

		if (ehVazia()) {
			return 0;
		} else {
			return pegaProfundidadeDoNoAteRaiz(raiz, noReferencia) - 1;
		}
	}

	private int pegaProfundidadeDoNoAteRaiz(No noReferencia, No noBuscado) {
		if (noReferencia == null) {
			return 0;
		} else {
			if (noBuscado.getValor() == noReferencia.getValor()) {
				return 1;
			}

			if (noBuscado.getValor() < noReferencia.getValor()) {
				return 1 + pegaProfundidadeDoNoAteRaiz(noReferencia.getEsquerdo(), noBuscado);
			} else {
				return 1 + pegaProfundidadeDoNoAteRaiz(noReferencia.getDireito(), noBuscado);
			}
		}
	}

	public No pegarNoArvorePorValor(int valorBuscado) {

		return buscaValorNoReferenciaSeusDescendentes(valorBuscado, raiz);
	}

	private No buscaValorNoReferenciaSeusDescendentes(int valorBuscado, No noReferencia) {

		if (noReferencia == null) {
			return null;
		}

		if (valorBuscado == noReferencia.getValor()) {
			return noReferencia;
		}

		if (valorBuscado < noReferencia.getValor()) {
			return buscaValorNoReferenciaSeusDescendentes(valorBuscado, noReferencia.getEsquerdo());
		} else {
			return buscaValorNoReferenciaSeusDescendentes(valorBuscado, noReferencia.getDireito());
		}
	}

	public boolean contem(int valorBuscado) {

		return buscaValorNoReferenciaSeusDescendentes(valorBuscado, raiz) != null;
	}
	
	public void adicionaNo(int valorAdicionado) {
		if (raiz == null) {
			raiz = new No (valorAdicionado, null, null);
		}
		else {
			adicionaNoSubNosRecursivamente(valorAdicionado, raiz);
		}
	}
	
	private void adicionaNoSubNosRecursivamente(int valorAdicionado, No noReferencia) {
		
		
		if (noReferencia != null) {
			
			if (valorAdicionado < noReferencia.getValor()) {
				
				if (noReferencia.getEsquerdo() == null) {
					No novoNo = new No(valorAdicionado, null, null);
					noReferencia.setEsquerdo(novoNo);
				}
				else {
					adicionaNoSubNosRecursivamente(valorAdicionado, noReferencia.getEsquerdo());
				}
			}
			else {
				
				if (noReferencia.getDireito() == null) {
					No novoNo = new No(valorAdicionado, null, null);
					noReferencia.setDireito(novoNo);
				}
				else {
					adicionaNoSubNosRecursivamente(valorAdicionado, noReferencia.getDireito());
				}
			}	
		}
		
	}
	
	public void removeNoPorValor(int valorRemovido) {
		
		if (ehVazia()) {
			throw new IllegalArgumentException(OPERACAO_INVALIDA_ARVORE_VAZIA);
		}
		else {
			raiz = removeNoPorValorRecursivamente(valorRemovido, raiz);
		}	
	}
	
	private No removeNoPorValorRecursivamente(int valorRemovido, No noReferencia) {
		
		if (noReferencia == null) {
			return null;
		}
		
		if (valorRemovido == noReferencia.getValor()) {
			
			//se for folha
			if (noReferencia.ehFolha()) {
				return null;
			}
			//se tem 1 filhos
			if (temSoFilhoDireito(noReferencia)) {
				return noReferencia.getDireito();
			}
			else if (temSoFilhoEsquerdo(noReferencia)) {
				return noReferencia.getEsquerdo();
			}
			else {
			//dois filhos
				int menorValor = pegaMenorValor(noReferencia.getDireito());
				noReferencia.setValor(menorValor);
				noReferencia.setDireito(removeNoPorValorRecursivamente(menorValor, noReferencia.getDireito()));
				
			}
			
		}
		else if (valorRemovido < noReferencia.getValor()){
			noReferencia.setEsquerdo(removeNoPorValorRecursivamente(valorRemovido, noReferencia.getEsquerdo()));
		}
		else {
			noReferencia.setDireito(removeNoPorValorRecursivamente(valorRemovido, noReferencia.getDireito()));
		}
		return noReferencia;
		
	}
	
	private boolean temSoFilhoDireito(No noReferencia) {
		return ((noReferencia.getDireito() != null) && (noReferencia.getEsquerdo() == null));
	}
	
	private boolean temSoFilhoEsquerdo(No noReferencia) {
		return ((noReferencia.getDireito() == null) && (noReferencia.getEsquerdo() != null));
	}
	
	private int pegaMenorValor(No noReferencia) {
		
		if (noReferencia.getEsquerdo() == null) {
			return noReferencia.getValor();
		}
		else {
			return pegaMenorValor(noReferencia.getEsquerdo());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
