package br.com.challenge.pagamentos.core.factory;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;


public interface PagamentosEntityFactory {

    public PagamentosEntity factoryEntity(PagamentosRequestDto dto);

    public PagamentosEntity factoryEntityUpdate(PagamentosRequestDto dto, PagamentosEntity entityBase);

}
