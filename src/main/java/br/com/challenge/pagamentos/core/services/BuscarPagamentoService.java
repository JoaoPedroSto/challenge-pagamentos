package br.com.challenge.pagamentos.core.services;

import br.com.challenge.pagamentos.app.entrypoint.filter.FiltroBuscaPagamento;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;

import java.util.List;
import java.util.Optional;

public interface BuscarPagamentoService {
    List<PagamentosEntity> searchPagamentos(FiltroBuscaPagamento filtro);

    Optional<PagamentosEntity> searchById(String id);
}
