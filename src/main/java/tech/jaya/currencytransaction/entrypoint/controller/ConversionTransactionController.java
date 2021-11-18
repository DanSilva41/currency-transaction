package tech.jaya.currencytransaction.entrypoint.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tech.jaya.currencytransaction.core.usecase.ConvertCurrencies;
import tech.jaya.currencytransaction.core.usecase.GetConvertTransactionsByUser;
import tech.jaya.currencytransaction.entrypoint.controller.converter.ConversionTransactionRequestConverter;
import tech.jaya.currencytransaction.entrypoint.controller.converter.ConversionTransactionResponseConverter;
import tech.jaya.currencytransaction.entrypoint.controller.vm.ConversionTransactionRequest;
import tech.jaya.currencytransaction.entrypoint.controller.vm.ConversionTransactionResponse;

import javax.validation.Valid;
import java.util.List;

/**
 * Reactive REST controller to manage currencies transactions
 */
@Slf4j
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/transaction")
public class ConversionTransactionController {

    private final ConvertCurrencies convertCurrencies;
    private final GetConvertTransactionsByUser getConvertTransactionsByUser;
    private final ConversionTransactionRequestConverter requestConverter;
    private final ConversionTransactionResponseConverter responseConverter;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{userId}")
    public Mono<ResponseEntity<List<ConversionTransactionResponse>>> allCurrenciesTransactions(@PathVariable("userId") final String userId) {
        log.info("allCurrenciesTransactions: userId = {}", userId);

        return getConvertTransactionsByUser.execute(userId)
                .flatMap(responseConverter::toConversionTransactionResponse)
                .collectList()
                .flatMap(transactions -> {
                    if (transactions.isEmpty())
                        return Mono.just(ResponseEntity.notFound().build());
                    return Mono.just(ResponseEntity.ok(transactions));
                });
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ConversionTransactionResponse> convertCurrencies(@PathVariable("userId") final String userId,
                                                                 @Valid @RequestBody final ConversionTransactionRequest request) {
        log.info("convertCurrencies: userId = {} | request = {}", userId, request);

        return requestConverter.toConversionTransaction(userId, request)
                .flatMap(convertCurrencies::execute)
                .flatMap(responseConverter::toConversionTransactionResponse);
    }

}
