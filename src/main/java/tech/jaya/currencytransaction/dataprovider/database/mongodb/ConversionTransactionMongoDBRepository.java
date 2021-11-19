package tech.jaya.currencytransaction.dataprovider.database.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import tech.jaya.currencytransaction.dataprovider.database.mongodb.collection.ConversionTransactionData;

public interface ConversionTransactionMongoDBRepository extends ReactiveMongoRepository<ConversionTransactionData, String> {

    Flux<ConversionTransactionData> findAllByUserIdentifier(String userIdentifier);
}
