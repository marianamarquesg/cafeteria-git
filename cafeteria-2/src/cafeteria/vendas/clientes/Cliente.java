package cafeteria.vendas.clientes;
//import java.util.ArrayList;

import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String telefone;
	private int id;

	//private ArrayList<Registro> registros;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome == null || nome.isEmpty()) {
			this.nome = "Nenhum nome encontrado para esse cadastro";
		}else {
			this.nome = nome;
		}
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if(telefone == null || telefone.isEmpty()) {
			this.telefone = "Nenhum telefone encontrado para esse cadastro";
		}else {
			this.telefone = telefone;
		}

		this.telefone = telefone;
	}
	public void setId(int id) {
		this.id = id;
	}
    public int getId() {
        return id;
    }

}
