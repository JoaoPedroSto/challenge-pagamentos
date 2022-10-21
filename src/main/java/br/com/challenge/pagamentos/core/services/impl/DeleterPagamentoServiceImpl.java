package br.com.challenge.pagamentos.core.services.impl;

import br.com.challenge.pagamentos.app.configuration.exception.BusinessException;
import br.com.challenge.pagamentos.app.dataprovider.kafka.KafkaProducer;
import br.com.challenge.pagamentos.app.dataprovider.repository.PagamentosRepository;
import br.com.challenge.pagamentos.core.services.DeletarPagamentoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class DeleterPagamentoServiceImpl implements DeletarPagamentoService {

    @Autowired
    private PagamentosRepository repository;
    @Autowired
    private KafkaProducer producer;

    @Override
    public void deletePagamento(String id) {
        var pagamento = repository.findById(id);
        if(pagamento.isPresent()){
            repository.delete(pagamento.get());
            producer.send(pagamento.get(), "DELETE");
        }else
            throw new BusinessException("ID não encontrado na base!");

    }
}
