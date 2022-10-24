package br.com.challenge.pagamentos.app.entrypoint.validator;

import br.com.challenge.pagamentos.core.entity.model.RecurrenceEntity;

public interface RecurrenceValidator {
    void validate(RecurrenceEntity payload, Float amount);
}
