package br.com.challenge.pagamentos.app.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverPixDTO implements Serializable {

    private static final long serialVersionUID = 1669742738643988372L;
    @NotBlank(message = "A chave é obrigatória!")
    private String key;
}
