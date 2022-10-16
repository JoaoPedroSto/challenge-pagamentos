package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.core.configuration.RecorrenciaValidator;
import br.com.challenge.pagamentos.core.factory.PagamentosEntityFactory;
import br.com.challenge.pagamentos.core.services.AtualizarPagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AtualizarPagamentoServiceImpl implements AtualizarPagamentoService {

    @Autowired
    PagamentosEntityFactory factory;
    @Autowired
    RecorrenciaValidator validator;
    @Autowired
    PagamentosRepository repository;

    @Override
    public void updatePagamento(PagamentosRequestDto pagamentosDto, String id) {
        validator.validate(pagamentosDto.getRecurrenceDTO(), pagamentosDto.getAmount());
        var pagamentoBase = repository.findById(id);
        if(pagamentoBase.isPresent()){
            var entityUpdate = factory.factoryEntityUpdate(pagamentosDto, pagamentoBase.get());
            repository.save(entityUpdate);
        }
    }
}
