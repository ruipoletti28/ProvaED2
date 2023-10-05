package hash;

import listaligada.ListaLigada;
import abb.ArvoreBinariaBusca; // Importe a classe de sua Árvore Binária de Busca aqui

public class TabelaHash {

    private ArvoreBinariaBusca[] elementos; // Use sua classe de Árvore Binária de Busca aqui
    private ListaLigada[] colisoes;

    public TabelaHash(int tamanho) {
        elementos = new ArvoreBinariaBusca[tamanho]; // Use sua classe de Árvore Binária de Busca aqui
        colisoes = new ListaLigada[tamanho];
        inicializarListaLigadaColisoes(tamanho);
    }

    private void inicializarListaLigadaColisoes(int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            colisoes[i] = new ListaLigada();
        }
    }

    public ArvoreBinariaBusca[] getElementos() {
        return elementos;
    }

    public ListaLigada[] getColisoes() {
        return colisoes;
    }
}
