package br.com.challenge.pagamentos.app.dataprovider.kafka;

import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;

public interface KafkaProducerInterface {

    void send(PaymentsEntity paymentEntity, String operation);

}
