package cafeteria.vendas.relatorios;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cafeteria.Conexao;
import cafeteria.vendas.clientes.Cliente;

public class RelatorioCliente implements RelatorioExportavelEmArquivoTexto {
    Connection conn = Conexao.getConnection();
    String nome = null;

    public void exportar(File destino) {
        List<Cliente> clientes = this.listarClientes();

        try(PrintWriter writer = new PrintWriter(new PrintWriter(destino))) {
			writer.println("ID - NOME - TELEFONE");        
            for (Cliente cliente : clientes) {
                writer.printf("%d - %s - %s%n", cliente.getId(), cliente.getNome(), cliente.getTelefone());
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

        public List<Cliente> listarClientes() {
       Cliente cliente = null; 
        List<Cliente> clientes = new ArrayList<>();
        String listarSQL = "SELECT nome, telefone, id FROM cliente";
		try (PreparedStatement stmt = conn.prepareStatement(listarSQL);) {
            ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                cliente = new Cliente();
                cliente.setNome(rs.getString(1));
                cliente.setTelefone(rs.getString(2));
                cliente.setId(rs.getInt(3));
                clientes.add(cliente);
            }
			rs.close();
			rs = null;
		} catch (SQLException e) {
			System.err.println("Nao foi possivel realizar a atualizacao: " + e.getMessage());
		}
        Conexao.close(conn);
        return clientes;
    }


        @Override
        public String getNomeRelatorio() {
             return "Lista de Clientes";
        }
     

}
