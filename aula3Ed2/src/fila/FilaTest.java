package fila;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FilaTest {

	@Test
	void testAdicionarFilaVazia() {

		Fila fila = new Fila();

		fila.adicionar("fernando");

		assertEquals(1, fila.pegaTamanho());
		assertTrue(fila.contem("fernando"));

	}

	@Test
	void testAdicionarFilaComElementos() {
		
		Fila fila = new Fila();

		fila.adicionar("fernando");
		fila.adicionar("carlos");
		fila.adicionar("ana");

		assertEquals(3, fila.pegaTamanho());
		assertTrue(fila.contem("fernando"));
		assertTrue(fila.contem("carlos"));
		assertTrue(fila.contem("ana"));
	}
	
	@Test
	void testRemoverFilaVazia() {

		Fila fila = new Fila();

		assertThrows(RuntimeException.class, ()->fila.remover());

	}

	@Test
	void testRemoverFilaComElementos() {
		
		Fila fila = new Fila();

		fila.adicionar("fernando");
		fila.adicionar("carlos");
		fila.adicionar("ana");
		
		fila.remover();

		assertEquals(2, fila.pegaTamanho());
		assertTrue(fila.contem("carlos"));
		assertTrue(fila.contem("ana"));
	}

}
