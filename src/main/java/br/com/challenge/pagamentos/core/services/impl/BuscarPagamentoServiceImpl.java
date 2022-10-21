package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.app.entrypoint.filter.FiltroBuscaPagamento;
import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import br.com.challenge.pagamentos.core.services.BuscarPagamentoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BuscarPagamentoServiceImpl implements BuscarPagamentoService {

    @Autowired
    private PagamentosRepository repository;
    @Override
    public List<PagamentosEntity> searchPagamentos(FiltroBuscaPagamento filtro) {
        if(filtro.equals(FiltroBuscaPagamento.TODOS))
            return repository.findAll();
        return repository.findByStatus(filtro.toString());
    }

    @Override
    public Optional<PagamentosEntity> searchById(String id) {
        return repository.findById(id);
    }
}
