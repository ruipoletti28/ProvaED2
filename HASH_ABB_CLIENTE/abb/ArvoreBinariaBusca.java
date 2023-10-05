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
	//CLIENTE
	public No pegarNoArvorePorValor(Cliente clienteBuscado) {
        return buscaClienteNoReferenciaSeusDescendentes(clienteBuscado, raiz);
    }
	//CLIENTE
	private No buscaClienteNoReferenciaSeusDescendentes(Cliente clienteBuscado, No noReferencia) {
        if (noReferencia == null) {
            return null;
        }

        if (clienteBuscado.equals(noReferencia.getCliente())) {
            return noReferencia;
        }

        if (clienteBuscado.compareTo(noReferencia.getCliente()) < 0) {
            return buscaClienteNoReferenciaSeusDescendentes(clienteBuscado, noReferencia.getEsquerdo());
        } else {
            return buscaClienteNoReferenciaSeusDescendentes(clienteBuscado, noReferencia.getDireito());
        }
    }

	public boolean contem(Cliente clienteBuscado) {
        return buscaClienteNoReferenciaSeusDescendentes(clienteBuscado, raiz) != null;
    }
	
	public void adicionaNo(Cliente clienteAdicionado) {
        if (raiz == null) {
            raiz = new No(clienteAdicionado, null, null);
        } else {
            adicionaNoSubNosRecursivamente(clienteAdicionado, raiz);
        }
    }
	
	private void adicionaNoSubNosRecursivamente(Cliente clienteAdicionado, No noReferencia) {
        if (noReferencia != null) {
            if (clienteAdicionado.compareTo(noReferencia.getCliente()) < 0) {
                if (noReferencia.getEsquerdo() == null) {
                    No novoNo = new No(clienteAdicionado, null, null);
                    noReferencia.setEsquerdo(novoNo);
                } else {
                    adicionaNoSubNosRecursivamente(clienteAdicionado, noReferencia.getEsquerdo());
                }
            } else {
                if (noReferencia.getDireito() == null) {
                    No novoNo = new No(clienteAdicionado, null, null);
                    noReferencia.setDireito(novoNo);
                } else {
                    adicionaNoSubNosRecursivamente(clienteAdicionado, noReferencia.getDireito());
                }
            }
        }
    }
	
	public void removeNoPorValor(Cliente clienteRemovido) {
        if (ehVazia()) {
            throw new IllegalArgumentException(OPERACAO_INVALIDA_ARVORE_VAZIA);
        } else {
            raiz = removeNoPorValorRecursivamente(clienteRemovido, raiz);
        }
    }
	
	private No removeNoPorValorRecursivamente(Cliente clienteRemovido, No noReferencia) {
        if (noReferencia == null) {
            return null;
        }

        if (clienteRemovido.equals(noReferencia.getCliente())) {
            // Se for folha
            if (noReferencia.ehFolha()) {
                return null;
            }
            // Se tem 1 filho
            if (temSoFilhoDireito(noReferencia)) {
                return noReferencia.getDireito();
            } else if (temSoFilhoEsquerdo(noReferencia)) {
                return noReferencia.getEsquerdo();
            } else {
                // Dois filhos
                Cliente menorCliente = pegaMenorCliente(noReferencia.getDireito());
                noReferencia.setCliente(menorCliente);
                noReferencia.setDireito(removeNoPorValorRecursivamente(menorCliente, noReferencia.getDireito()));
            }
        } else if (clienteRemovido.compareTo(noReferencia.getCliente()) < 0) {
            noReferencia.setEsquerdo(removeNoPorValorRecursivamente(clienteRemovido, noReferencia.getEsquerdo()));
        } else {
            noReferencia.setDireito(removeNoPorValorRecursivamente(clienteRemovido, noReferencia.getDireito()));
        }
        return noReferencia;
    }
	
	private boolean temSoFilhoDireito(No noReferencia) {
        return (noReferencia.getDireito() != null) && (noReferencia.getEsquerdo() == null);
    }

    private boolean temSoFilhoEsquerdo(No noReferencia) {
        return (noReferencia.getDireito() == null) && (noReferencia.getEsquerdo() != null);
    }
	
	private Cliente pegaMenorCliente(No noReferencia) {
        if (noReferencia.getEsquerdo() == null) {
            return noReferencia.getCliente();
        } else {
            return pegaMenorCliente(noReferencia.getEsquerdo());
        }
    }

	//VERIFICAR SE É ZIG ZAG
	
	public boolean isZigZag() {
		return isZigZag(root, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
	}
	
	private boolean isZigZag(No node, int minValue, int maxValue, boolean isLesser) {
		if (node == null) {
			return true;
		}
	
		// Verifica se o nó atual está dentro dos limites
		if (node.getValor() > maxValue || node.getValor() < minValue) {
			return false;
		}
	
		// Recursivamente verifica os nós filhos, alternando entre "menor" e "maior"
		return (isZigZag(node.getEsquerdo(), minValue, node.getValor(), !isLesser) &&
				isZigZag(node.getDireito(), node.getValor(), maxValue, !isLesser));
	}

}
