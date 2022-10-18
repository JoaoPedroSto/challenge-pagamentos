package br.com.challenge.pagamentos.core.services;

import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosRequestDto;
import br.com.challenge.pagamentos.app.entrypoint.dto.PagamentosResponseDTO;

public interface SalvarPagamentoService {
    public PagamentosResponseDTO persistPagamento(PagamentosRequestDto pagamentoDto);
}
