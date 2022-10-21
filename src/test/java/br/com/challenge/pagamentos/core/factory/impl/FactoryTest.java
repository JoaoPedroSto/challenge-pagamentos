package br.com.challenge.pagamentos.core.factory.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.DestinatarioPixDTO;
import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.mapper.PagamentosMapper;
import br.com.challenge.pagamentos.core.entity.model.DestinatarioPixEntity;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import br.com.challenge.pagamentos.core.validator.ChaveValidator;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static br.com.challenge.pagamentos.core.entity.enuns.StatusPagamento.AGENDADO;
import static br.com.challenge.pagamentos.core.entity.enuns.StatusPagamento.EFETUADO;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FactoryTest {

    @InjectMocks
    private PagamentosEntityFactoryImpl service;

    @Mock
    private PagamentosMapper mapper;

    @Mock
    private ChaveValidator validatorMock;

    private PagamentosRequestDto dto;
    private PagamentosEntity entity;

    @BeforeEach
    private void setup(){
        dto = PagamentosRequestDto
                .builder()
                .paymentDate(LocalDate.now())
                .receiverDTO(new DestinatarioPixDTO(UUID.randomUUID().toString()))
                .build();
        entity = PagamentosEntity
                .builder()
                .paymentDate(dto.getPaymentDate())
                .receiver(DestinatarioPixEntity.builder().key(dto.getReceiverDTO().getKey()).build())
                .build();
    }

    @Test
    public void factory_entity_efetuado_test(){
        entity.setPaymentDate(dto.getPaymentDate().minusDays(1));
        when(mapper.pagamentosDtoToPagamentosEntity(dto)).thenReturn(entity);
        doNothing().when(validatorMock).validarchave(entity.getReceiver());
        var result = service.factoryEntity(dto);
        Assert.assertEquals(EFETUADO, result.getStatus());
    }

    @Test
    public void factory_entity_agendado_test(){
        entity.setPaymentDate(dto.getPaymentDate().plusDays(1));
        when(mapper.pagamentosDtoToPagamentosEntity(dto)).thenReturn(entity);
        var result = service.factoryEntity(dto);
        Assert.assertEquals(AGENDADO, result.getStatus());
    }

    @Test
    public void factory_update_entity_test(){
        when(mapper.pagamentosDtoToPagamentosEntity(dto)).thenReturn(entity);
        var result = service.factoryEntityUpdate(dto, entity);
    }

}
