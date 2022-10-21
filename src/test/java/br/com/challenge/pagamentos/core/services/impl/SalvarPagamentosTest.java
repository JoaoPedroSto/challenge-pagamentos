package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.DestinatarioPixDTO;
import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import br.com.challenge.pagamentos.core.factory.impl.PagamentosEntityFactoryImpl;
import br.com.challenge.pagamentos.core.validator.RecorrenciaValidator;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class SalvarPagamentosTest {

    @InjectMocks
    private SalvarPagamentoServiceImpl salvarService;
    @Mock
    private RecorrenciaValidator validatorMock;
    @Mock
    private PagamentosEntityFactoryImpl factoryMock;
    @Mock
    private PagamentosRepository repositoryMock;
    @Mock
    private KafkaProducer producerMock;

    private static PagamentosRequestDto request;
    private static PagamentosEntity entity;

    @BeforeAll
    private static void setup(){
        var receiver = DestinatarioPixDTO.builder().key(UUID.randomUUID().toString()).build();
        request = PagamentosRequestDto
                .builder()
                .inclusionDate(LocalDate.now().minusDays(10))
                .paymentDate(LocalDate.now().minusDays(5))
                .amount((float)200.0)
                .description("Pagamento Churrasco!")
                .receiverDTO(receiver)
                .build();

        entity = PagamentosEntity
                .builder()
                .id(UUID.randomUUID().toString())
                .build();

        entity = new ModelMapper().map(request, PagamentosEntity.class);
    }

    @Test
    public void salvar_cliente_sucesso(){
        doNothing().when(validatorMock).validate(request.getRecurrenceDTO(),request.getAmount());
        doNothing().when(producerMock).send(any(PagamentosEntity.class), anyString());
        when(repositoryMock.existsByPaymentDateAndAmountAndReceiverKey(
                request.getPaymentDate(), request.getAmount(),request.getReceiverDTO().getKey()
        )).thenReturn(false);
        when(factoryMock.factoryEntity(request)).thenReturn(entity);
        when(repositoryMock.save(entity)).thenReturn(entity);
        var result = salvarService.persistPagamento(request);
        Assert.assertEquals("Operação salva com sucesso", result.getMensagem());
    }

    @Test
    public void salvar_cliente_sucesso_notifica_duplicidate(){
        doNothing().when(validatorMock).validate(request.getRecurrenceDTO(),request.getAmount());
        doNothing().when(producerMock).send(any(PagamentosEntity.class), anyString());
        when(repositoryMock.existsByPaymentDateAndAmountAndReceiverKey(
                request.getPaymentDate(), request.getAmount(),request.getReceiverDTO().getKey()
        )).thenReturn(true);
        when(factoryMock.factoryEntity(request)).thenReturn(entity);
        when(repositoryMock.save(entity)).thenReturn(entity);
        var result = salvarService.persistPagamento(request);
        Assert.assertEquals("Operação salva com sucesso", result.getMensagem());
        Assert.assertEquals("Operação já realizada anteriormente!", result.getDetalhes());
    }


}
