package cafeteria.vendas.produtos;

public class Produto {
    //atributos do produto
     private int id;
     private String nome;
     private UnidadeMedida medida;
     private double preco;

    //construtores 

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
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
