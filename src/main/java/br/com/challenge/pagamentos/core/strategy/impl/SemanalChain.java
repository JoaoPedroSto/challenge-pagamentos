package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.RecorrenciaDTO;
import br.com.challenge.pagamentos.core.entity.enuns.Frequencia;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;

import java.time.LocalDate;

public class SemanalChain extends RecurrenceChain {

    @Override
    public void execute(RecorrenciaDTO recurrence, Float amount){
        if (recurrence != null
                && recurrence.getFrequency() == Frequencia.SEMANAL){
            if(amount < 50 || recurrence.getFinalDate().isAfter(LocalDate.now().plusYears(1))){
                throw new RuntimeException();
            }
        }
    }
}
