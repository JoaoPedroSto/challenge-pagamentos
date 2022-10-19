package br.com.challenge.pagamentos.core.entity.model;

import br.com.challenge.pagamentos.core.entity.enuns.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinatarioPixEntity implements Serializable {
    private static final long serialVersionUID = -8759014831695833750L;
    private String key;
    private TipoChave keyType;
}
