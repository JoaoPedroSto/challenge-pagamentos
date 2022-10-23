package br.com.challenge.pagamentos.app.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsResponseDTO<T> {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String details;
    private T body;
}
