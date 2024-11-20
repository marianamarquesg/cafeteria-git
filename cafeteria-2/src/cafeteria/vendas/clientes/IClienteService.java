package cafeteria.vendas.clientes;

public interface IClienteService {
    Cliente pesquisarClientePorId(int idCliente);
    void cadastrarCliente(String nome, String telefone);
    void atualizarCliente(String nome, String telefone, int idCliente);
}
