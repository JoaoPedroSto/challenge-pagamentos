package br.com.challenge.pagamentos.app.configuration.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private String label;

    private String details;

    public BusinessException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

    public BusinessException(String mensagem){
        super(mensagem);
        this.label = "Erro na validação de negócios!";
    }

}
