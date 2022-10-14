package br.com.challenge.pagamentos.app.dataprovider.repository;

import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentosRepository extends MongoRepository<PagamentosEntity, Integer> {
}
