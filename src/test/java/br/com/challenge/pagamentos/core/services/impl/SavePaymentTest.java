package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PaymentRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.ReceiverPixDTO;
import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.validator.RecurrenceValidator;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import br.com.challenge.pagamentos.core.factory.impl.PaymentEntityFactoryImpl;
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
class SavePaymentTest {

    @InjectMocks
    private SavePaymentServiceImpl salvarService;
    @Mock
    private RecurrenceValidator validatorMock;
    @Mock
    private PaymentEntityFactoryImpl factoryMock;
    @Mock
    private PaymentRepository repositoryMock;
    @Mock
    private KafkaProducer producerMock;

    private static PaymentsRequestDto request;
    private static PaymentsEntity entity;

    @BeforeAll
    private static void setup(){
        var receiver = ReceiverPixDTO.builder().key(UUID.randomUUID().toString()).build();
        request = PaymentsRequestDto
                .builder()
                .inclusionDate(LocalDate.now().minusDays(10))
                .paymentDate(LocalDate.now().minusDays(5))
                .amount((float)200.0)
                .description("Pagamento Churrasco!")
                .receiverDTO(receiver)
                .build();

        entity = PaymentsEntity
                .builder()
                .id(UUID.randomUUID().toString())
                .build();

        entity = new ModelMapper().map(request, PaymentsEntity.class);
    }

    @Test
    public void salvar_cliente_sucesso(){
        doNothing().when(validatorMock).validate(request.getRecurrenceDTO(),request.getAmount());
        doNothing().when(producerMock).send(any(PaymentsEntity.class), anyString());
        when(repositoryMock.existsByPaymentDateAndAmountAndReceiverKey(
                request.getPaymentDate(), request.getAmount(),request.getReceiverDTO().getKey()
        )).thenReturn(false);
        when(factoryMock.factoryEntity(request)).thenReturn(entity);
        when(repositoryMock.save(entity)).thenReturn(entity);
        var result = salvarService.persistPayment(request);
        Assert.assertEquals("Operação salva com sucesso", result.getMessage());
    }

    @Test
    public void salvar_cliente_sucesso_notifica_duplicidate(){
        doNothing().when(validatorMock).validate(request.getRecurrenceDTO(),request.getAmount());
        doNothing().when(producerMock).send(any(PaymentsEntity.class), anyString());
        when(repositoryMock.existsByPaymentDateAndAmountAndReceiverKey(
                request.getPaymentDate(), request.getAmount(),request.getReceiverDTO().getKey()
        )).thenReturn(true);
        when(factoryMock.factoryEntity(request)).thenReturn(entity);
        when(repositoryMock.save(entity)).thenReturn(entity);
        var result = salvarService.persistPayment(request);
        Assert.assertEquals("Operação salva com sucesso", result.getMessage());
        Assert.assertEquals("Operação já realizada anteriormente!", result.getDetails());
    }


}
