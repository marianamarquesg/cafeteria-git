package cafeteria.vendas.clientes;

import java.sql.Connection;
import cafeteria.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ClienteService implements IClienteService {
    Connection conn = Conexao.getConnection();

        @Override
        public Cliente pesquisarClientePorId(int idCliente) {
            Cliente cliente = null; 
            String pesquisarSQL = "SELECT nome, telefone FROM cliente WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(pesquisarSQL);) {
                stmt.setInt(1, idCliente);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setNome(rs.getString(1));
                    cliente.setTelefone(rs.getString(2));
                } 
            } catch (SQLException e) {
                System.err.println("Nao foi possivel realizar a consulta: " + e.getMessage());
            }
      //      Conexao.close(conn);
            return cliente;     
    }


    public void cadastrarCliente(String nome, String telefone) {
        String cadastrarSQL = "INSERT INTO cliente (nome, telefone) VALUES (?, ?);";
		try (PreparedStatement stmt = conn.prepareStatement(cadastrarSQL);) {
			stmt.setString(1, nome);
			stmt.setString(2, telefone);         
			stmt.execute();
		} catch (SQLException e) {
			System.err.println("Nao foi possivel realizar o cadastro: " + e.getMessage());
		}
      //  Conexao.close(conn);
        
    }

    public void atualizarCliente(String nome, String telefone, int idCliente) {
        String atualizarSQL = "UPDATE cliente SET nome = ?, telefone = ? WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(atualizarSQL);) {
			stmt.setString(1, nome);
			stmt.setString(2, telefone);   
            stmt.setInt(3, idCliente);      
			stmt.execute();
		} catch (SQLException e) {
			System.err.println("Nao foi possivel realizar a atualizacao: " + e.getMessage());
		}
     //   Conexao.close(conn);
    }

}
