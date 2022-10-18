package br.com.challenge.pagamentos.app.entrypoint.dto;

import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentosResponseDTO<T> {

    private String mensagem;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detalhes;
    private T body;
}
