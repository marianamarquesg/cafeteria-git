package cafeteria.vendas.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JOptionPane;


public class ClienteView extends JInternalFrame {

	private static final String TITULO = "Cadastro de Cliente";

	private static final int POSICAO_X_INICIAL = 30;
	private static final int POSICAO_Y_INICIAL = 30;

	private static final int LARGURA = 580;
	private static final int ALTURA = 210;

	private static final long serialVersionUID = 1L;

	private JTextField id;
	private JTextField nome;
	private JFormattedTextField telefone;

	private JButton btSalvar;
	private JButton btVoltar;
	private JButton btNovoCliente;
	private JButton btPesquisar;

	private IClienteService service = null;

	private Boolean clienteNovo = false;

	/**
	 * Cria a janela do CRUD do cliente
	 */
	public ClienteView(IClienteService service) {
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

		JLabel lbTelefone = new JLabel("Telefone:");
		lbTelefone.setBounds(31, 106, 60, 17);
		getContentPane().add(lbTelefone);

		MaskFormatter maskFormatter;
		try {
			maskFormatter = new MaskFormatter("(##) #####-####");
			maskFormatter.setPlaceholderCharacter('_'); // Caracter de espaço reservado
			telefone = new JFormattedTextField(maskFormatter);
			telefone.setBounds(109, 104, 132, 21);
			getContentPane().add(telefone);
			telefone.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		btSalvar = new JButton("Confirmar");
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickSalvar();
			}
		});
		btSalvar.setBounds(434, 126, 105, 27);
		getContentPane().add(btSalvar);

		btVoltar = new JButton("Cancelar");
		btVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickVoltar();
			}
		});
		btVoltar.setBounds(317, 126, 105, 27);
		getContentPane().add(btVoltar);

		btNovoCliente = new JButton("Novo Cliente");
		btNovoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickIncluirNovoCliente();
			}
		});
		btNovoCliente.setBounds(400, 35, 139, 27);
		getContentPane().add(btNovoCliente);

		btPesquisar = new JButton("Pesquisar");
		btPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onClickPesquisar();
			}
		});
		btPesquisar.setBounds(235, 35, 96, 27);
		getContentPane().add(btPesquisar);
	}

	/**
	 * Prepara o frame para a ação de consultar
	 */
	public void setupConsultar() {
		// configura os botões de ação
		btSalvar.setEnabled(false);
		btVoltar.setEnabled(false);
		btNovoCliente.setVisible(true);
		btPesquisar.setEnabled(true);

		// configura o comportamento dos campos
		id.setEnabled(true);
		nome.setEnabled(false);
		telefone.setEnabled(false);
	}

	public void setupClienteEncontrado() {
		// configura os botões de ação
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);
		btNovoCliente.setEnabled(false);
		btPesquisar.setEnabled(false);

		// configura o comportamento dos campos
		id.setEnabled(false);
		nome.setEnabled(true);
		telefone.setEnabled(true);
	}

	public void setupVoltar() {
		// configura os botões de ação
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);
		btNovoCliente.setEnabled(true);
		btPesquisar.setEnabled(true);

		// configura o comportamento dos campos
		id.setEnabled(true);
		nome.setEnabled(true);
		telefone.setEnabled(true);
	}

	public void setupAdicionarCliente() {
		// configura os botões de ação
		btSalvar.setEnabled(true);
		btVoltar.setEnabled(true);
		btNovoCliente.setEnabled(false);
		btPesquisar.setEnabled(false);

		// configura o comportamento dos campos
		id.setEnabled(false);
		nome.setEnabled(true);
		telefone.setEnabled(true);
	}
	/**
	 * Executa as tarefas para efetuar uma pesquisa com base no ID informado
	 */
	protected void onClickPesquisar() {
		int idCliente =  Integer.parseInt(id.getText());
		Cliente cliente = this.service.pesquisarClientePorId(idCliente); //chama a service
		if(cliente != null) {
			this.nome.setText(cliente.getNome());
			this.telefone.setText(cliente.getTelefone());
			this.setupClienteEncontrado();
			this.clienteNovo = false;
		}else {
			JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado!", "Sucesso", JOptionPane.ERROR_MESSAGE);
		}			
	}

	/**
	 * Executa as tarefas para preparar a interface para a inclusão de um novo
	 * cliente
	 */
	protected void onClickIncluirNovoCliente() {
		this.setupAdicionarCliente();
		this.clienteNovo = true;		
		System.out.println("==> onClickIncluirNovoCliente");
	}

	/**
	 * Executa as tarefas para cancelar a inclusão de um cliente
	 */
	protected void onClickVoltar() {
		this.nome.setText("");
		this.telefone.setText("");
		this.id.setText("");
		this.setupVoltar();
		System.out.println("==> onClickVoltar");
	}

	/**
	 * Executa as tarefas para confirmar a inclusão de um novo cliente
	 */
	protected void onClickSalvar() {
		String nomeCliente = nome.getText();
		String telefoneCliente = telefone.getText();
		int idCliente =  Integer.parseInt(id.getText());

		if(!nomeCliente.isEmpty() && !telefoneCliente.isEmpty()) {
			ClienteService upsert = new ClienteService();
				if(this.clienteNovo) {	
					upsert.cadastrarCliente(nomeCliente, telefoneCliente);
					this.setupAdicionarCliente();	
				} else {
					upsert.atualizarCliente(nomeCliente, telefoneCliente, idCliente);
				}
			JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);	
		}else{
			JOptionPane.showMessageDialog(null, "Preencha o nome e o telefone!", "Sucesso", JOptionPane.ERROR_MESSAGE);
		}
			
		System.out.println("==> onClickSalvar");
	}

}
