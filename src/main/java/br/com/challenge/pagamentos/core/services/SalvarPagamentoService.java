package br.com.challenge.pagamentos.core.services;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;

public interface SalvarPagamentoService {
    public void persistPagamento(PagamentosRequestDto pagamentoDto);
}
