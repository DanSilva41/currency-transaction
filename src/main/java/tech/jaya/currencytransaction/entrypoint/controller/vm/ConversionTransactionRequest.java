package tech.jaya.currencytransaction.entrypoint.controller.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConversionTransactionRequest {

    @NotBlank(message = "{global.cannot-be-empty}")
    @Size(min = 3, max = 3, message = "{validation.fields.originCurrency.size}")
    private String originCurrency;

    @NotBlank(message = "{global.cannot-be-empty}")
    @Size(min = 3, max = 3, message = "{validation.fields.destinationCurrency.size}")
    private String destinationCurrency;

    @NotNull(message = "{global.cannot-be-null}")
    @DecimalMin(value = "0.01", message = "{validation.fields.destinationCurrency.min}")
    @DecimalMax(value = "999999999.99", message = "{validation.fields.destinationCurrency.max}")
    private BigDecimal sourceValue;
}
