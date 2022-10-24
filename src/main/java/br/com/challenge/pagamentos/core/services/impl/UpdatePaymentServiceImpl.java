package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PaymentRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.PaymentsRequestDto;
import br.com.challenge.pagamentos.core.factory.PaymentEntityFactory;
import br.com.challenge.pagamentos.core.services.UpdatePaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdatePaymentServiceImpl implements UpdatePaymentService {

    @Autowired
    private PaymentEntityFactory factory;
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private KafkaProducer producer;
    @Override
    public void updatePayment(PaymentsRequestDto paymentDto, String id) {
        var paymentData = repository.findById(id);
        if(paymentData.isPresent()){
            var entityUpdate = factory.factoryEntityUpdate(paymentDto, paymentData.get());
            repository.save(entityUpdate);
            producer.send(entityUpdate, "UPDATE");
        }else
            throw new BusinessException("Pagamento não existe na base!");
    }
}
