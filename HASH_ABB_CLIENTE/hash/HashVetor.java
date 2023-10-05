package hash;

public class HashVetor {
	
	private static final String TAMANHO_INVALIDO_O_VALOR_DEVE_SER_MAIOR_QUE_ZERO = "Tamanho Invalido. O valor deve ser maior que zero.";
	private int tamanho;
	private TabelaHash tabelaHash;
	
	public HashVetor (int tamanho) {
		
		if (tamanho <= 0) {
			throw new IllegalArgumentException(TAMANHO_INVALIDO_O_VALOR_DEVE_SER_MAIOR_QUE_ZERO);
		}
		
		tabelaHash = new TabelaHash(tamanho);
		this.tamanho = tamanho;
	}
	
	private int calculaHash(int chave) {
		
		String chaveConvertida = String.valueOf(chave);
		int endereco = 0;
		for (int i=0; i < chaveConvertida.length(); i++) {
			endereco = 31 * endereco + chaveConvertida.charAt(i);
		}
		return endereco % tamanho;
	}
	
	public int adicionar (Cliente novoCliente) {
		
		int enderecoAdicionado = calculaHash(novoCliente.getCodigo());
		
		if (tabelaHash.getElementos()[enderecoAdicionado] == null) {
			tabelaHash.getElementos()[enderecoAdicionado] = novoCliente;
		}
		else {
			tabelaHash.getColisoes()[enderecoAdicionado].adicionaNoFinal(novoCliente);
		}
		return enderecoAdicionado;
	}
	
	public void imprimir() {
		
		for (Cliente cliente : tabelaHash.getElementos()) {
			if (cliente != null) {
				int enderecoHash = calculaHash(cliente.getCodigo());
				System.out.println("End. Hash =" + enderecoHash + " - " + cliente.toString());
				imprimirColisoes(enderecoHash);
			}
		}
		
	}

	private void imprimirColisoes(int enderecoHash) {
		
		int tamanhoListaColisoes = tabelaHash.getColisoes()[enderecoHash].pegaTotalElementos();
		for (int i=0; i< tamanhoListaColisoes; i++) {
			Cliente clienteColisao = (Cliente) tabelaHash.getColisoes()[enderecoHash].pega(i);
			System.out.println("End. Hash =" + enderecoHash + " - " + clienteColisao.toString());
		}
		
	}
	
	public Cliente buscarClientePorCodigo (int codigoClienteBuscado) {
		
		int enderecoHashBuscado = calculaHash(codigoClienteBuscado);
		
		if (tabelaHash.getElementos()[enderecoHashBuscado] != null) {
			
			if (tabelaHash.getElementos()[enderecoHashBuscado].getCodigo() == codigoClienteBuscado) {
				return tabelaHash.getElementos()[enderecoHashBuscado];
			}
			else {
				int tamanhoListaColisoes = tabelaHash.getColisoes()[enderecoHashBuscado].pegaTotalElementos();
				for (int i=0; i < tamanhoListaColisoes; i++) {
					Cliente clienteColisao = (Cliente) tabelaHash.getColisoes()[enderecoHashBuscado].pega(i);
					if (clienteColisao.getCodigo() == codigoClienteBuscado) {
						return clienteColisao;
					}
					
				}
				
			}
			
		}
		return null;
	}
	
	
	
	
	
	
	
	
	

}
