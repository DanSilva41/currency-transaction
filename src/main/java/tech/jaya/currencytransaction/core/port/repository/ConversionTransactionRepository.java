package tech.jaya.currencytransaction.core.port.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;

public interface ConversionTransactionRepository {

    Flux<ConversionTransaction> findAllByUserIdentifier(String userIdentifier);

    Mono<ConversionTransaction> save(ConversionTransaction conversionTransaction);
}
