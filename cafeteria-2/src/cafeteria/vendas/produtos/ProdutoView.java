package cafeteria.vendas.produtos;

import java.sql.Connection;
import cafeteria.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class ProdutoService implements IProdutoService {
    Connection conn = Conexao.getConnection();

    @Override
    public Produto pesquisarProdutoPorId(int idProduto) {
        Produto produto = null;
        String sql = "SELECT id, nome, medida, preco, estoque FROM produto WHERE id = ?";
    
        System.out.println("Executando consulta para ID: " + idProduto); // Verificando o ID antes de consultar
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);  
            System.out.println("Consulta SQL: " + stmt);
    
            ResultSet sw = stmt.executeQuery();
    
            if (sw.next()) {
                produto = new Produto();
                produto.setId(sw.getInt("id"));
                produto.setNome(sw.getString("nome"));
                produto.setMedida(this.converterdeIntParaUnidadeDeMedida(sw.getInt("medida")));
                produto.setPreco(sw.getDouble("preco"));
                if (sw.getObject("estoque") != null) {
                    produto.setEstoque(sw.getInt("estoque"));
                } else {
                    produto.setEstoque(-1);
                }
                
    
                System.out.println("Produto encontrado: " + produto.getNome());
            } else {
                System.out.println("Nenhum produto encontrado para o ID: " + idProduto); // Log quando não encontrar
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar produto: " + e.getMessage());
        }
    
        if (produto == null) {
            throw new RuntimeException("Produto Inexistente"); // Lançando exceção se não encontrar o produto
        }
    
        return produto;
    }

    public void cadastrarProduto(String nome, UnidadeMedida medida, double preco, int estoque) {
        String sql = "INSERT INTO produto (nome, medida, preco, estoque) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, medida.getNome());
            stmt.setDouble(3, preco);
            if (estoque == -1) {
                stmt.setObject(4, null);
            } else {
                stmt.setInt(4, estoque);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nao foi possivel realizar o cadastro: " + e.getMessage());
        }

    }

    public void atualizarProduto(String nome, int id, UnidadeMedida medida, double preco, int estoque) {
        String sql = "UPDATE produto SET nome = ?, medida = ?, preco = ?, estoque = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Logs para depuração
            System.out.println("Tentando atualizar produto:");
            System.out.println("ID: " + id);
            System.out.println("Nome: " + nome);
            System.out.println("Medida: " + medida.getNome());
            System.out.println("Preço: " + preco);
            System.out.println("Estoque: " + estoque);
    
            stmt.setString(1, nome);
            stmt.setString(2, medida.getNome());
            stmt.setDouble(3, preco);
            if (estoque == -1) {
                stmt.setObject(4, null);
            } else {
                stmt.setInt(4, estoque);
            }
            stmt.setInt(5, id);
    
            int rowsAffected = stmt.executeUpdate();
    
            if (rowsAffected == 0) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado para atualizar!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a atualização: " + e.getMessage());
        }
    }

    public int converterUnidadeDeMedidaParaInt(UnidadeMedida unidadeMedida){
        switch (unidadeMedida) {
            case GARRAFA:
                return 1;
            case LATA:
                return 2;
            case LITRO:
                return 3;
            case UNIDADE:
                return 4;
            case FATIA:
                return 5;
            case PACOTE:
                return 6;
            case CAIXA:
                return 7;
        
            default:
                throw new RuntimeException("Inexistente");
        }
    }
    public UnidadeMedida converterdeIntParaUnidadeDeMedida(int unidadeMedida){
        switch (unidadeMedida) {
            case 1:
                return UnidadeMedida.GARRAFA;
            case 2:
                return UnidadeMedida.LATA;
            case 3:
                return UnidadeMedida.LITRO;
            case 4:
                return UnidadeMedida.UNIDADE;
            case 5:
                return UnidadeMedida.FATIA;
            case 6:
                return UnidadeMedida.PACOTE;
            case 7:
                return UnidadeMedida.CAIXA;

            default:
                throw new RuntimeException("Inexistente");
        }
    }
}
