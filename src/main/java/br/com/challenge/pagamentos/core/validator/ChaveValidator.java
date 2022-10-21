package br.com.challenge.pagamentos.core.validator;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.core.entity.enuns.TipoChave;
import br.com.challenge.pagamentos.core.entity.model.DestinatarioPixEntity;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.UUID;

@Component
public class ChaveValidator {

    private final String ERRO_CPF = "CPF informado é inválido!";
    private final String ERRO_UUID= "Chave aleatória não segue o padrão do UUID!";
    private final String ERRO_CHAVE_NULA = "Tipo de chave errado!";

    private void CPFValidator(String CPF) {
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            throw new BusinessException(ERRO_CPF);

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
                throw new BusinessException(ERRO_CPF);
        } catch (InputMismatchException erro) {
            throw new BusinessException(ERRO_CPF);
        }
    }

    private void UUIDValidator(String chave){
        try{
            var result = UUID.fromString(chave);
        }catch(Exception e){
            throw new BusinessException(ERRO_UUID);
        }
    }

    public void validarchave(DestinatarioPixEntity receiver){
        if(receiver.getKeyType() == TipoChave.CPF){
            CPFValidator(receiver.getKey());
        } else if (receiver.getKeyType() == TipoChave.ALEATORIA) {
            UUIDValidator(receiver.getKey());
        } else if (receiver.getKeyType() == null){
            throw new BusinessException(ERRO_CHAVE_NULA);
        }
    }


}
