package aula08_arvorerubronegra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArvoreRNTest {

	@Test
	void testImprimir() {
		
		ArvoreRN arvore = new ArvoreRN();
		
		arvore.inserir(34);
		arvore.inserir(3);
		arvore.inserir(50);
		arvore.inserir(20);
		arvore.inserir(15);
		arvore.inserir(16);
		arvore.inserir(25);
		arvore.inserir(27);
		
		assertEquals(15, arvore.buscarNoPorValor(15).getDado());
		
		// arvore.imprimirInOrder();
	
	}

}
