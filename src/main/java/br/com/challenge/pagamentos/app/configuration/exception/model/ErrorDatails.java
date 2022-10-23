package br.com.challenge.pagamentos.app.configuration.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import lombok.Data;

import java.util.List;

@Data
public class ErrorDatails {

    private String error;
    @JsonInclude(Include.NON_NULL)
    private List<String> errorList;
    @JsonInclude(Include.NON_NULL)
    private String messageException;
    @JsonInclude(Include.NON_NULL)
    private String details;

    public ErrorDatails(String error, String message, String details) {
        super();
        this.error = error;
        this.messageException = message;
        this.details = details;
    }

    public ErrorDatails(String error, List<String> errorList){
        super();
        this.error = error;
        this.errorList = errorList;
    }

}
