package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.entrypoint.dto.RecurrenceDTO;
import br.com.challenge.pagamentos.core.entity.enuns.Frequency;
import br.com.challenge.pagamentos.core.entity.model.RecurrenceEntity;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;

import java.time.LocalDate;

public class TrimestralChain implements RecurrenceChain {

    private RecurrenceChain chain;

    @Override
    public void execute(RecurrenceEntity payload, Float amount) {
        if (payload != null
                && payload.getFrequency() == Frequency.TRIMETRAL){
            if(amount < 130 || payload.getFinalDate().isAfter(LocalDate.now().plusYears(3))){
                throw new BusinessException("O valor deve ser superior a 130 reais e a data final inferior a 3 ano.");
            }
            payload.setNextPayment(LocalDate.now().plusMonths(3));
        }

        this.chain.execute(payload,amount);
    }

    @Override
    public void next(RecurrenceChain next) {
        this.chain = next;
    }

}
