package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PaymentRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsResponseDTO;
import br.com.challenge.pagamentos.core.entity.enuns.StatusPayment;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import br.com.challenge.pagamentos.core.factory.PaymentEntityFactory;
import br.com.challenge.pagamentos.core.services.SavePaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SavePaymentServiceImpl implements SavePaymentService {

    @Autowired
    private PaymentEntityFactory factory;
    @Autowired
    private KafkaProducer producer;
    @Autowired
    private PaymentRepository repository;

    @Override
    public PaymentsResponseDTO persistPayment(PaymentsRequestDto paymentDto) {
        var paymentEntity = factory.factoryEntity(paymentDto);
        var duplicity = isDuplicity(paymentDto, paymentEntity);
        var response = repository.save(paymentEntity);
        producer.send(response, "SAVE");
        log.info(paymentEntity.toString());
        return PaymentsResponseDTO
                .builder()
                .message("Operação salva com sucesso")
                .body(response)
                .details(duplicity?"Operação já realizada anteriormente!":null)
                .build();
    }

    private boolean isDuplicity(PaymentsRequestDto paymentDto, PaymentsEntity paymentEntity) {
        return paymentEntity.getStatus() == StatusPayment.EFETUADO ?
                repository.existsByPaymentDateAndAmountAndReceiverKey(paymentDto.getPaymentDate(), paymentDto.getAmount(),
                        paymentDto.getReceiverDTO().getKey()) : false;
    }
}
