package tech.jaya.currencytransaction.controller.vm;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ConversionTransactionRequest {

    @NotBlank(message = "{global.cannot-be-empty}")
    @Size(max = 3, message = "{fields.originCurrency.size}")
    private String originCurrency;

    @NotBlank(message = "{global.cannot-be-empty}")
    @Size(max = 3, message = "{fields.destinationCurrency.size}")
    private String destinationCurrency;

    @NotNull(message = "{global.cannot-be-null}")
    @DecimalMin(value = "0.01", message = "{fields.destinationCurrency.min}")
    @DecimalMax(value = "999999999.99", message = "{fields.destinationCurrency.max}")
    private BigDecimal sourceValue;

    public String getOriginCurrency() {
        return originCurrency;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public BigDecimal getSourceValue() {
        return sourceValue;
    }
}
