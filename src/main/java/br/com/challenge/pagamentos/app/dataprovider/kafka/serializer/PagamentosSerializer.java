package br.com.challenge.pagamentos.app.dataprovider.kafka.serializer;

import br.com.challenge.pagamentos.core.entity.model.PagamentosEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

@Slf4j
@Component
public class PagamentosSerializer implements Serializer<PagamentosEntity> {

    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, PagamentosEntity pagamentosEntity) {
        try{
            log.info("Serializando Pagamento para o ID: {}" , s);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(pagamentosEntity);
            oos.flush();
            return bos.toByteArray();
        } catch (JsonProcessingException e) {
            throw new SerializationException("Erro ao serializar o objeto pra byte[]");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
