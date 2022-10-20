package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.DestinatarioPixDTO;
import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import br.com.challenge.pagamentos.core.factory.PagamentosEntityFactory;
import br.com.challenge.pagamentos.core.validator.RecorrenciaValidator;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AtualizarPagamentosTest {

    @InjectMocks
    private AtualizarPagamentoServiceImpl service;
    @Mock
    private PagamentosEntityFactory factory;
    @Mock
    private RecorrenciaValidator validator;
    @Mock
    private PagamentosRepository repository;

    private static PagamentosRequestDto dto;
    private static PagamentosEntity entity;

    @Before
    public void setup(){
        var receiver = DestinatarioPixDTO
                .builder()
                .key(UUID.randomUUID().toString())
                .build();
        dto = PagamentosRequestDto
                .builder()
                .inclusionDate(LocalDate.now().minusDays(10))
                .paymentDate(LocalDate.now().minusDays(1))
                .amount((float) 300.0)
                .receiverDTO(receiver)
                .build();

        entity = PagamentosEntity
                .builder()
                .id("IDPagamento")
                .build();
    }

    @Test
    public void atualizar_pagamento_test(){
        doNothing().when(validator).validate(dto.getRecurrenceDTO(), dto.getAmount());
        when(repository.findById("IDPagamento")).thenReturn(Optional.of(entity));
        ModelMapper mapper = new ModelMapper();
        var entityUpdate = mapper.map(dto, PagamentosEntity.class);
        entityUpdate.setId(entity.getId());
        when(factory.factoryEntityUpdate(dto, entity)).thenReturn(entityUpdate);
        when(repository.save(entityUpdate)).thenReturn(PagamentosEntity.builder().build());
        service.updatePagamento(dto ,"IDPagamento");
    }

    @Test(expected = BusinessException.class)
    public void atualizar_pagamento_exception_test(){
        doNothing().when(validator).validate(dto.getRecurrenceDTO(), dto.getAmount());
        when(repository.findById("IDPagamento")).thenReturn(Optional.empty());
        service.updatePagamento(dto ,"IDPagamento");
    }
}
