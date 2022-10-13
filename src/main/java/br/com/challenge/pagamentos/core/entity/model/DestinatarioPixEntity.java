package br.com.challenge.pagamentos.core.entity.model;

import br.com.challenge.pagamentos.core.entity.enuns.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinatarioPixEntity {
    private String key;
    private TipoChave keyType;
}
