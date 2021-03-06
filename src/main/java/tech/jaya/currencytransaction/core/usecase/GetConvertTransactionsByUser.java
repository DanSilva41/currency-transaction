package tech.jaya.currencytransaction.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.core.port.repository.ConversionTransactionRepository;

@Slf4j
@RequiredArgsConstructor
public class GetConvertTransactionsByUser {

    private final ConversionTransactionRepository conversionTransactionRepository;

    public Flux<ConversionTransaction> execute(final String userIdentifier) {
        return conversionTransactionRepository.findAllByUserIdentifier(userIdentifier);
    }

}
