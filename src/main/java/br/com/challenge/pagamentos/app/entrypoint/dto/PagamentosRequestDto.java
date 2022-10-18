package br.com.challenge.pagamentos.app.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentosRequestDto {
    @PastOrPresent(message = "Este campo deve estar no Passado ou Presente!")
    @NotNull(message = "Data de Inclusão é obrigatória!")
    private LocalDate inclusionDate;
    @NotNull(message = "Data de pagamento é obrigatória!")
    private LocalDate paymentDate;
    @NotNull(message = "Valor a ser pago é obrigatório!")
    private Float amount;
    private String description;
    private RecorrenciaDTO recurrenceDTO;
    @Valid @NotNull(message = "As informações do destinatário são obrigatórios!")
    private DestinatarioPixDTO receiverDTO;
}
