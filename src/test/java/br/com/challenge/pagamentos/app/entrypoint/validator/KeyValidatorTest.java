package br.com.challenge.pagamentos.app.entrypoint.validator;

import br.com.challenge.pagamentos.app.configuration.exception.KeyValidatorException;
import br.com.challenge.pagamentos.core.entity.enuns.KeyType;
import br.com.challenge.pagamentos.core.entity.model.ReceiverPixEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
public class KeyValidatorTest {

    @InjectMocks
    private KeyValidator validator;

    private ReceiverPixEntity receiver;

    @Before
    public void setup(){
        receiver = ReceiverPixEntity
                .builder()
                .key("56938260150")
                .keyType(KeyType.CPF)
                .build();
    }

    @Test
    public void validar_cpf_sucesso(){
        validator.validateKey(receiver);
    }

    @Test(expected = KeyValidatorException.class)
    public void validar_cpf_falha(){
        receiver.setKey("56938260157");
        validator.validateKey(receiver);
    }

    @Test
    public void validar_uuid_sucesso(){
        receiver.setKey(UUID.randomUUID().toString());
        receiver.setKeyType(KeyType.ALEATORIA);
        validator.validateKey(receiver);
    }
    @Test(expected = KeyValidatorException.class)
    public void validar_uuid_erro(){
        receiver.setKey("aleatório!");
        validator.validateKey(receiver);
    }

}
