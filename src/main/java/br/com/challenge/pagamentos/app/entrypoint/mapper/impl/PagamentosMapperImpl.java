package br.com.challenge.pagamentos.app.entrypoint.mapper.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.mapper.PagamentosMapper;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PagamentosMapperImpl implements PagamentosMapper {

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public PagamentosRequestDto pagamentosEntityToPagamentosDTO(PagamentosEntity pagamentosEntity) {
        return mapper.map(pagamentosEntity, PagamentosRequestDto.class);
    }

    @Override
    public PagamentosEntity pagamentosDtoToPagamentosEntity(PagamentosRequestDto pagamentosRequestDto) {
        return mapper.map(pagamentosRequestDto, PagamentosEntity.class);
    }
}
