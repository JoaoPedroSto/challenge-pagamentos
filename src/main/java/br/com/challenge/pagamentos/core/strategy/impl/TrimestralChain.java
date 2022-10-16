package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.RecorrenciaDTO;
import br.com.challenge.pagamentos.core.entity.enuns.Frequencia;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;

import java.time.LocalDate;

public class TrimestralChain implements RecurrenceChain {

    private RecurrenceChain chain;

    @Override
    public void execute(RecorrenciaDTO payload, Float amount) {
        if (payload != null
                && payload.getFrequency() == Frequencia.TRIMETRAL){
            if(amount < 130 || payload.getFinalDate().isAfter(LocalDate.now().plusYears(3))){
                throw new RuntimeException();
            }
        }
        this.chain.execute(payload,amount);
    }

    @Override
    public void next(RecurrenceChain next) {
        this.chain = next;
    }

}
