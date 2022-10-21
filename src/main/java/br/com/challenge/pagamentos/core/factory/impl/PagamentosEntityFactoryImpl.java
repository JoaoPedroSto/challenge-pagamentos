package br.com.challenge.pagamentos.core.factory.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.mapper.PagamentosMapper;
import br.com.challenge.pagamentos.core.entity.enuns.StatusPagamento;
import br.com.challenge.pagamentos.core.entity.enuns.TipoChave;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import br.com.challenge.pagamentos.core.factory.PagamentosEntityFactory;
import br.com.challenge.pagamentos.core.validator.ChaveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PagamentosEntityFactoryImpl implements PagamentosEntityFactory {

    @Autowired
    private PagamentosMapper mapper;
    @Autowired
    private ChaveValidator validator;

    @Override
    public PagamentosEntity factoryEntity(PagamentosRequestDto dto) {

        var entity = mapper.pagamentosDtoToPagamentosEntity(dto);
        var status = entity.getPaymentDate().isBefore(LocalDate.now().plusDays(1))
                ? StatusPagamento.EFETUADO
                : StatusPagamento.AGENDADO;
        entity.setStatus(status);
        entity.getReceiver().setKeyType(TipoChave.getTipoChave(dto.getReceiverDTO().getKey()));
        validator.validarchave(entity.getReceiver());
        return entity;
    }

    @Override
    public PagamentosEntity factoryEntityUpdate(PagamentosRequestDto dto, PagamentosEntity entityBase) {
        var entity = factoryEntity(dto);
        entityBase.setStatus(entity.getStatus());
        entityBase.setInclusionDate(entity.getInclusionDate());
        entityBase.setPaymentDate(entity.getPaymentDate());
        entityBase.setAmount(entity.getAmount());
        entityBase.setDescription(entity.getDescription());
        entityBase.setRecurrence(entity.getRecurrence());
        entityBase.setReceiver(entity.getReceiver());
        return entityBase;
    }
}
