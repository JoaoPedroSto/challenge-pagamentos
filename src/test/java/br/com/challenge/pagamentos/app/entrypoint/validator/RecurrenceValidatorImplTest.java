package br.com.challenge.pagamentos.app.entrypoint.validator;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.entrypoint.validator.impl.RecurrenceValidatorImpl;
import br.com.challenge.pagamentos.core.entity.enuns.Frequency;
import br.com.challenge.pagamentos.core.entity.model.RecurrenceEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class RecurrenceValidatorImplTest {

    @InjectMocks
    private RecurrenceValidatorImpl validator;

    private RecurrenceEntity entity;

    @Before
    public void setup(){
        entity = RecurrenceEntity
                    .builder()
                    .finalDate(LocalDate.now().plusMonths(5))
                    .frequency(Frequency.SEMESTRAL)
                    .build();
    }

    @Test
    public void validar_recorrencia_sucesso(){
        validator.validate(entity, (float) 200.0);
    }

    @Test(expected = BusinessException.class)
    public void validar_recorrencia_semanal_exception(){
        entity.setFrequency(Frequency.SEMANAL);
        entity.setFinalDate(LocalDate.now().plusMonths(13));
        validator.validate(entity, (float) 49.0);
    }
    @Test(expected = BusinessException.class)
    public void validar_recorrencia_mensal_exception(){
        entity.setFrequency(Frequency.MENSAL);
        entity.setFinalDate(LocalDate.now().plusMonths(25));
        validator.validate(entity, (float) 99.0);
    }
    @Test(expected = BusinessException.class)
    public void validar_recorrencia_trimestral_exception(){
        entity.setFrequency(Frequency.TRIMETRAL);
        entity.setFinalDate(LocalDate.now().plusMonths(37));
        validator.validate(entity, (float) 129.0);
    }
    @Test(expected = BusinessException.class)
    public void validar_recorrencia_semestral_exception(){
        entity.setFinalDate(LocalDate.now().plusMonths(49));
        validator.validate(entity, (float) 149.0);
    }



}
