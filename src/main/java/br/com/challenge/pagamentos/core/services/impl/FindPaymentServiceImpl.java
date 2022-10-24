package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.repository.PaymentRepository;
import br.com.challenge.pagamentos.app.entrypoint.filter.PaymentSearchFilter;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import br.com.challenge.pagamentos.core.services.FindPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FindPaymentServiceImpl implements FindPaymentService {

    @Autowired
    private PaymentRepository repository;
    @Override
    public List<PaymentsEntity> searchPayment(PaymentSearchFilter filter) {
        if(filter.equals(PaymentSearchFilter.TODOS))
            return repository.findAll();
        return repository.findByStatus(filter.toString());
    }

    @Override
    public Optional<PaymentsEntity> searchById(String id) {
        return repository.findById(id);
    }
}
