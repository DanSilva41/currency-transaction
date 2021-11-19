package tech.jaya.currencytransaction.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jaya.currencytransaction.core.port.gateway.CurrencyLayer;
import tech.jaya.currencytransaction.core.port.repository.ConversionTransactionRepository;
import tech.jaya.currencytransaction.core.usecase.ConvertCurrencies;
import tech.jaya.currencytransaction.core.usecase.GetConvertTransactionsByUser;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public ConvertCurrencies createConvertCurrencies(CurrencyLayer currencyLayer,
                                                     ConversionTransactionRepository conversionTransactionRepository) {
        return new ConvertCurrencies(currencyLayer, conversionTransactionRepository);
    }

    @Bean
    public GetConvertTransactionsByUser createGetConvertTransactionsByUser(ConversionTransactionRepository conversionTransactionRepository) {
        return new GetConvertTransactionsByUser(conversionTransactionRepository);
    }
}
