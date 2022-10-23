package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PaymentRepository;
import br.com.challenge.pagamentos.core.services.DeletePaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class DeletePaymentServiceImpl implements DeletePaymentService {

    @Autowired
    private PaymentRepository repository;
    @Autowired
    private KafkaProducer producer;

    @Override
    public void deletePayment(String id) {
        var payment = repository.findById(id);
        if(payment.isPresent()){
            repository.delete(payment.get());
            producer.send(payment.get(), "DELETE");
        }else
            throw new BusinessException("ID não encontrado na base!");

    }
}
