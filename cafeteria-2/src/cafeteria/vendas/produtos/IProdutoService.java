package cafeteria.vendas.produtos;

public interface IProdutoService {
    Produto pesquisarProdutoPorId(int idProduto);
    void cadastrarProduto (String nome, int id, UnidadeMedida medida, double preco);
    void atualizarProduto(String nome, int id, UnidadeMedida medida, double preco);

}
