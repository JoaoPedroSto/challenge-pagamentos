package br.com.challenge.pagamentos.core.strategy.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.entrypoint.dto.RecorrenciaDTO;
import br.com.challenge.pagamentos.core.entity.enuns.Frequencia;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;

public class SemanalChain implements RecurrenceChain {

    private RecurrenceChain chain;

    @Override
    public void execute(RecorrenciaDTO payload, Float amount){
        if (payload != null
                && payload.getFrequency() == Frequencia.SEMANAL){
            if(amount < 50 || payload.getFinalDate().isAfter(LocalDate.now().plusYears(1))){
                throw new BusinessException("O valor deve ser superior a 50 reais e a data final inferior a 1 ano.");
            }
        }
        this.chain.execute(payload,amount);
    }

    @Override
    public void next(RecurrenceChain next) {
        this.chain = next;
    }
}
