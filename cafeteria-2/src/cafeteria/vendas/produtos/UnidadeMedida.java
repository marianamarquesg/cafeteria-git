package cafeteria.vendas.produtos;

public enum UnidadeMedida {
    UNIDADE("Unidade"),
    LATA("Lata"),
    LITRO("Litro"),
    PACOTE("Pacote"),
    FATIA("Fatia"),
    GARRAFA("Garrafa"),
    CAIXA("Caixa");

    private final String nome;

    UnidadeMedida(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static UnidadeMedida fromString(String nome) {
        for (UnidadeMedida medida : UnidadeMedida.values()) {
            if (medida.nome.equalsIgnoreCase(nome)) {
                return medida;
            }
        }
        throw new IllegalArgumentException("Medida inválida: " + nome);
    }
}
