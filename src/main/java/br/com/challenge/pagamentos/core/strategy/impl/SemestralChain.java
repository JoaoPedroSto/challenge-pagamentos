package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.core.entity.enuns.Frequencia;
import br.com.challenge.pagamentos.core.entity.model.RecorrenciaEntity;
import br.com.challenge.pagamentos.core.strategy.RecorrenciaStrategy;

import java.time.LocalDate;

public class SemestralChain implements RecorrenciaStrategy {

    private RecorrenciaStrategy strategy;

    @Override
    public void nextChain(RecorrenciaStrategy nextChain) {
        this.strategy = nextChain;
    }


    @Override
    public void execute(RecorrenciaEntity recurrence, Float amount) {
        if (recurrence != null
                && recurrence.getFrequency() == Frequencia.SEMESTRAL){
            if(amount < 150 || recurrence.getFinalDate().isAfter(LocalDate.now().plusYears(4))){
                throw new RuntimeException();
            }
        }
    }
}
