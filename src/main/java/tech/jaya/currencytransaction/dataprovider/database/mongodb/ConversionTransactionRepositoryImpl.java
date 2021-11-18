package tech.jaya.currencytransaction.dataprovider.database.mongodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.core.port.repository.ConversionTransactionRepository;
import tech.jaya.currencytransaction.dataprovider.database.mongodb.converter.ConversionTransactionDataConverter;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ConversionTransactionRepositoryImpl implements ConversionTransactionRepository {

    private final ConversionTransactionMongoDBRepository conversionTransactionMongoDBRepository;

    @Override
    public Flux<ConversionTransaction> findAllByUserIdentifier(final String userIdentifier) {
        log.debug("Database | MongoDB | ConversionTransactionRepository |findAllByUserIdentifier");
        return conversionTransactionMongoDBRepository.findAllByUserIdentifier(userIdentifier)
                .flatMap(ConversionTransactionDataConverter::toConversionTransaction);
    }

    @Override
    public Mono<ConversionTransaction> save(ConversionTransaction conversionTransactionToSave) {
        log.debug("Database | MongoDB | ConversionTransactionRepository | save");
        return conversionTransactionMongoDBRepository.save(
                        ConversionTransactionDataConverter.toConversionTransactionData(conversionTransactionToSave)
                )
                .flatMap(ConversionTransactionDataConverter::toConversionTransaction);
    }
}
