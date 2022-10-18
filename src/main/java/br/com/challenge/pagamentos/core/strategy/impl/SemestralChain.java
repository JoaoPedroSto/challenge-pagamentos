package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.entrypoint.dto.RecorrenciaDTO;
import br.com.challenge.pagamentos.core.entity.enuns.Frequencia;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;

import java.time.LocalDate;

public class SemestralChain implements RecurrenceChain {

    private RecurrenceChain chain;

    @Override
    public void execute(RecorrenciaDTO payload, Float amount) {
        if (payload != null
                && payload.getFrequency() == Frequencia.SEMESTRAL){
            if(amount < 150 || payload.getFinalDate().isAfter(LocalDate.now().plusYears(4))){
                throw new BusinessException("O valor deve ser superior a 150 reais e a data final inferior a 4 ano.");            }
        }
    }

    @Override
    public void next(RecurrenceChain next) {
        this.chain = next;
    }
}
