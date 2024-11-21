package cafeteria.vendas.produtos;

public enum UnidadeMedida {
    UNIDADE(1),
    LATA(2),
    LITRO(3),
    PACOTE(4),
    FATIA(5),
    GARRAFA(6),
    CAIXA(7);

    private final int codigo;

    UnidadeMedida(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
