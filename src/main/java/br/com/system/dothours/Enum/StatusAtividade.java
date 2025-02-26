package br.com.system.dothours.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusAtividade {

    PENDENTE(0),
    EM_ANDAMENTO(1),
    CONCLUIDO(2),
    CANCELADO(3);

    private final int codigo;

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
    }

}
