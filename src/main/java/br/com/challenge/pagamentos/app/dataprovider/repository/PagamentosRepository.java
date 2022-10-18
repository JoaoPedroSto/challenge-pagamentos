package br.com.challenge.pagamentos.app.dataprovider.repository;

import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentosRepository extends MongoRepository<PagamentosEntity, String> {

    List<PagamentosEntity> findByStatus(String status);

    boolean existsByPaymentDateAndAmountAndReceiverKey(LocalDate paymente, Float amount, String key);
}
