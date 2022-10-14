package br.com.challenge.pagamentos.core.strategy;

import br.com.challenge.pagamentos.app.entrypoint.dto.RecorrenciaDTO;

import javax.validation.Validation;
import javax.validation.Validator;

public abstract class RecurrenceChain<T> {

    private RecurrenceChain<T> next;

    protected abstract void execute(RecorrenciaDTO payload, Float amount);
    private void next(RecorrenciaDTO data,Float amount) {
        if (this.next != null) {
            this.next.validate(data, amount);
        }
    }

    public void validate (RecorrenciaDTO payload, Float amount){
        this.execute(payload, amount);
        this.next(payload, amount);
    }

    public RecurrenceChain<T> linkWith(RecurrenceChain<T> next){
        this.next = next;
        return next;
    }
}
