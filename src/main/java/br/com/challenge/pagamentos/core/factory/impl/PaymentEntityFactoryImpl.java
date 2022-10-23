package br.com.challenge.pagamentos.core.factory.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.mapper.PaymentsMapper;
import br.com.challenge.pagamentos.app.entrypoint.validator.KeyValidator;
import br.com.challenge.pagamentos.core.entity.enuns.StatusPayment;
import br.com.challenge.pagamentos.core.entity.enuns.KeyType;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import br.com.challenge.pagamentos.core.factory.PaymentEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PaymentEntityFactoryImpl implements PaymentEntityFactory {

    @Autowired
    private PaymentsMapper mapper;
    @Autowired
    private KeyValidator validator;

    @Override
    public PaymentsEntity factoryEntity(PaymentsRequestDto dto) {

        var entity = mapper.PaymentsDtoToPaymentsEntity(dto);
        var status = entity.getPaymentDate().isBefore(LocalDate.now().plusDays(1))
                ? StatusPayment.EFETUADO
                : StatusPayment.AGENDADO;
        entity.setStatus(status);
        entity.getReceiver().setKeyType(KeyType.getKeyType(dto.getReceiverDTO().getKey()));
        validator.validateKey(entity.getReceiver());
        return entity;
    }

    @Override
    public PaymentsEntity factoryEntityUpdate(PaymentsRequestDto dto, PaymentsEntity entityBase) {
        var entity = factoryEntity(dto);
        entityBase.setStatus(entity.getStatus());
        entityBase.setInclusionDate(entity.getInclusionDate());
        entityBase.setPaymentDate(entity.getPaymentDate());
        entityBase.setAmount(entity.getAmount());
        entityBase.setDescription(entity.getDescription());
        entityBase.setRecurrence(entity.getRecurrence());
        entityBase.setReceiver(entity.getReceiver());
        return entityBase;
    }
}
