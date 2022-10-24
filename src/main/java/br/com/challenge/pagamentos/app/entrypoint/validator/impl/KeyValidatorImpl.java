package br.com.challenge.pagamentos.app.entrypoint.validator.impl;

import br.com.challenge.pagamentos.app.configuration.exception.KeyValidatorException;
import br.com.challenge.pagamentos.app.entrypoint.validator.KeyValidator;
import br.com.challenge.pagamentos.core.entity.enuns.KeyType;
import br.com.challenge.pagamentos.core.entity.model.ReceiverPixEntity;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.UUID;

@Component
public class KeyValidatorImpl implements KeyValidator {

    private final String ERROR_CPF = "CPF informado é inválido!";
    private final String ERROR_UUID = "Chave aleatória não segue o padrão do UUID!";
    private final String ERROR_KEY_NULL = "Tipo de chave errado!";

    private void CPFValidator(String CPF) {
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            throw new KeyValidatorException(ERROR_CPF);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 != CPF.charAt(9)) || (dig11 != CPF.charAt(10)))
                throw new KeyValidatorException(ERROR_CPF);
        } catch (InputMismatchException erro) {
            throw new KeyValidatorException(ERROR_CPF);
        }
    }

    private void UUIDValidator(String key){
        try{
            var result = UUID.fromString(key);
        }catch(Exception e){
            throw new KeyValidatorException(ERROR_UUID);
        }
    }

    public void validateKey(ReceiverPixEntity receiver){
        if(receiver.getKeyType() == KeyType.CPF){
            CPFValidator(receiver.getKey());
        } else if (receiver.getKeyType() == KeyType.ALEATORIA) {
            UUIDValidator(receiver.getKey());
        } else if (receiver.getKeyType() == null){
            throw new KeyValidatorException(ERROR_KEY_NULL);
        }
    }


}
