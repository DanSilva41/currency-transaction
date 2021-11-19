package tech.jaya.currencytransaction.core.usecase;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.exception.BaseBusinessException;
import tech.jaya.currencytransaction.core.exception.ErrorMessage;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.core.model.ExchangeRates;
import tech.jaya.currencytransaction.core.port.gateway.CurrencyLayer;
import tech.jaya.currencytransaction.core.port.repository.ConversionTransactionRepository;

@Slf4j
@AllArgsConstructor
public class ConvertCurrencies {

    private final CurrencyLayer currencyLayer;
    private final ConversionTransactionRepository conversionTransactionRepository;

    public Mono<ConversionTransaction> execute(ConversionTransaction conversionToExecute) {
        return Mono.just(conversionToExecute)
                .doOnNext(x -> {
                    if (conversionToExecute.verifyIfOriginAndDestinationCurrencySame()) {
                        log.debug("Business | convertCurrencies | exception | CURRENCIES_MUST_BE_DIFFERENT");
                        throw new BaseBusinessException(ErrorMessage.CURRENCIES_MUST_BE_DIFFERENT);
                    }
                })
                .flatMap(x -> currencyLayer.getConversionRates(conversionToExecute.getOriginCurrency(), conversionToExecute.getDestinationCurrency()))
                .doOnNext(exchangeRates -> {
                    final var currencies = Set.of(conversionToExecute.getOriginCurrency(), conversionToExecute.getDestinationCurrency());
                    if (exchangeRates.isInvalidRates(currencies)) {
                        log.debug("Business | convertCurrencies | exception | WAS_NOT_POSSIBLE_GET_EXCHANGE_RATES");
                        throw new BaseBusinessException(ErrorMessage.WAS_NOT_POSSIBLE_GET_EXCHANGE_RATES);
                    }
                })
                .flatMap(exchangeRates -> {
                    log.debug("Business | convertCurrencies | call conversion");

                    callConversion(exchangeRates, conversionToExecute);

                    log.debug("Business | convertCurrencies | next |save conversion transaction");
                    return Mono.just(conversionToExecute);
                })
                .flatMap(conversionTransactionRepository::save);
    }

    private void callConversion(ExchangeRates exchangeRates, ConversionTransaction conversionToExecute) {
        final var rateOfOriginCurrency = exchangeRates.getRates().get(conversionToExecute.getOriginCurrency());
        final var rateOfDestinationCurrency = exchangeRates.getRates().get(conversionToExecute.getDestinationCurrency());

        conversionToExecute.convertCurrencies(rateOfOriginCurrency, rateOfDestinationCurrency);
    }

}
