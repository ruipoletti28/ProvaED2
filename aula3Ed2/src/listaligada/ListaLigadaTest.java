package listaligada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ListaLigadaTest {

	@Test
	void testAdicionarNoComecoListaVazia() {

		ListaLigada lista = new ListaLigada();

		lista.adicionaNoComeco("Fernando");

		assertEquals(1, lista.pegaTotalElementos());
		assertEquals("Fernando", lista.pegaPrimeiro());
		assertEquals("Fernando", lista.pegaUltima());
	}

	@Test
	void testAdicionarNoComecoComElementos() {

		ListaLigada lista = new ListaLigada();

		lista.adicionaNoComeco("Fernando");
		lista.adicionaNoComeco("Maria");
		lista.adicionaNoComeco("Carlos");

		assertEquals(3, lista.pegaTotalElementos());
		assertEquals("Carlos", lista.pegaPrimeiro());
		assertEquals("Fernando", lista.pegaUltima());

	}

	@Test
	void testAdicionarNoFinalListaVazia() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");

		assertEquals(1, lista.pegaTotalElementos());
		assertEquals("Fernando", lista.pegaPrimeiro());
		assertEquals("Fernando", lista.pegaUltima());


	}

	@Test
	void testAdicionarNoFinalComElementos() {

		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		lista.adicionaNoFinal("Maria");
		lista.adicionaNoFinal("Carlos");

		assertEquals(3, lista.pegaTotalElementos());
		assertEquals("Fernando", lista.pegaPrimeiro());
		assertEquals("Carlos", lista.pegaUltima());

	}
	
	@Test
	void testPegarNaPosicaoInvalidaNegativa() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		
		assertThrows(IllegalArgumentException.class, ()->lista.pega(-1));
		
	}
	
	@Test
	void testPegarNaPosicaoInvalidaQuantidadeElementos() {
	
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		
		assertThrows(IllegalArgumentException.class, ()->lista.pega(6));
	}
	
	@Test
	void testPegarNaPosicaoValida() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		lista.adicionaNoFinal("Maria");
		lista.adicionaNoFinal("Carlos");

		assertEquals("Fernando", lista.pega(0));
		assertEquals("Maria", lista.pega(1));
		assertEquals("Carlos", lista.pega(2));
		
		
	}
	
	@Test
	void testAdicionarNaPosicaoInvalidaNegativa() {
		
		ListaLigada lista = new ListaLigada();
		
		lista.adicionaNoFinal("Carlos");
		
		assertThrows(IllegalArgumentException.class, ()->lista.adicinaNaPosicao("Fernando", -1));
		
	}
	
	@Test
	void testAdicionarNaPosicaoInvalidaQuantidadeElementos() {
	
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Carlos");
		
		assertThrows(IllegalArgumentException.class, ()->lista.adicinaNaPosicao("Fernando", 2));
		
	}
	
	@Test
	void testAdicionarNaPosicaoValida() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		lista.adicionaNoFinal("Maria");
		lista.adicionaNoFinal("Carlos");

		lista.adicinaNaPosicao("Rose", 1);
		
		assertEquals("Fernando", lista.pega(0));
		assertEquals("Rose", lista.pega(1));
		assertEquals("Maria", lista.pega(2));
		assertEquals("Carlos", lista.pega(3));
		
	}
	
	@Test
	void testContemListaVazia() {
		
		ListaLigada lista = new ListaLigada();
		
		assertFalse(lista.contem("Mauro"));
		
	}
	
	@Test
	void testContemNaoAcharLista() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		lista.adicionaNoFinal("Maria");
		lista.adicionaNoFinal("Carlos");
		
		assertFalse(lista.contem("Mauro"));
		
	}
	
	@Test
	void testContemAcharLista() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		lista.adicionaNoFinal("Maria");
		lista.adicionaNoFinal("Carlos");
		
		assertTrue(lista.contem("Maria"));
		
		
	}
	
	@Test
	void testRemoveDoComecoListaVazia() {

		ListaLigada lista = new ListaLigada();

		assertThrows(RuntimeException.class, ()-> lista.removeDoComeco());
		
	}
	
	@Test
	void testRemoveDoComecoComElementos() {

		ListaLigada lista = new ListaLigada();

		lista.adicionaNoComeco("Fernando");
		lista.adicionaNoComeco("Maria");
		lista.adicionaNoComeco("Carlos");
		
		lista.removeDoComeco();
		
		assertEquals(2, lista.pegaTotalElementos());
		assertEquals("Maria", lista.pegaPrimeiro());
		assertEquals("Fernando", lista.pegaUltima());

	}
	
	@Test
	void testRemoveDoComecoComApenasUmElemento() {

		ListaLigada lista = new ListaLigada();

		lista.adicionaNoComeco("Fernando");
		
		
		lista.removeDoComeco();
		
		assertEquals(0, lista.pegaTotalElementos());
		assertTrue(lista.primeiroEhNulo());
		assertTrue(lista.ultimoEhNulo());

	}
	
	@Test
	void testRemoveDoFinalListaVazia() {

		ListaLigada lista = new ListaLigada();

		assertThrows(RuntimeException.class, ()-> lista.removeDoFinal());
		
	}
	
	@Test
	void testRemoveDoFinalComElementos() {

		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		lista.adicionaNoFinal("Maria");
		lista.adicionaNoFinal("Carlos");
		
		lista.removeDoFinal();
		
		assertEquals(2, lista.pegaTotalElementos());
		assertEquals("Fernando", lista.pegaPrimeiro());
		assertEquals("Maria", lista.pegaUltima());

	}
	
	@Test
	void testRemoveDoFinalComApenasUmElemento() {

		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		
		lista.removeDoFinal();
		
		assertEquals(0, lista.pegaTotalElementos());
		assertTrue(lista.primeiroEhNulo());
		assertTrue(lista.ultimoEhNulo());

	}
	
	@Test
	void testRemoverNaPosicaoInvalidaNegativa() {
		
		ListaLigada lista = new ListaLigada();
		
		lista.adicionaNoFinal("Carlos");
		
		assertThrows(IllegalArgumentException.class, ()->lista.removeNaPosicao(-1));
		
	}
	
	@Test
	void testRemoverNaPosicaoInvalidaQuantidadeElementos() {
	
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Carlos");
		
		assertThrows(IllegalArgumentException.class, ()->lista.removeNaPosicao(2));
		
	}
	
	@Test
	void testRemoverNaPosicaoValida() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");
		lista.adicionaNoFinal("Maria");
		lista.adicionaNoFinal("Carlos");

		lista.removeNaPosicao(1);
		
		assertEquals(2, lista.pegaTotalElementos());
		assertEquals("Fernando", lista.pega(0));
		assertEquals("Carlos", lista.pega(1));
		
	}
	
	@Test
	void testRemoverNaPosicaoValidaApenasUmElemento() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionaNoFinal("Fernando");

		lista.removeNaPosicao(0);
		
		assertEquals(0, lista.pegaTotalElementos());
		assertTrue(lista.primeiroEhNulo());
		assertTrue(lista.ultimoEhNulo());
		
	}

}
