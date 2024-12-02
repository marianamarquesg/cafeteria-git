package cafeteria.vendas;

import java.time.LocalDateTime;
import cafeteria.vendas.clientes.Cliente;

public class Venda {
    private int id;
    private LocalDateTime dataHora;
    private Cliente cliente; 
    private double valorTotal;

    // Construtor
    public Venda(int id, LocalDateTime dataHora, Cliente cliente, double valorTotal) {
        this.id = id;
        this.dataHora = dataHora;
        this.cliente = cliente; // Associa um cliente à venda
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void exibirDetalhes() {
        System.out.println("Venda ID: " + id);
        System.out.println("Data e Hora: " + dataHora);
        System.out.println("Cliente: " + (cliente != null ? cliente.getNome() : "Cliente não cadastrado"));
        System.out.println("Valor Total: R$ " + valorTotal);
    }
}
