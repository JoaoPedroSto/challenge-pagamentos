package br.com.challenge.pagamentos.app.dataprovider.kafka;

import br.com.challenge.pagamentos.app.dataprovider.kafka.message.KafkaMessage;
import br.com.challenge.pagamentos.core.entity.model.PaymentsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducerInterface {
    @Value("${topic.name.producer}")
    private String topic;
    @Autowired
    private final KafkaTemplate<String, String> template;

    public void send(PaymentsEntity paymentEntity, String operation){
        var message = KafkaMessage.builder().operationType(operation).entity(paymentEntity).build();
        log.info("Payload enviado: {}", paymentEntity.toString());
        template.send(topic, message.toString());
    }

}
