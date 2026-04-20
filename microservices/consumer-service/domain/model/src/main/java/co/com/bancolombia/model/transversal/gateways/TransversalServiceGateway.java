package co.com.bancolombia.model.transversal.gateways;

import co.com.bancolombia.model.transversal.TransversalResponse;
import reactor.core.publisher.Mono;

public interface TransversalServiceGateway {
    Mono<TransversalResponse> invoke(String mode);
}
