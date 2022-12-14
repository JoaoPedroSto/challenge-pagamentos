package br.com.challenge.pagamentos.core.entity.model;

import br.com.challenge.pagamentos.core.entity.enuns.StatusPayment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
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
public class PaymentsEntity implements Serializable{
    private static final long serialVersionUID = -6035432439817008047L;
    @Id
    private String id;
    private StatusPayment status;
    private LocalDate inclusionDate;
    private LocalDate paymentDate;
    private Float amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RecurrenceEntity recurrence;
    private ReceiverPixEntity receiver;

}
