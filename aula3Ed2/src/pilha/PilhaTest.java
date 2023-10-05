package pilha;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fila.Fila;

class PilhaTest {

	@Test
	void testPushPilhaVazia() {

		Pilha pilha = new Pilha();

		pilha.push("fernando");

		assertEquals(1, pilha.pegaTamanho());
		assertTrue(pilha.contem("fernando"));

	}

	@Test
	void testPushPilhaComElementos() {

		Pilha pilha = new Pilha();

		pilha.push("fernando");
		pilha.push("marcos");
		pilha.push("ana");

		assertEquals(3, pilha.pegaTamanho());
		assertTrue(pilha.contem("fernando"));
		assertTrue(pilha.contem("marcos"));
		assertTrue(pilha.contem("ana"));
		assertEquals("ana", pilha.top());
		
	}
	
	@Test
	void testPopPilhaVazia() {

		Pilha pilha = new Pilha();
		assertThrows(RuntimeException.class, ()-> pilha.pop());
		
	}

	@Test
	void testPopPilhaComElementos() {

		Pilha pilha = new Pilha();

		pilha.push("fernando");
		pilha.push("marcos");
		pilha.push("ana");
		
		pilha.pop();

		assertEquals(2, pilha.pegaTamanho());
		assertTrue(pilha.contem("fernando"));
		assertTrue(pilha.contem("marcos"));
		assertEquals("marcos", pilha.top());
		
	}

}
