package br.com.challenge.pagamentos.core.entity.model;

import br.com.challenge.pagamentos.core.entity.enuns.StatusPagamento;
import lombok.*;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "pagamentos")
public class PagamentosEntity implements Serializable{
    private static final long serialVersionUID = -6035432439817008047L;
    @Id
    private String id;
    private StatusPagamento status;
    private LocalDate inclusionDate;
    private LocalDate paymentDate;
    private Float amount;
    private String description;
    private RecorrenciaEntity recurrence;
    private DestinatarioPixEntity receiver;

}
