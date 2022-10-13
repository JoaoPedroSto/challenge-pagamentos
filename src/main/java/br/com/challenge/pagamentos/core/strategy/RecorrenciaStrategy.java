package br.com.challenge.pagamentos.core.strategy;

import br.com.challenge.pagamentos.core.entity.model.RecorrenciaEntity;

public interface RecorrenciaStrategy {

    void nextChain(RecorrenciaStrategy chain);
    void execute(RecorrenciaEntity recurrence, Float amount);
}
