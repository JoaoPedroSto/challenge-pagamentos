package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.entrypoint.dto.RecurrenceDTO;
import br.com.challenge.pagamentos.core.entity.enuns.Frequency;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;

import java.time.LocalDate;

public class MensalChain implements RecurrenceChain {

    private RecurrenceChain chain;

    @Override
    public void execute(RecurrenceDTO payload, Float amount) {
        if (payload != null
                && payload.getFrequency() == Frequency.MENSAL){
            if(amount < 100 || payload.getFinalDate().isAfter(LocalDate.now().plusYears(2))){
                throw new BusinessException("O valor deve ser superior a 100 reais e a data final inferior a 2 ano.");
            }
        }
        this.chain.execute(payload,amount);
    }

    @Override
    public void next(RecurrenceChain next) {
        this.chain = next;
    }

}
