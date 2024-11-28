package cafeteria.vendas.relatorios;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cafeteria.Conexao;
import cafeteria.vendas.clientes.Cliente;
import cafeteria.vendas.produtos.IProdutoService;
import cafeteria.vendas.produtos.Produto;
import cafeteria.vendas.produtos.ProdutoService;
import cafeteria.vendas.produtos.UnidadeMedida;

public class RelatorioProduto {
      Connection conn = Conexao.getConnection();
    String nome = null;

    public void exportar(File destino) {
        List<Cliente> produtos = this.listarProdutos();

        try(PrintWriter writer = new PrintWriter(new PrintWriter(destino))) {
			writer.println("ID - NOME - MEDIDA - PRECO - ESTOQUE");        
            for (Cliente produto : produtos) {
                writer.printf("%d - %s - %s%n", produto.getId(), produto.getNome(), produto.getMedida(), produto.getPreco(), produto.getEstoque());
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public List<Cliente> listarProdutos() {
        Produto produto = null; 
        ProdutoService prdService = new ProdutoService();
         List<Produto> produtos = new ArrayList<>();
         String listarSQL = "SELECT id, nome, medida, preco, estoque FROM cliente";
         try (PreparedStatement stmt = conn.prepareStatement(listarSQL);) {
             ResultSet rs = stmt.executeQuery();
              while (rs.next()) {
                produto = new Produto();
                produto.setNome(rs.getString(1));
                produto.setMedida(prdService.converterdeIntParaUnidadeDeMedida(rs.getInt(2)));              
                produto.setPreco(rs.getDouble(3));

           //     produto.setEstoque(rs.getInt(4));
                produtos.add(produto);
             }
             rs.close();
             rs = null;
         } catch (SQLException e) {
             System.err.println("Nao foi possivel realizar a atualizacao: " + e.getMessage());
         }
         Conexao.close(conn);
         return produtos;
     }


        @Override
        public String getNomeRelatorio() {
             return "Relatorio Produto";
        }
     

}
