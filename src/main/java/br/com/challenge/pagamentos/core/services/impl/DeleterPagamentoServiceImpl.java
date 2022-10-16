package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.core.services.DeletarPagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class DeleterPagamentoServiceImpl implements DeletarPagamentoService {

    @Autowired
    private PagamentosRepository repository;

    @Override
    public void deletePagamento(String id) {
        var pagamento = repository.findById(id);
        repository.delete(pagamento.orElseThrow());
    }
}
