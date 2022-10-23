package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PaymentRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.ReceiverPixDTO;
import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.validator.RecurrenceValidator;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import br.com.challenge.pagamentos.core.factory.PaymentEntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UpdatePaymentTest {

    @InjectMocks
    private UpdatePaymentServiceImpl service;
    @Mock
    private PaymentEntityFactory factory;
    @Mock
    private RecurrenceValidator validator;
    @Mock
    private PaymentRepository repository;
    @Mock
    private KafkaProducer producer;

    private static PaymentsRequestDto dto;
    private static PaymentsEntity entity;

    @Before
    public void setup(){
        var receiver = ReceiverPixDTO
                .builder()
                .key(UUID.randomUUID().toString())
                .build();
        dto = PaymentsRequestDto
                .builder()
                .inclusionDate(LocalDate.now().minusDays(10))
                .paymentDate(LocalDate.now().minusDays(1))
                .amount((float) 300.0)
                .receiverDTO(receiver)
                .build();

        entity = PaymentsEntity
                .builder()
                .id("IDPagamento")
                .build();
    }

    @Test
    public void atualizar_pagamento_test(){
        doNothing().when(validator).validate(dto.getRecurrenceDTO(), dto.getAmount());
        doNothing().when(producer).send(any(PaymentsEntity.class), anyString());
        when(repository.findById("IDPagamento")).thenReturn(Optional.of(entity));
        ModelMapper mapper = new ModelMapper();
        var entityUpdate = mapper.map(dto, PaymentsEntity.class);
        entityUpdate.setId(entity.getId());
        when(factory.factoryEntityUpdate(dto, entity)).thenReturn(entityUpdate);
        when(repository.save(entityUpdate)).thenReturn(PaymentsEntity.builder().build());
        service.updatePayment(dto ,"IDPagamento");
    }

    @Test(expected = BusinessException.class)
    public void atualizar_pagamento_exception_test(){
        doNothing().when(validator).validate(dto.getRecurrenceDTO(), dto.getAmount());
        doNothing().when(producer).send(any(PaymentsEntity.class), anyString());
        when(repository.findById("IDPagamento")).thenReturn(Optional.empty());
        service.updatePayment(dto ,"IDPagamento");
    }
}
