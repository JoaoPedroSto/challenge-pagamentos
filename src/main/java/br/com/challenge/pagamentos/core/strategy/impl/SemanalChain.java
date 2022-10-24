package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.core.entity.enuns.Frequency;
import br.com.challenge.pagamentos.core.entity.model.RecurrenceEntity;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;

import java.time.LocalDate;

public class SemanalChain implements RecurrenceChain {

    private RecurrenceChain chain;

    @Override
    public void execute(RecurrenceEntity payload, Float amount){
        if (payload != null
                && payload.getFrequency() == Frequency.SEMANAL){
            if(amount < 50 || payload.getFinalDate().isAfter(LocalDate.now().plusYears(1))){
                throw new BusinessException("O valor deve ser superior a 50 reais e a data final inferior a 1 ano.");
            }
            payload.setNextPayment(LocalDate.now().plusWeeks(1));
        }
        this.chain.execute(payload,amount);
    }

    @Override
    public void next(RecurrenceChain next) {
        this.chain = next;
    }
}
