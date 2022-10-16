package br.com.challenge.pagamentos.core.strategy;

import br.com.challenge.pagamentos.app.entrypoint.dto.RecorrenciaDTO;

public interface RecurrenceChain<T> {

    void execute(RecorrenciaDTO payload, Float amount);
    void next(RecurrenceChain next);

}
