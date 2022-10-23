package br.com.challenge.pagamentos.app.dataprovider.repository;

import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentsEntity, String> {

    List<PaymentsEntity> findByStatus(String status);

    boolean existsByPaymentDateAndAmountAndReceiverKey(LocalDate payment, Float amount, String key);
}
