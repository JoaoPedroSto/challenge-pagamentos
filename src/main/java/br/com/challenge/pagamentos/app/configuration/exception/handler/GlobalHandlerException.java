package br.com.challenge.pagamentos.app.configuration.exception.handler;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.configuration.exception.model.DetalhesErros;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handlerBadRequest(Exception ex, WebRequest request){
        DetalhesErros erros = new DetalhesErros("Erro durante o Processamento na classe: " + ex.getClass() ,ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(erros, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> resposta = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach( e -> {
            String mensagem = e.getField() + ": " + e.getDefaultMessage();
            resposta.add(mensagem);
        });
        return new ResponseEntity<>(new DetalhesErros("Ocorreram erros de validação!", resposta), HttpStatus.BAD_REQUEST);
    }

}
