package cafeteria.vendas.produtos;

public class Produto {
    //atributos do produto
     private int id;
     private String nome;
     private UnidadeMedida medida;
     private double preco;
     private int estoque;

    //construtores getters e setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public UnidadeMedida getMedida() {
        return medida;
    }
    public void setMedida(UnidadeMedida medida) {
        this.medida = medida;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
