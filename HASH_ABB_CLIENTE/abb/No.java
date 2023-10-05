public class No {

    private Cliente cliente;
    private No direito;
    private No esquerdo;

    public No(Cliente cliente, No direito, No esquerdo) {
        this.cliente = cliente;
        this.direito = direito;
        this.esquerdo = esquerdo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public No getDireito() {
        return direito;
    }

    public void setDireito(No direito) {
        this.direito = direito;
    }

    public No getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;
    }

    public boolean ehFolha() {
        return (direito == null && esquerdo == null);
    }
}
