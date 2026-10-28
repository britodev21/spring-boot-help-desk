package tech.brunobrito.spring_boot_help_desk.chamado;

public enum StatusChamado {
    ABERTO,
    EM_ATENDIMENTO,
    AGUARDANDO_SOLICITANTE,
    RESOLVIDO,
    FECHADO,
    CANCELADO;

    public boolean podeIrPara(StatusChamado novo) {
        return switch (this) {
            case ABERTO -> novo == ABERTO || novo == CANCELADO;
            case EM_ATENDIMENTO -> novo == AGUARDANDO_SOLICITANTE || novo == RESOLVIDO;
            case AGUARDANDO_SOLICITANTE -> novo == EM_ATENDIMENTO;
            case RESOLVIDO -> novo == FECHADO || novo == EM_ATENDIMENTO;
            case FECHADO, CANCELADO -> false;
        };
    }


    public boolean isEncerrado() {
    return this == RESOLVIDO || this == FECHADO || this == CANCELADO;
}

}
