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

<<<<<<< HEAD
		// configura o comportamento dos campos
		id.setEnabled(false);
		nome.setEnabled(true);
		medida.setEnabled(true);
		preco.setEnabled(true);
	}
	protected void onClickPesquisar() {
		try {
			int idProduto = Integer.parseInt(id.getText().trim());
			System.out.println("Iniciando pesquisa para o ID: " + idProduto);
			
			Produto produto = this.service.pesquisarProdutoPorId(idProduto);
			
			if (produto != null) {
				this.id.setText(String.valueOf(produto.getId()));
				this.nome.setText(produto.getNome());
				this.medida.setSelectedItem(produto.getMedida());
				this.preco.setText(String.valueOf(produto.getPreco()));
				if (produto.getEstoque()>= 0) {
					this.estoque.setText(String.valueOf(produto.getEstoque()));
					this.temEstoque.setSelected(true);
				}
				this.setupProdutoEncontrado();
				this.produtoNovo = false;
				System.out.println("Produto exibido com sucesso.");
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum produto encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("Produto não encontrado.");  // Adicionando log aqui
			}
		} catch (NumberFormatException e) {
			System.err.println("Erro ao converter ID: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (RuntimeException e) {
			System.err.println("Erro na pesquisa: " + e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);  // Mostrar a exceção de forma mais clara
		}
	}

	/**
	 * Executa as tarefas para preparar a interface para a inclusão de um novo
	 * produto
	 */
	protected void onClickIncluirNovoProduto() {
		this.setupAdicionarProduto();
		this.produtoNovo = true;
		System.out.println("==> onClickIncluirNovoProduto");
	}

	/**
	 * Executa as tarefas para voltar a inclusão de um produto
	 */

	protected void onClickVoltar() {
		this.id.setText(""); 
		this.nome.setText(""); 
		this.medida.setSelectedItem("");
		this.preco.setText("");
		this.estoque.setText("");
		this.temEstoque.setSelected(false);
		this.setupVoltar();
		System.out.println("==> onClickVoltar");
}
	/**
	 * Executa as tarefas para salvar a inclusão de um novo produto
	 */
	protected void onClickSalvar() {
		try {
			String idText = id.getText().trim();
			int idProduto = 0;
	
			if (!idText.isEmpty()) {
				idProduto = Integer.parseInt(idText);
				if (idProduto < 0) {
					JOptionPane.showMessageDialog(null, "O ID deve ser um número positivo!", "Erro", JOptionPane.ERROR_MESSAGE);
					return; // Interrompe a execução
				}
			}
	
			String precoText = preco.getText().trim();
			if (precoText.isEmpty()) {
				JOptionPane.showMessageDialog(null, "O campo Preço não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
	
			precoText = precoText.replace(",", ".");
			double precoProduto = Double.parseDouble(precoText);
	
			if (precoProduto <= 0) {
				JOptionPane.showMessageDialog(null, "O preço deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
				return; // Interrompe a execução
			}
	
			String nomeProduto = nome.getText().trim();
			if (nomeProduto.isEmpty()) {
				JOptionPane.showMessageDialog(null, "O campo Nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
				return; // Interrompe a execução
			}
	
			UnidadeMedida medidaProduto = (UnidadeMedida) medida.getSelectedItem();
			if (medidaProduto == null) {
				JOptionPane.showMessageDialog(null, "Selecione uma medida válida!", "Erro", JOptionPane.ERROR_MESSAGE);
				return; // Interrompe a execução
			}
	
			String estoqueText = estoque.getText().trim();
			if (estoqueText.isEmpty()) {
				JOptionPane.showMessageDialog(null, "O campo Estoque não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
				return; // Interrompe a execução
			}
	
			int estoqueProduto;
			try {
				estoqueProduto = Integer.parseInt(estoqueText); // Conversão do texto para inteiro
				if (estoqueProduto < 0) {
					JOptionPane.showMessageDialog(null, "O estoque não pode ser negativo!", "Erro", JOptionPane.ERROR_MESSAGE);
					return; // Interrompe a execução
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "O valor do estoque deve ser um número inteiro válido!", "Erro", JOptionPane.ERROR_MESSAGE);
				return; // Interrompe a execução
			}
	
			ProdutoService upsert = new ProdutoService();
			if (this.produtoNovo) {
				upsert.cadastrarProduto(nomeProduto, medidaProduto, precoProduto, estoqueProduto);
			} else {
				upsert.atualizarProduto(nomeProduto, idProduto, medidaProduto, precoProduto, estoqueProduto);
			}
	
			JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Um ou mais campos numéricos estão inválidos. Verifique os valores inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
=======
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
>>>>>>> refs/remotes/origin/main

            default:
                throw new RuntimeException("Inexistente");
        }
    }
}
