package tech.brunobrito.spring_boot_help_desk.chamado;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public enum Prioridade {
    CRITICA(4),
    ALTA(8),
    MEDIA(24),
    BAIXA(72);


    private final int horasSla;

    Prioridade(int horasSla) {
        this.horasSla = horasSla;
    }

    public Instant calculcarPrazo(Instant abertura){
        return abertura.plus(horasSla, ChronoUnit.HOURS);

    }

}
