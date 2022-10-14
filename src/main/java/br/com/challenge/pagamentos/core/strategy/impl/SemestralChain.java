package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.RecorrenciaDTO;
import br.com.challenge.pagamentos.core.entity.enuns.Frequencia;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;

import java.time.LocalDate;

public class SemestralChain extends RecurrenceChain {

    @Override
    public void execute(RecorrenciaDTO recurrence, Float amount) {
        if (recurrence != null
                && recurrence.getFrequency() == Frequencia.SEMESTRAL){
            if(amount < 150 || recurrence.getFinalDate().isAfter(LocalDate.now().plusYears(4))){
                throw new RuntimeException();
            }
        }
    }
}
