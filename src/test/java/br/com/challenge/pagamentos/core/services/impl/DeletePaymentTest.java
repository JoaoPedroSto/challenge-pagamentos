package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PaymentRepository;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeletePaymentTest {

    @InjectMocks
    private DeletePaymentServiceImpl service;

    @Mock
    private PaymentRepository repository;
    @Mock
    private KafkaProducer producer;


    @Test
    public void deletar_pagamento_test(){
        var id = UUID.randomUUID().toString();
        when(repository.findById(id)).thenReturn(Optional.of(PaymentsEntity.builder().build()));
        doNothing().when(repository).delete(any(PaymentsEntity.class));
        doNothing().when(producer).send(any(PaymentsEntity.class), anyString());
        service.deletePayment(id);
    }

}
