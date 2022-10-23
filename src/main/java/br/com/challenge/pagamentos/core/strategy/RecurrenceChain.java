package br.com.challenge.pagamentos.core.strategy;

import br.com.challenge.pagamentos.app.entrypoint.dto.RecurrenceDTO;

public interface RecurrenceChain<T> {

    void execute(RecurrenceDTO payload, Float amount);
    void next(RecurrenceChain next);

}
