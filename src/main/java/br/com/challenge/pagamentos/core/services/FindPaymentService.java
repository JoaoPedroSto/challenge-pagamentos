package br.com.challenge.pagamentos.core.services;

import br.com.challenge.pagamentos.app.entrypoint.filter.PaymentSearchFilter;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;

import java.util.List;
import java.util.Optional;

public interface FindPaymentService {
    List<PaymentsEntity> searchPayment(PaymentSearchFilter filter);

    Optional<PaymentsEntity> searchById(String id);
}
