package br.com.challenge.pagamentos.core.strategy;

import br.com.challenge.pagamentos.app.entrypoint.dto.RecurrenceDTO;
import br.com.challenge.pagamentos.core.entity.model.RecurrenceEntity;

public interface RecurrenceChain<T> {

    void execute(RecurrenceEntity payload, Float amount);
    void next(RecurrenceChain next);

}
