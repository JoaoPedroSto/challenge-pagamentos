package br.com.challenge.pagamentos.app.entrypoint.mapper.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.mapper.PaymentsMapper;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentsMapperImpl implements PaymentsMapper {

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public PaymentsEntity PaymentsDtoToPaymentsEntity(PaymentsRequestDto paymentsRequestDto) {
        return mapper.map(paymentsRequestDto, PaymentsEntity.class);
    }
}
