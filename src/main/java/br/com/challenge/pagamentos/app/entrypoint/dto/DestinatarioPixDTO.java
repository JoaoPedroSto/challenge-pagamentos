package br.com.challenge.pagamentos.app.entrypoint.dto;

import br.com.challenge.pagamentos.core.entity.enuns.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinatarioPixDTO implements Serializable {

    private static final long serialVersionUID = 1669742738643988372L;
    @NotBlank(message = "Campo key é obrigatório!")
    private String key;
}
