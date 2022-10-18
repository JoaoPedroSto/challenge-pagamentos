package br.com.challenge.pagamentos.app.configuration.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DetalhesErros {

    private String erro;
    @JsonInclude(Include.NON_NULL)
    private List<String> listaErros;
    @JsonInclude(Include.NON_NULL)
    private String mensagemException;
    @JsonInclude(Include.NON_NULL)
    private String detalhes;

    public DetalhesErros(String erro, String mensagem, String detalhes) {
        super();
        this.erro = erro;
        this.mensagemException = mensagem;
        this.detalhes = detalhes;
    }

    public DetalhesErros(String erro, List<String> erros){
        super();
        this.erro = erro;
        this.listaErros = erros;
    }

}
