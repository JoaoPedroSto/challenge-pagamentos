package br.com.challenge.pagamentos.core.entity.model;

import br.com.challenge.pagamentos.core.entity.enuns.StatusPagamento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class PagamentosEntity {
    @Id
    private Integer id;
    private StatusPagamento status;
    private LocalDate inclusionDate;
    private LocalDate paymentDate;
    private Float amount;
    private String description;
    private RecorrenciaEntity recurrence;
    private DestinatarioPixEntity receiver;
}
