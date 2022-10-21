package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosResponseDTO;
import br.com.challenge.pagamentos.core.factory.PagamentosEntityFactory;
import br.com.challenge.pagamentos.core.services.SalvarPagamentoService;
import br.com.challenge.pagamentos.core.validator.RecorrenciaValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SalvarPagamentoServiceImpl implements SalvarPagamentoService {

    @Autowired
    private PagamentosEntityFactory factory;
    @Autowired
    private RecorrenciaValidator validator;
    @Autowired
    private KafkaProducer producer;
    @Autowired
    private PagamentosRepository repository;

    @Override
    public PagamentosResponseDTO persistPagamento(PagamentosRequestDto pagamentoDto) {
        validator.validate(pagamentoDto.getRecurrenceDTO(), pagamentoDto.getAmount());
        var pagamentoEntity = factory.factoryEntity(pagamentoDto);
        var duplicity = repository.existsByPaymentDateAndAmountAndReceiverKey(
                pagamentoDto.getPaymentDate(),
                pagamentoDto.getAmount(),
                pagamentoDto.getReceiverDTO().getKey()
        );
        var response = repository.save(pagamentoEntity);
        producer.send(response, "SAVE");
        log.info(pagamentoEntity.toString());
        return PagamentosResponseDTO
                .builder()
                .mensagem("Operação salva com sucesso")
                .body(response)
                .detalhes(duplicity?"Operação já realizada anteriormente!":null)
                .build();
    }
}
