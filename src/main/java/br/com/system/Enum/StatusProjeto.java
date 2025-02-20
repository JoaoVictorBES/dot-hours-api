package br.com.system.Enum;

public enum StatusProjeto {

    PENDENTE(0),
    EM_ANDAMENTO(1),
    CONCLUIDO(2),
    CANCELADO(3);

    private final int codigo;

    StatusProjeto(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static StatusProjeto fromCodigo(int codigo) {
        for (StatusProjeto status : StatusProjeto.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + codigo);
    }

}
