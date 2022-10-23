package br.com.challenge.pagamentos.app.entrypoint.mapper;

import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import org.springframework.stereotype.Component;

@Component
public interface PaymentsMapper {

    PaymentsEntity PaymentsDtoToPaymentsEntity(PaymentsRequestDto paymentsRequestDto);

}
