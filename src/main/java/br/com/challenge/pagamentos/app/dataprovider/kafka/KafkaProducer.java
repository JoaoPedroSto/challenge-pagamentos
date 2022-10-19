package br.com.challenge.pagamentos.app.dataprovider.kafka;

import br.com.challenge.pagamentos.app.dataprovider.kafka.serializer.PagamentosSerializer;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    @Value("${topic.name.producer}")
    private String topic;

    private final KafkaTemplate<String, String> template;

    public void send(PagamentosEntity pagamento){
        log.info("Payload enviado: {}", pagamento.toString());
        template.send(topic, pagamento.toString());
    }

}
