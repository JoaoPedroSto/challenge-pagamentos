package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.app.entrypoint.filter.FiltroBuscaPagamento;
import br.com.challenge.pagamentos.core.entity.enuns.StatusPagamento;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
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
public class BuscarPagamentosTest {

    @InjectMocks
    private BuscarPagamentoServiceImpl service;
    @Mock
    private PagamentosRepository repositoryMock;

    private PagamentosEntity entityAgendado;
    private PagamentosEntity entityEfetuado;
    private List<PagamentosEntity> lista;


    @BeforeEach
    private void setup(){
        lista = new ArrayList<>();
        entityAgendado = PagamentosEntity
                .builder()
                .id(UUID.randomUUID().toString())
                .status(StatusPagamento.AGENDADO)
                .build();

        entityEfetuado = PagamentosEntity
                .builder()
                .id(UUID.randomUUID().toString())
                .status(StatusPagamento.EFETUADO)
                .build();
    }

    @Test
    public void buscar_todos_pagamentos(){
        lista.add(entityAgendado);
        lista.add(entityEfetuado);
        when(repositoryMock.findAll()).thenReturn(lista);
        var result = service.searchPagamentos(FiltroBuscaPagamento.TODOS);
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void buscar_pagamentos_agendados(){
        lista.add(entityAgendado);
        when(repositoryMock.findByStatus(StatusPagamento.AGENDADO.toString())).thenReturn(lista);
        var result =  service.searchPagamentos(FiltroBuscaPagamento.AGENDADO);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getStatus(), StatusPagamento.AGENDADO);
    }

    @Test
    public void buscar_pagamentos_efetuados(){
        lista.add(entityEfetuado);
        when(repositoryMock.findByStatus(StatusPagamento.EFETUADO.toString())).thenReturn(lista);
        var result =  service.searchPagamentos(FiltroBuscaPagamento.EFETUADO);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getStatus(), StatusPagamento.EFETUADO);
    }

    @Test
    public void buscar_por_id(){
        when(repositoryMock.findById(entityEfetuado.getId())).thenReturn(Optional.of(entityEfetuado));
        var result = service.searchById(entityEfetuado.getId());
        Assert.assertEquals(entityEfetuado, result.get());
    }

}
