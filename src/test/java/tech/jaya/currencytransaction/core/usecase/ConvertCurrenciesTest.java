package tech.jaya.currencytransaction.core.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tech.jaya.currencytransaction.core.exception.BaseBusinessException;
import tech.jaya.currencytransaction.core.exception.ErrorMessage;
import tech.jaya.currencytransaction.core.model.ConversionTransaction;
import tech.jaya.currencytransaction.core.model.ExchangeRates;
import tech.jaya.currencytransaction.core.port.gateway.CurrencyLayer;
import tech.jaya.currencytransaction.core.port.repository.ConversionTransactionRepository;
import tech.jaya.currencytransaction.fixture.FixtureConversionTransaction;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ConvertCurrenciesTest {

    @InjectMocks
    private ConvertCurrencies convertCurrencies;

    @Mock
    private ConversionTransactionRepository conversionTransactionRepository;

    @Mock
    private CurrencyLayer currencyLayer;

    @Test
    void shouldConvertWithSuccess() {
        final var userIdentifier = "1";
        var conversionTransaction = FixtureConversionTransaction.oneWithoutSave(userIdentifier);
        var conversionTransactionReturned = FixtureConversionTransaction.one(userIdentifier);
        var exchangesRates = ExchangeRates.builder()
                .success(true)
                .rates(Map.of(conversionTransaction.getOriginCurrency(), new BigDecimal("1.137314"),
                        conversionTransaction.getDestinationCurrency(), new BigDecimal("6.333688")))
                .build();

        when(currencyLayer.getConversionRates(conversionTransaction.getOriginCurrency(), conversionTransaction.getDestinationCurrency()))
                .thenReturn(Mono.just(exchangesRates));
        when(conversionTransactionRepository.save(any(ConversionTransaction.class))).thenReturn(Mono.just(conversionTransactionReturned));

        StepVerifier.create(convertCurrencies.execute(conversionTransaction))
                .assertNext(returned -> {
                    assertEquals(conversionTransactionReturned.getIdentifier(), returned.getIdentifier());
                    assertEquals(conversionTransaction.getUserIdentifier(), returned.getUserIdentifier());
                    assertEquals(conversionTransaction.getOriginCurrency(), returned.getOriginCurrency());
                    assertEquals(conversionTransaction.getDestinationCurrency(), returned.getDestinationCurrency());
                    assertEquals(conversionTransaction.getOriginValue(), returned.getOriginValue());
                    assertEquals(conversionTransactionReturned.getDestinationValue(), returned.getDestinationValue());
                    assertEquals(conversionTransactionReturned.getConversionRate(), returned.getConversionRate());
                    assertEquals(conversionTransactionReturned.getTransactionTime(), returned.getTransactionTime());
                })
                .verifyComplete();
    }

    @Test
    void shouldThrowConvertWhenCurrenciesSame() {
        final var userIdentifier = "1";
        var conversionTransaction = FixtureConversionTransaction.oneWithSameCurrency(userIdentifier);

        StepVerifier.create(convertCurrencies.execute(conversionTransaction))
                .expectErrorMatches(exception -> exception instanceof BaseBusinessException
                        && ErrorMessage.CURRENCIES_MUST_BE_DIFFERENT.getIdentifier().equals(exception.getMessage()))
                .verify();
    }

    @Test
    void shouldThrowConvertWhenInvalidRates() {
        final var userIdentifier = "1";
        var conversionTransaction = FixtureConversionTransaction.oneWithoutSave(userIdentifier);
        var exchangesRates = ExchangeRates.builder()
                .success(true)
                .rates(Map.of(conversionTransaction.getDestinationCurrency(), new BigDecimal("6.333688")))
                .build();

        when(currencyLayer.getConversionRates(conversionTransaction.getOriginCurrency(), conversionTransaction.getDestinationCurrency()))
                .thenReturn(Mono.just(exchangesRates));

        StepVerifier.create(convertCurrencies.execute(conversionTransaction))
                .expectErrorMatches(exception -> exception instanceof BaseBusinessException
                        && ErrorMessage.WAS_NOT_POSSIBLE_GET_EXCHANGE_RATES.getIdentifier().equals(exception.getMessage()))
                .verify();
    }


}