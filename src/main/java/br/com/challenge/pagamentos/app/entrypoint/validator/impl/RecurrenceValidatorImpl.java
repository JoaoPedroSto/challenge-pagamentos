package br.com.challenge.pagamentos.app.entrypoint.validator.impl;

import br.com.challenge.pagamentos.app.entrypoint.validator.RecurrenceValidator;
import br.com.challenge.pagamentos.core.entity.model.RecurrenceEntity;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;
import br.com.challenge.pagamentos.core.strategy.impl.MensalChain;
import br.com.challenge.pagamentos.core.strategy.impl.SemanalChain;
import br.com.challenge.pagamentos.core.strategy.impl.SemestralChain;
import br.com.challenge.pagamentos.core.strategy.impl.TrimestralChain;
import org.springframework.stereotype.Component;

@Component
public class RecurrenceValidatorImpl implements RecurrenceValidator {

    public void validate(RecurrenceEntity payload, Float amount){
        RecurrenceChain validatorSemanal = new SemanalChain();
        RecurrenceChain validatorMensal = new MensalChain();
        RecurrenceChain validatorTrimestral = new TrimestralChain();
        RecurrenceChain validatorSemestral = new SemestralChain();

        validatorSemanal.next(validatorMensal);
        validatorMensal.next(validatorTrimestral);
        validatorTrimestral.next(validatorSemestral);
        validatorSemanal.execute(payload, amount);
    }

}
