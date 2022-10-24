package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.entrypoint.dto.RecurrenceDTO;
import br.com.challenge.pagamentos.core.entity.enuns.Frequency;
import br.com.challenge.pagamentos.core.entity.model.RecurrenceEntity;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;

import java.time.LocalDate;

public class SemestralChain implements RecurrenceChain {

    private RecurrenceChain chain;

    @Override
    public void execute(RecurrenceEntity payload, Float amount) {
        if (payload != null
                && payload.getFrequency() == Frequency.SEMESTRAL){
            if(amount < 150 || payload.getFinalDate().isAfter(LocalDate.now().plusYears(4))){
                throw new BusinessException("O valor deve ser superior a 150 reais e a data final inferior a 4 ano.");
            }
            payload.setFinalDate(LocalDate.now().plusMonths(6));
        }
    }

    @Override
    public void next(RecurrenceChain next) {
        this.chain = next;
    }
}
