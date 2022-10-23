package br.com.challenge.pagamentos.core.factory.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.ReceiverPixDTO;
import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.mapper.PaymentsMapper;
import br.com.challenge.pagamentos.app.entrypoint.validator.KeyValidator;
import br.com.challenge.pagamentos.core.entity.model.ReceiverPixEntity;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static br.com.challenge.pagamentos.core.entity.enuns.StatusPayment.AGENDADO;
import static br.com.challenge.pagamentos.core.entity.enuns.StatusPayment.EFETUADO;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FactoryTest {

    @InjectMocks
    private PaymentEntityFactoryImpl service;

    @Mock
    private PaymentsMapper mapper;

    @Mock
    private KeyValidator validator;

    private PaymentsRequestDto dto;
    private PaymentsEntity entity;

    @BeforeEach
    private void setup(){
        dto = PaymentsRequestDto
                .builder()
                .paymentDate(LocalDate.now())
                .receiverDTO(new ReceiverPixDTO(UUID.randomUUID().toString()))
                .build();
        entity = PaymentsEntity
                .builder()
                .paymentDate(dto.getPaymentDate())
                .receiver(ReceiverPixEntity.builder().key(dto.getReceiverDTO().getKey()).build())
                .build();
    }

    @Test
    public void factory_entity_efetuado_test(){
        entity.setPaymentDate(dto.getPaymentDate().minusDays(1));
        when(mapper.PaymentsDtoToPaymentsEntity(dto)).thenReturn(entity);
        doNothing().when(validator).validateKey(entity.getReceiver());
        var result = service.factoryEntity(dto);
        Assert.assertEquals(EFETUADO, result.getStatus());
    }

    @Test
    public void factory_entity_agendado_test(){
        entity.setPaymentDate(dto.getPaymentDate().plusDays(1));
        when(mapper.PaymentsDtoToPaymentsEntity(dto)).thenReturn(entity);
        var result = service.factoryEntity(dto);
        Assert.assertEquals(AGENDADO, result.getStatus());
    }

    @Test
    public void factory_update_entity_test(){
        when(mapper.PaymentsDtoToPaymentsEntity(dto)).thenReturn(entity);
        var result = service.factoryEntityUpdate(dto, entity);
    }

}
