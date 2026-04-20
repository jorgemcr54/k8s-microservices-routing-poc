package co.com.bancolombia.api;

import co.com.bancolombia.usecase.invokecommonservice.InvokeCommonServiceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final InvokeCommonServiceUseCase useCase;

    public Mono<ServerResponse> listenGETLegacy(ServerRequest serverRequest) {
        return useCase.invoke("legacy")
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(WebClientResponseException.class, ex ->
                        ServerResponse.status(ex.getStatusCode()).bodyValue(ex.getResponseBodyAsString()));
    }

    public Mono<ServerResponse> listenGETTarget(ServerRequest serverRequest) {
        return useCase.invoke("target")
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(WebClientResponseException.class, ex ->
                        ServerResponse.status(ex.getStatusCode()).bodyValue(ex.getResponseBodyAsString()));
    }
}
