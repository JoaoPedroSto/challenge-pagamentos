package br.com.challenge.pagamentos.core.configuration;

import br.com.challenge.pagamentos.app.entrypoint.dto.RecorrenciaDTO;
import br.com.challenge.pagamentos.core.strategy.RecurrenceChain;
import br.com.challenge.pagamentos.core.strategy.impl.MensalChain;
import br.com.challenge.pagamentos.core.strategy.impl.SemanalChain;
import br.com.challenge.pagamentos.core.strategy.impl.SemestralChain;
import br.com.challenge.pagamentos.core.strategy.impl.TrimestralChain;
import org.springframework.stereotype.Component;

import javax.validation.Validation;

@Component
public class RecorrenciaValidator {

    public void validate(RecorrenciaDTO payload, Float amount){
        RecurrenceChain validator = new SemanalChain();
        validator.linkWith(new MensalChain());
        validator.linkWith(new TrimestralChain());
        validator.linkWith(new SemestralChain());
        validator.validate(payload, amount);
    }

}
