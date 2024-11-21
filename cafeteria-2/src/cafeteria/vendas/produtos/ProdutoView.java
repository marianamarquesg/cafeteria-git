package cafeteria.vendas.produtos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ProdutoView extends JInternalFrame {

	private static final String TITULO = "Cadastro de Produto";

	private static final int POSICAO_X_INICIAL = 60;
	private static final int POSICAO_Y_INICIAL = 60;

	private static final int LARGURA = 580;
	private static final int ALTURA = 300;

	private static final long serialVersionUID = 1L;

	private JTextField id;
	private JTextField nome;
	private JComboBox<UnidadeMedida> medida;
	private JFormattedTextField preco;
	private JTextField estoque;
	private JCheckBox temEstoque;

	private JButton btSalvar;
	private JButton btVoltar;
	private JButton btNovoProduto;
	private JButton btPesquisar;

	private IProdutoService service = null;

	private Boolean produtoNovo = false;
	/**
	 * Cria a janela do CRUD do produto
	 */
	public ProdutoView(IProdutoService service) {
		this.service = service;

		setClosable(true);
		setIconifiable(true);
		setSize(LARGURA, ALTURA);
		setLocation(POSICAO_X_INICIAL, POSICAO_Y_INICIAL);
		setTitle(TITULO);
		getContentPane().setLayout(null);

		JLabel lbId = new JLabel("ID:");
		lbId.setBounds(31, 40, 60, 17);
		getContentPane().add(lbId);

		id = new JTextField();
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(109, 38, 114, 21);
		getContentPane().add(id);
		id.setColumns(10);

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(31, 73, 60, 17);
		getContentPane().add(lbNome);

		nome = new JTextField();
		nome.setBounds(109, 71, 430, 21);
		getContentPane().add(nome);
		nome.setColumns(10);

		JLabel lbMedida = new JLabel("Medida:");
		lbMedida.setBounds(31, 106, 60, 17);
		getContentPane().add(lbMedida);

        medida = new JComboBox<>(UnidadeMedida.values());
		medida.setBounds(109, 104, 114, 21);
		getContentPane().add(medida);

		btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickSalvar();
			}
		});
		btSalvar.setBounds(434, 229, 105, 27);
		getContentPane().add(btSalvar);

		btVoltar = new JButton("Voltar");
		btVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickVoltar();
			}
		});
		btVoltar.setBounds(322, 229, 105, 27);
		getContentPane().add(btVoltar);

		btNovoProduto = new JButton("Novo Produto");
		btNovoProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickIncluirNovoProduto();
			}
		});
		btNovoProduto.setBounds(400, 35, 139, 27);
		getContentPane().add(btNovoProduto);

		btPesquisar = new JButton("Pesquisar");
		btPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickPesquisar();
			}
		});
		btPesquisar.setBounds(235, 35, 96, 27);
		getContentPane().add(btPesquisar);

		JLabel lbPreco = new JLabel("Preço:");
		lbPreco.setBounds(31, 136, 60, 17);
		getContentPane().add(lbPreco);

		JLabel lbEstoque = new JLabel("Estoque:");
		lbEstoque.setBounds(31, 205, 60, 17);
		getContentPane().add(lbEstoque);

		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);
		preco = new JFormattedTextField(numberFormat);
		preco.setHorizontalAlignment(SwingConstants.RIGHT);
		preco.setBounds(109, 137, 114, 21);
		getContentPane().add(preco);
		preco.setColumns(10);

		estoque = new JTextField();
		estoque.setHorizontalAlignment(SwingConstants.RIGHT);
		estoque.setBounds(109, 203, 114, 21);
		getContentPane().add(estoque);
		estoque.setColumns(10);

		temEstoque = new JCheckBox("Tem estoque?");
		temEstoque.setBounds(26, 166, 114, 25);
		getContentPane().add(temEstoque);
	}

	/**
	 * Prepara o frame para a ação de consultar
	 */
	public void setupConsultar() {
		// configura os botões de ação
		btSalvar.setEnabled(false);
		btVoltar.setEnabled(false);
		btNovoProduto.setEnabled(true);
		btPesquisar.setEnabled(true);

		// configura o comportamento dos campos
		id.setEnabled(true);
		nome.setEnabled(false);
		medida.setEnabled(false);
		preco.setEnabled(false);
		temEstoque.setEnabled(false);
		estoque.setEnabled(false);
	}

	public void setupProdutoEncontrado() {
		// configura os botões de ação
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);
		btNovoProduto.setEnabled(false);
		btPesquisar.setEnabled(false);

		// configura o comportamento dos campos
		id.setEnabled(false);
		nome.setEnabled(true);
		medida.setEnabled(true);
		preco.setEnabled(true);
	}

	public void setupVoltar() {
		// configura os botões de ação
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);
		btNovoProduto.setEnabled(true);
		btPesquisar.setEnabled(true);

		// configura o comportamento dos campos
		id.setEnabled(false);
		nome.setEnabled(true);
		medida.setEnabled(true);
		preco.setEnabled(true);

    }

	public void setupAdicionarProduto() {
		// configura os botões de ação
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);
		btNovoProduto.setEnabled(false);
		btPesquisar.setEnabled(false);

		// configura o comportamento dos campos
		id.setEnabled(false);
		nome.setEnabled(true);
		medida.setEnabled(true);
		preco.setEnabled(true);
	}
	protected void onClickPesquisar() {
		int idProduto = Integer.parseInt(id.getText());
		Produto produto = this.service.pesquisarProdutoPorId(idProduto); //chama o produto service
		if (produto != null) {
			this.id.setText(String.valueOf(produto.getId()));
			this.nome.setText(produto.getNome());
			this.medida.setSelectedItem(produto.getMedida());
			this.preco.setText(String.valueOf(produto.getPreco()));
			this.setupProdutoEncontrado();
			this.produtoNovo = false;
		}else {
			JOptionPane.showMessageDialog(null, "Nenhum produto encontrado!", "Sucesso", JOptionPane.ERROR_MESSAGE);
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
	this.setupVoltar();
	System.out.println("==> onClickVoltar");
}

	/**
	 * Executa as tarefas para salvar a inclusão de um novo produto
	 */
	protected void onClickSalvar() {
		try {
			// Converte os valores dos campos
			int idProduto = Integer.parseInt(id.getText()); // Converte o ID para int
			String nomeProduto = nome.getText(); // Obtém o nome do produto
			UnidadeMedida medidaProduto = (UnidadeMedida) medida.getSelectedItem(); // Obtém o item selecionado no JComboBox
			Double precoProduto = Double.parseDouble(preco.getText()); // Converte o preço para Double
	
			// Verificação dos campos
			if (idProduto <= 0) { // Verifica se o ID é maior que 0
				JOptionPane.showMessageDialog(null, "O campo ID não pode ser vazio ou inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
				return; // Interrompe a execução se o ID for inválido
			}
	
			if (nomeProduto.isEmpty()) { // Verifica se o nome não está vazio
				JOptionPane.showMessageDialog(null, "O campo Nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
	
			if (medidaProduto == null) { // Verifica se a medida foi selecionada no JComboBox
				JOptionPane.showMessageDialog(null, "Selecione uma medida válida!", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
	
			if (precoProduto <= 0) { // Verifica se o preço é maior que 0
				JOptionPane.showMessageDialog(null, "O preço deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
	
			// Operações de cadastro ou atualização
			ProdutoService upsert = new ProdutoService();
			if (this.produtoNovo) {
				// Cadastrar novo produto
				upsert.cadastrarProduto(nomeProduto, idProduto, medidaProduto, precoProduto.doubleValue());
				this.setupAdicionarProduto();
			} else {
				// Atualizar produto existente
				upsert.atualizarProduto(nomeProduto, idProduto, medidaProduto, precoProduto.doubleValue());
			}
	
			JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	
		} catch (NumberFormatException e) {
			// Captura erros de conversão para int ou Double
			JOptionPane.showMessageDialog(null, "ID ou Preço inválido. Por favor, insira valores numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	}
