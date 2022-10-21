package br.com.challenge.pagamentos.core.services.validator;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.core.entity.enuns.TipoChave;
import br.com.challenge.pagamentos.core.entity.model.DestinatarioPixEntity;
import br.com.challenge.pagamentos.core.validator.ChaveValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
public class ChaveValidatorTest {

    @InjectMocks
    private ChaveValidator validator;

    private DestinatarioPixEntity receiver;

    @Before
    public void setup(){
        receiver = DestinatarioPixEntity
                .builder()
                .key("56938260150")
                .keyType(TipoChave.CPF)
                .build();
    }

    @Test
    public void validar_cpf_sucesso(){
        validator.validarchave(receiver);
    }

    @Test(expected = BusinessException.class)
    public void validar_cpf_falha(){
        receiver.setKey("56938260157");
        validator.validarchave(receiver);
    }

    @Test
    public void validar_uuid_sucesso(){
        receiver.setKey(UUID.randomUUID().toString());
        receiver.setKeyType(TipoChave.ALEATORIA);
        validator.validarchave(receiver);
    }
    @Test(expected = BusinessException.class)
    public void validar_uuid_erro(){
        receiver.setKey("aleatório!");
        validator.validarchave(receiver);
    }

}
