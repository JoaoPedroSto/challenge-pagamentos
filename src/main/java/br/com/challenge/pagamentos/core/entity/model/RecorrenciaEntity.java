package br.com.challenge.pagamentos.core.entity.model;

import br.com.challenge.pagamentos.core.entity.enuns.Frequencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecorrenciaEntity implements Serializable {

    private static final long serialVersionUID = 4330518515868807047L;
    private LocalDate finalDate;
    private Frequencia frequency;
}
