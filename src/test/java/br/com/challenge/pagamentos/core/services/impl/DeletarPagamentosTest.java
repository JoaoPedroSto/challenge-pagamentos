package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
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
public class DeletarPagamentosTest {

    @InjectMocks
    private DeleterPagamentoServiceImpl service;

    @Mock
    private PagamentosRepository repositoryMock;
    @Mock
    private KafkaProducer producerMock;


    @Test
    public void deletar_pagamento_test(){
        var id = UUID.randomUUID().toString();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(PagamentosEntity.builder().build()));
        doNothing().when(repositoryMock).delete(any(PagamentosEntity.class));
        doNothing().when(producerMock).send(any(PagamentosEntity.class), anyString());
        service.deletePagamento(id);
    }

}
