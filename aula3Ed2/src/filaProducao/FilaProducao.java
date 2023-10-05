package filaProducao;

import java.util.NoSuchElementException;

public class FilaProducao  {
	
	private ListaLigada fila 	 = new ListaLigada();
	private ListaLigada maquina1 = new ListaLigada();
	private ListaLigada maquina2 = new ListaLigada();
	private ListaLigada maquina3 = new ListaLigada();
	
	private int pecaId = 1;

    public void adicionarPecasNaMaquina1(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Peca peca = new Peca(pecaId++);
            fila.adicionarNoFinal(peca);
        }
        imprimirFilas();
    }
	
    public void processarMaquina(int numeroMaquina) {
        ListaLigada maquinaAtual;
        ListaLigada proximaMaquina;
        
        switch (numeroMaquina) {
            case 1:
                maquinaAtual = maquina1;
                proximaMaquina = maquina2;
                break;
            case 2:
                maquinaAtual = maquina2;
                proximaMaquina = maquina3;
                break;
            case 3:
                maquinaAtual = maquina3;
                proximaMaquina = null;
                break;
            default:
                throw new IllegalArgumentException("Número de máquina inválido");
        }
        
        while (!maquinaAtual.ehVazia()) {
            Peca peca = (Peca) maquinaAtual.removerDoComeco();
            if (proximaMaquina != null) {
                proximaMaquina.adicionarNoFinal(peca);
            }
        }
        
        imprimirFilas();
    }
    
    public void imprimirFilas() {
        System.out.println("Fila: " + fila);
        System.out.println("Máquina 1: " + maquina1);
        System.out.println("Máquina 2: " + maquina2);
        System.out.println("Máquina 3: " + maquina3);
        System.out.println();
    }
    
    public static void main(String[] args) {
        FilaProducao producao = new FilaProducao();
        
        producao.adicionarPecasNaMaquina1(5);
        producao.processarMaquina(1);
        producao.processarMaquina(2);
        producao.processarMaquina(3);
    }
}

class Peca {
    private int id;

    public Peca(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "P" + id;
    }
}

class Celula {
    private Object elemento;
    private Celula proximo;

    public Celula(Object elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }

    public Object getElemento() {
        return elemento;
    }

    public Celula getProximo() {
        return proximo;
    }

    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }
}

class ListaLigada {
    private Celula primeira;
    private Celula ultima;
    private int totalDeElementos = 0;

    public void adicionarNoFinal(Object novoElemento) {
        Celula nova = new Celula(novoElemento);

        if (ehVazia()) {
            this.primeira = nova;
            this.ultima = nova;
        } else {
            this.ultima.setProximo(nova);
            this.ultima = nova;
        }
        this.totalDeElementos++;
    }

    public Object removerDoComeco() {
        if (ehVazia()) {
        	throw new RuntimeException("Vazia");
        }
        
        Object elementoRemovido = this.primeira.getElemento();
        this.primeira = this.primeira.getProximo();
        this.totalDeElementos--;
        
        if (this.totalDeElementos == 0) {
            this.ultima = null;
        }
        
        return elementoRemovido;
    }

    public boolean ehVazia() {
        return this.totalDeElementos == 0;
    }

    @Override
    public String toString() {
        if (ehVazia()) {
            return "[]";
        }

        Celula atual = this.primeira;
        StringBuilder builder = new StringBuilder("[");
        
        while (atual != null) {
            builder.append(atual.getElemento());
            atual = atual.getProximo();
            if (atual != null) {
                builder.append(", ");
            }
        }
        
        builder.append("]");
        return builder.toString();
    }
}
