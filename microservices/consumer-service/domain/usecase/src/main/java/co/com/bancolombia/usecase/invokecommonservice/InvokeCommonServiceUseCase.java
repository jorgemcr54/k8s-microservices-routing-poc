package co.com.bancolombia.usecase.invokecommonservice;

import co.com.bancolombia.model.transversal.TransversalResponse;
import co.com.bancolombia.model.transversal.gateways.TransversalServiceGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class InvokeCommonServiceUseCase {

    private final TransversalServiceGateway gateway;

    public Mono<TransversalResponse> invoke(String mode) {
        return gateway.invoke(mode);
    }
}
