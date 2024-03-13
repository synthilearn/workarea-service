package com.synthilearn.workareaservice.infra.adapter.client;

import com.synthilearn.commonstarter.GenericResponse;
import com.synthilearn.workareaservice.app.config.WebClientProperties;
import com.synthilearn.workareaservice.domain.AbstractWidget;
import com.synthilearn.workareaservice.infra.rest.dto.CreateDictionaryDto;
import com.synthilearn.workareaservice.infra.rest.exception.DictionaryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class DictionaryClient {

    private final WebClientProperties webClientProperties;
    private final WebClient webClient;

    public Mono<AbstractWidget> createDictionary(CreateDictionaryDto request) {
        return webClient.post()
                .uri(webClientProperties.getDictionaryHost() + "/dictionary")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CreateDictionaryDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<GenericResponse<AbstractWidget>>() {
                })
                .map(GenericResponse::getResultData)
                .onErrorResume(error -> {
                    log.error("Error has occurred while create dictionary: {} {}", error, error);
                    GenericResponse<?> response = ((WebClientResponseException) error).getResponseBodyAs(GenericResponse.class);
                    throw DictionaryException.creationError(request.workareaId(), response == null ? null : response.getMessage());
                });
    }

}
