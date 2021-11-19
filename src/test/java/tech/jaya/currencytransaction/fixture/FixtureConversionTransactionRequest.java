package tech.jaya.currencytransaction.fixture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tech.jaya.currencytransaction.entrypoint.controller.vm.ConversionTransactionRequest;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FixtureConversionTransactionRequest {

    public static ConversionTransactionRequest withDefaultValues() {
        return new ConversionTransactionRequest("BRL", "USD", new BigDecimal("20"));
    }

    public static ConversionTransactionRequest withoutMandatoryFields() {
        return new ConversionTransactionRequest();
    }

    public static ConversionTransactionRequest withInvalidFields() {
        return new ConversionTransactionRequest("BRLT", "PUSD", new BigDecimal("9999999999.99"));
    }
}
