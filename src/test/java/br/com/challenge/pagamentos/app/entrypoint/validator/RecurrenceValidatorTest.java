package br.com.challenge.pagamentos.app.entrypoint.validator;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.entrypoint.dto.RecurrenceDTO;
import br.com.challenge.pagamentos.core.entity.enuns.Frequency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class RecurrenceValidatorTest {

    @InjectMocks
    private RecurrenceValidator validator;

    private RecurrenceDTO dto;

    @Before
    public void setup(){
        dto = RecurrenceDTO
                .builder()
                .finalDate(LocalDate.now().plusMonths(5))
                .frequency(Frequency.SEMESTRAL)
                .build();
    }

    @Test
    public void validar_recorrencia_sucesso(){
        validator.validate(dto, (float) 200.0);
    }

    @Test(expected = BusinessException.class)
    public void validar_recorrencia_semanal_exception(){
        dto.setFrequency(Frequency.SEMANAL);
        dto.setFinalDate(LocalDate.now().plusMonths(13));
        validator.validate(dto, (float) 49.0);
    }
    @Test(expected = BusinessException.class)
    public void validar_recorrencia_mensal_exception(){
        dto.setFrequency(Frequency.MENSAL);
        dto.setFinalDate(LocalDate.now().plusMonths(25));
        validator.validate(dto, (float) 99.0);
    }
    @Test(expected = BusinessException.class)
    public void validar_recorrencia_trimestral_exception(){
        dto.setFrequency(Frequency.TRIMETRAL);
        dto.setFinalDate(LocalDate.now().plusMonths(37));
        validator.validate(dto, (float) 129.0);
    }
    @Test(expected = BusinessException.class)
    public void validar_recorrencia_semestral_exception(){
        dto.setFinalDate(LocalDate.now().plusMonths(49));
        validator.validate(dto, (float) 149.0);
    }



}
