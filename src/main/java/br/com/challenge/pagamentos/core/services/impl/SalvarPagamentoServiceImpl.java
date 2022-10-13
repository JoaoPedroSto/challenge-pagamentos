package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.core.factory.PagamentosEntityFactory;
import br.com.challenge.pagamentos.core.services.SalvarPagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SalvarPagamentoServiceImpl implements SalvarPagamentoService {

    @Autowired
    private PagamentosEntityFactory factory;

    @Override
    public void persistPagamento(PagamentosRequestDto pagamentoDto) {
        var pagamentoEntity = factory.factoryEntity(pagamentoDto);
        log.info(pagamentoEntity.toString());
    }
}
