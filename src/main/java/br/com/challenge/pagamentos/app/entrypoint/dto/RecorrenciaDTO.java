package br.com.challenge.pagamentos.app.entrypoint.dto;

import br.com.challenge.pagamentos.core.entity.enuns.Frequencia;

import java.io.Serializable;
import java.time.LocalDate;

public class RecorrenciaDTO implements Serializable {

    private static final long serialVersionUID = 5895922749068399471L;
    private LocalDate finalDate;
    private Frequencia frequency;
}
