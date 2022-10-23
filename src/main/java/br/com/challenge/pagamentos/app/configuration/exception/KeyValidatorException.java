package br.com.challenge.pagamentos.app.configuration.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class KeyValidatorException extends RuntimeException{

    private String label;

    private String details;

    public KeyValidatorException(String message, Throwable cause){
        super(message, cause);
    }

    public KeyValidatorException(String message){
        super(message);
        this.label = "Erro na validação da chave!";
    }

}
