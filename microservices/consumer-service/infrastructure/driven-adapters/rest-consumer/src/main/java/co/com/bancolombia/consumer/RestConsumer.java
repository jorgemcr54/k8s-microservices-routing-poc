package co.com.bancolombia.consumer;

import co.com.bancolombia.model.transversal.TransversalResponse;
import co.com.bancolombia.model.transversal.gateways.TransversalServiceGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RestConsumer implements TransversalServiceGateway {

    private final WebClient legacyClient;
    private final WebClient targetClient;
    private final String path;

    public RestConsumer(@Qualifier("legacyClient") WebClient legacyClient,
                        @Qualifier("targetClient") WebClient targetClient,
                        @Value("${adapter.restconsumer.path}") String path) {
        this.legacyClient = legacyClient;
        this.targetClient = targetClient;
        this.path = path;
    }

    @Override
    public Mono<TransversalResponse> invoke(String mode) {
        WebClient client = "legacy".equals(mode) ? legacyClient : targetClient;
        return client.get()
                .uri(path)
                .header("Host","midominiointerno.lab")
                .retrieve()
                .bodyToMono(TransversalResponse.class);
    }
}
