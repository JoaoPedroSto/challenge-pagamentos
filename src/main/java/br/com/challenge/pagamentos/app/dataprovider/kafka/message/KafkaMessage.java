package br.com.challenge.pagamentos.app.dataprovider.kafka.message;

import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
public class KafkaMessage {

    private String tipoOperacao;
    private PagamentosEntity entity;

}
