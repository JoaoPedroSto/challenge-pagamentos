package br.com.challenge.pagamentos.app.dataprovider.kafka.message;

import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
public class KafkaMessage {

    private String operationType;
    private PaymentsEntity entity;

}
