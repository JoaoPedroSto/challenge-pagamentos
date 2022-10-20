package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeletarPagamentosTest {

    @InjectMocks
    private DeleterPagamentoServiceImpl service;

    @Mock
    private PagamentosRepository repositoryMock;


    @Test
    public void deletar_pagamento_test(){
        var id = UUID.randomUUID().toString();
        when(repositoryMock.findById(id)).thenReturn(Optional.of(PagamentosEntity.builder().build()));
        doNothing().when(repositoryMock).delete(any(PagamentosEntity.class));
        service.deletePagamento(id);
    }

}
