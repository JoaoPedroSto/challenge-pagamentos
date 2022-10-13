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
    @PastOrPresent(message = "Data de inclusão deve ser no Passado ou Presente!")
    @NotNull(message = "Campo inclusionDate é obrigatória!")
    private LocalDate inclusionDate;
    @NotNull(message = "Campo paymentDate é obrigatório!")
    private LocalDate paymentDate;
    @NotNull(message = "Campo amount é obrigatório!")
    private Float amount;
    private String description;
    private RecorrenciaDTO recurrenceDTO;
    @Valid @NotNull(message = "Campo receiver é obrigatório!")
    private DestinatarioPixDTO receiverDTO;
}
