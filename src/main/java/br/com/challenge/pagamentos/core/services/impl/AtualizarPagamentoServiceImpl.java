package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.core.factory.PagamentosEntityFactory;
import br.com.challenge.pagamentos.core.services.AtualizarPagamentoService;
import br.com.challenge.pagamentos.core.validator.RecorrenciaValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AtualizarPagamentoServiceImpl implements AtualizarPagamentoService {

    @Autowired
    private PagamentosEntityFactory factory;
    @Autowired
    private RecorrenciaValidator validator;
    @Autowired
    private PagamentosRepository repository;
    @Autowired
    private KafkaProducer producer;
    @Override
    public void updatePagamento(PagamentosRequestDto pagamentosDto, String id) {
        validator.validate(pagamentosDto.getRecurrenceDTO(), pagamentosDto.getAmount());
        var pagamentoBase = repository.findById(id);
        if(pagamentoBase.isPresent()){
            var entityUpdate = factory.factoryEntityUpdate(pagamentosDto, pagamentoBase.get());
            repository.save(entityUpdate);
            producer.send(entityUpdate, "UPDATE");
        }else
            throw new BusinessException("Pagamento não existe na base!");
    }
}
