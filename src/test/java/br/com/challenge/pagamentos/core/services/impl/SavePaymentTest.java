package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PaymentRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.ReceiverPixDTO;
import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.core.entity.enuns.StatusPayment;
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
    private PaymentEntityFactoryImpl factory;
    @Mock
    private PaymentRepository repository;
    @Mock
    private KafkaProducer producer;

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

        entity = new ModelMapper().map(request, PaymentsEntity.class);
        entity.setId(UUID.randomUUID().toString());
        entity.setStatus(StatusPayment.EFETUADO);
    }

    @Test
    public void salvar_cliente_sucesso(){
        doNothing().when(producer).send(any(PaymentsEntity.class), anyString());
        when(repository.existsByPaymentDateAndAmountAndReceiverKey(
                request.getPaymentDate(), request.getAmount(),request.getReceiverDTO().getKey()
        )).thenReturn(false);
        when(factory.factoryEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        var result = salvarService.persistPayment(request);
        Assert.assertEquals("Operação salva com sucesso", result.getMessage());
    }

    @Test
    public void salvar_cliente_sucesso_notifica_duplicidate(){
        doNothing().when(producer).send(any(PaymentsEntity.class), anyString());
        when(repository.existsByPaymentDateAndAmountAndReceiverKey(
                request.getPaymentDate(), request.getAmount(),request.getReceiverDTO().getKey()
        )).thenReturn(true);
        when(factory.factoryEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        var result = salvarService.persistPayment(request);
        Assert.assertEquals("Operação salva com sucesso", result.getMessage());
        Assert.assertEquals("Operação já realizada anteriormente!", result.getDetails());
    }

}
