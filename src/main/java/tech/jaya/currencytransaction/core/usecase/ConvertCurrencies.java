package tech.jaya.currencytransaction.core.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.exception.BaseBusinessException;
import tech.jaya.currencytransaction.core.exception.ErrorMessage;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.core.model.ExchangeRates;
import tech.jaya.currencytransaction.core.port.gateway.CurrencyLayer;
import tech.jaya.currencytransaction.core.port.repository.ConversionTransactionRepository;

import java.util.Set;

@Slf4j
@AllArgsConstructor
public class ConvertCurrencies {

    private final CurrencyLayer currencyLayer;
    private final ConversionTransactionRepository conversionTransactionRepository;

    public Mono<ConversionTransaction> execute(ConversionTransaction conversionToExecute) {
        return Mono.just(conversionToExecute)
                .doOnNext(x -> {
                    if (conversionToExecute.verifyIfOriginAndDestinationCurrencySame())
                        throw new BaseBusinessException(ErrorMessage.CURRENCIES_MUST_BE_DIFFERENT);
                })
                .flatMap(x -> currencyLayer.getConversionRates(conversionToExecute.getOriginCurrency(), conversionToExecute.getDestinationCurrency()))
                .doOnNext(exchangeRates -> {
                    var currencies = Set.of(conversionToExecute.getOriginCurrency(), conversionToExecute.getDestinationCurrency());
                    if (exchangeRates.isInvalidRates(currencies))
                        throw new BaseBusinessException(ErrorMessage.ERROR_WAS_NOT_POSSIBLE_GET_EXCHANGE_RATES);
                })
                .flatMap(exchangeRates -> {
                    callConversion(exchangeRates, conversionToExecute);
                    return Mono.just(conversionToExecute);
                })
                .flatMap(conversionTransactionRepository::save);
    }

    private void callConversion(ExchangeRates exchangeRates, ConversionTransaction conversionToExecute) {
        var rateOfOriginCurrency = exchangeRates.getRates().get(conversionToExecute.getOriginCurrency());
        var rateOfDestinationCurrency = exchangeRates.getRates().get(conversionToExecute.getDestinationCurrency());

        conversionToExecute.convertCurrencies(rateOfOriginCurrency, rateOfDestinationCurrency);
    }

}
