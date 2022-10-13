package br.com.challenge.pagamentos.core.factory.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.mapper.PagamentosMapper;
import br.com.challenge.pagamentos.core.entity.enuns.StatusPagamento;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import br.com.challenge.pagamentos.core.factory.PagamentosEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PagamentosEntityFactoryImpl implements PagamentosEntityFactory {

    @Autowired
    private PagamentosMapper mapper;

    @Override
    public PagamentosEntity factoryEntity(PagamentosRequestDto dto) {

        var entity = mapper.pagamentosDtoToPagamentosEntity(dto);
        var status = entity.getPaymentDate().isBefore(LocalDate.now().plusDays(1))
                ? StatusPagamento.EFETUADO
                : StatusPagamento.AGENDADO;
        entity.setStatus(status);
        return entity;
    }
}
