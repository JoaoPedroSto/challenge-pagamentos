package br.com.challenge.pagamentos.core.factory;

import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;


public interface PaymentEntityFactory {

    public PaymentsEntity factoryEntity(PaymentsRequestDto dto);

    public PaymentsEntity factoryEntityUpdate(PaymentsRequestDto dto, PaymentsEntity entityBase);

}
