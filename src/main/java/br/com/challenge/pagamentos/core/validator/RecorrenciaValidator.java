package br.com.challenge.pagamentos.core.validator;

import br.com.challenge.pagamentos.app.entrypoint.dto.RecorrenciaDTO;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;
import br.com.challenge.pagamentos.core.strategy.impl.MensalChain;
import br.com.challenge.pagamentos.core.strategy.impl.SemanalChain;
import br.com.challenge.pagamentos.core.strategy.impl.SemestralChain;
import br.com.challenge.pagamentos.core.strategy.impl.TrimestralChain;
import org.springframework.stereotype.Component;

@Component
public class RecorrenciaValidator {

    public void validate(RecorrenciaDTO payload, Float amount){
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
