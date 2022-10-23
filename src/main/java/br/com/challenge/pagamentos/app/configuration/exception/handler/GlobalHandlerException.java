package br.com.challenge.pagamentos.app.configuration.exception.handler;

import br.com.challenge.pagamentos.app.configuration.exception.model.ErrorDatails;
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

@Slf4j
@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handlerBadRequest(Exception ex, WebRequest request){
        ErrorDatails erros = new ErrorDatails("Erro durante o Processamento!" ,ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(erros, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> response = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach( e -> {
            String message = e.getField() + ": " + e.getDefaultMessage();
            response.add(message);
        });
        return new ResponseEntity<>(new ErrorDatails("Ocorreram erros de validação!", response), HttpStatus.BAD_REQUEST);
    }

}
