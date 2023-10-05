package hash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashVetorTest {

	private HashVetor hash;
	
	@BeforeEach
	void inicializaHash() {
		hash = new HashVetor(10);
	}
	
	@Test
	void criarHashComTamanhoInvalido() {
		
		assertThrows(IllegalArgumentException.class, ()-> new HashVetor(0));
		assertThrows(IllegalArgumentException.class, ()-> new HashVetor(-5));
	}
	
	
	
	@Test
	void adicionarSemColisao() {
		
		Cliente cliente1 = new Cliente(22, "Fernando", "Rua das Coves, 23");
		Cliente cliente2 = new Cliente(7, "Maria", "Avenida Brasil, 100");
		
		assertEquals(0, hash.adicionar(cliente1));
		assertEquals(5, hash.adicionar(cliente2));
		
		assertEquals(22, hash.buscarClientePorCodigo(22).getCodigo());
		assertEquals(7, hash.buscarClientePorCodigo(7).getCodigo());
		
	}
	
	@Test
	void adicionarComColisao() {
		
		Cliente cliente1 = new Cliente(22, "Fernando", "Rua das Coves, 23");
		Cliente cliente2 = new Cliente(7, "Maria", "Avenida Brasil, 100");
		
		assertEquals(0, hash.adicionar(cliente1));
		assertEquals(5, hash.adicionar(cliente2));
		
		
		Cliente cliente3 = new Cliente (3645, "Andre", "Rua XV de Novembro, 33");
		Cliente cliente4 = new Cliente (511, "Rose", "Av Dos Cravos, 122");
		Cliente cliente5 = new Cliente (123, "Marcos", "Sem teto");
		
		assertEquals(0, hash.adicionar(cliente3));
		assertEquals(1, hash.adicionar(cliente4));
		assertEquals(0, hash.adicionar(cliente5));
		
		
		assertEquals(22, hash.buscarClientePorCodigo(22).getCodigo());
		assertEquals(7, hash.buscarClientePorCodigo(7).getCodigo());
		assertEquals(3645, hash.buscarClientePorCodigo(3645).getCodigo());
		assertEquals(511, hash.buscarClientePorCodigo(511).getCodigo());
		assertEquals(123, hash.buscarClientePorCodigo(123).getCodigo());
		
		
	}
	
	
	
	
	
	
	
	

}
