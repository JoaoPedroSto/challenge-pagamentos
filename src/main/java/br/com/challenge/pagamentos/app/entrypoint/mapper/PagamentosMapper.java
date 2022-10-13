package br.com.challenge.pagamentos.app.entrypoint.mapper;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import org.springframework.stereotype.Component;

@Component
public interface PagamentosMapper {

    PagamentosRequestDto pagamentosEntityToPagamentosDTO(PagamentosEntity pagamentosEntity);
    PagamentosEntity pagamentosDtoToPagamentosEntity(PagamentosRequestDto pagamentosRequestDto);

}
