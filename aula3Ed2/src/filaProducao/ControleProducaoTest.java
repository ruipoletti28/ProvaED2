package filaProducao;

import org.junit.Test;
import static org.junit.Assert.*;

public class ControleProducaoTest {

    @Test
    public void testAdicionarPecasNaPrimeiraMaquina() {
        ControleProducao controle = new ControleProducao();

        controle.adicionarPecasNaMaquina(1, 5);

        assertEquals(5, controle.getPecasNaMaquina(1).size());
        assertEquals(0, controle.getPecasNaMaquina(2).size());
        assertEquals(0, controle.getPecasNaMaquina(3).size());
    }

    @Test
    public void testProcessarPecasEmMaquina() {
        ControleProducao controle = new ControleProducao();

        controle.adicionarPecasNaMaquina(1, 3);
        controle.processarMaquina(1);

        assertEquals(0, controle.getPecasNaMaquina(1).size());
        assertEquals(3, controle.getPecasNaMaquina(2).size());
        assertEquals(0, controle.getPecasNaMaquina(3).size());
    }

    @Test
    public void testProcessoCompleto() {
        ControleProducao controle = new ControleProducao();

        controle.adicionarPecasNaMaquina(1, 4);
        controle.processarMaquina(1);
        controle.processarMaquina(2);
        controle.processarMaquina(3);

        assertEquals(0, controle.getPecasNaMaquina(1).size());
        assertEquals(0, controle.getPecasNaMaquina(2).size());
        assertEquals(0, controle.getPecasNaMaquina(3).size());
    }
}
