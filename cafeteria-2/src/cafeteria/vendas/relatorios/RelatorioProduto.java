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
import cafeteria.vendas.produtos.Produto;
import cafeteria.vendas.produtos.ProdutoService;

public class RelatorioProduto implements RelatorioExportavelEmArquivoTexto {
      Connection conn = Conexao.getConnection();
    String nome = null;

    public void exportar(File destino) {
        List<Produto> produtos = this.listarProdutos();

        try(PrintWriter writer = new PrintWriter(new PrintWriter(destino.getAbsolutePath()))) {
			writer.println("ID - NOME - MEDIDA - PRECO - ESTOQUE");        
            for (Produto produto : produtos) {
                writer.printf("%d - %s - %s - %.2f - %d%n", 
                produto.getId(), 
                produto.getNome(), 
                produto.getMedida(),
                produto.getPreco(), 
                produto.getEstoque());
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public List<Produto> listarProdutos() {
        Produto produto = null; 
        ProdutoService prdService = new ProdutoService();
         List<Produto> produtos = new ArrayList<>();
         String listarSQL = "SELECT id, nome, medida, preco, estoque FROM produto";
         try (PreparedStatement stmt = conn.prepareStatement(listarSQL);) {
             ResultSet rs = stmt.executeQuery();
              while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt(1));
                produto.setNome(rs.getString(2));
                produto.setMedida(prdService.converterdeIntParaUnidadeDeMedida(rs.getInt(3)));              
                produto.setPreco(rs.getDouble(4));
                produto.setEstoque(rs.getInt(5));
                produtos.add(produto);
             }
             rs.close();
             rs = null;
         } catch (SQLException e) {
             System.err.println("Nao foi possivel retornar a lista de produtos: " + e.getMessage());
         }
         Conexao.close(conn);
         return produtos;
     }

        public String getNomeRelatorio() {
             return "Relatorio Produto";
        }
     

}
