package br.com.challenge.pagamentos.app.entrypoint.dto;

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
public class RecorrenciaDTO implements Serializable {

    private static final long serialVersionUID = 5895922749068399471L;
    private LocalDate finalDate;
    private Frequencia frequency;
}
