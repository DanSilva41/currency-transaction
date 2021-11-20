package tech.jaya.currencytransaction.entrypoint.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.usecase.ConvertCurrencies;
import tech.jaya.currencytransaction.core.usecase.GetConvertTransactionsByUser;
import tech.jaya.currencytransaction.entrypoint.controller.converter.ConversionTransactionRequestConverter;
import tech.jaya.currencytransaction.entrypoint.controller.converter.ConversionTransactionResponseConverter;
import tech.jaya.currencytransaction.entrypoint.controller.vm.ConversionTransactionRequest;
import tech.jaya.currencytransaction.entrypoint.controller.vm.ConversionTransactionResponse;

/**
 * Reactive REST controller to manage currencies transactions.
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/transaction")
public class ConversionTransactionController {

    private final ConvertCurrencies convertCurrencies;
    private final GetConvertTransactionsByUser getConvertTransactionsByUser;


    @ResponseStatus(code = HttpStatus.OK, reason = "Returned conversions")
    @GetMapping(value = "/user/{userId}")
    public Mono<ResponseEntity<List<ConversionTransactionResponse>>> allCurrenciesTransactions(@PathVariable("userId") final String userId) {
        log.info("GET allCurrenciesTransactions: userId = {}", userId);

        return getConvertTransactionsByUser.execute(userId)
                .flatMap(ConversionTransactionResponseConverter::toConversionTransactionResponse)
                .collectList()
                .flatMap(transactions -> {
                    if (transactions.isEmpty()) {
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                    return Mono.just(ResponseEntity.ok(transactions));
                });
    }

    @ResponseStatus(code = HttpStatus.CREATED, reason = "Conversion performed")
    @PostMapping(value = "/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ConversionTransactionResponse> convertCurrencies(@PathVariable("userId") final String userId,
                                                                 @Valid @RequestBody final ConversionTransactionRequest request) {
        log.info("POST convertCurrencies: userId = {} | request = {}", userId, request);

        return ConversionTransactionRequestConverter.toConversionTransaction(userId, request)
                .flatMap(convertCurrencies::execute)
                .flatMap(ConversionTransactionResponseConverter::toConversionTransactionResponse);
    }

}
