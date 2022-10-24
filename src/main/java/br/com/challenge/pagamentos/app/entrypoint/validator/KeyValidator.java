package br.com.challenge.pagamentos.app.entrypoint.validator;

import br.com.challenge.pagamentos.core.entity.model.ReceiverPixEntity;

public interface KeyValidator {

    void validateKey(ReceiverPixEntity receiver);
}
