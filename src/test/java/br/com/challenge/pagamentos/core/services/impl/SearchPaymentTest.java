package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.repository.PaymentRepository;
import br.com.challenge.pagamentos.app.entrypoint.filter.PaymentSearchFilter;
import br.com.challenge.pagamentos.core.entity.enuns.StatusPayment;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
public class SearchPaymentTest {

    @InjectMocks
    private FindPaymentServiceImpl service;
    @Mock
    private PaymentRepository repository;
    private PaymentsEntity entityAgendado;
    private PaymentsEntity entityEfetuado;
    private List<PaymentsEntity> list;


    @BeforeEach
    private void setup(){
        list = new ArrayList<>();
        entityAgendado = PaymentsEntity
                .builder()
                .id(UUID.randomUUID().toString())
                .status(StatusPayment.AGENDADO)
                .build();

        entityEfetuado = PaymentsEntity
                .builder()
                .id(UUID.randomUUID().toString())
                .status(StatusPayment.EFETUADO)
                .build();
    }

    @Test
    public void buscar_todos_pagamentos(){
        list.add(entityAgendado);
        list.add(entityEfetuado);
        when(repository.findAll()).thenReturn(list);
        var result = service.searchPayment(PaymentSearchFilter.TODOS);
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void buscar_pagamentos_agendados(){
        list.add(entityAgendado);
        when(repository.findByStatus(StatusPayment.AGENDADO.toString())).thenReturn(list);
        var result =  service.searchPayment(PaymentSearchFilter.AGENDADO);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getStatus(), StatusPayment.AGENDADO);
    }

    @Test
    public void buscar_pagamentos_efetuados(){
        list.add(entityEfetuado);
        when(repository.findByStatus(StatusPayment.EFETUADO.toString())).thenReturn(list);
        var result =  service.searchPayment(PaymentSearchFilter.EFETUADO);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getStatus(), StatusPayment.EFETUADO);
    }

    @Test
    public void buscar_por_id(){
        when(repository.findById(entityEfetuado.getId())).thenReturn(Optional.of(entityEfetuado));
        var result = service.searchById(entityEfetuado.getId());
        Assert.assertEquals(entityEfetuado, result.get());
    }

}
