package br.com.system.dothours.Enum;

public enum StatusAtividade {

    PENDENTE,
    EM_ANDAMENTO,
    CONCLUIDO,
    CANCELADO;

    /*private final int codigo;

    StatusAtividade(int codigo) {
        this.codigo = codigo;
    }

    @JsonValue
    public int getCodigo() {
        return codigo;
    }

    @JsonCreator
    public static StatusAtividade fromCodigo(int codigo) {
        for (StatusAtividade status : StatusAtividade.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + codigo);
    }*/

}
