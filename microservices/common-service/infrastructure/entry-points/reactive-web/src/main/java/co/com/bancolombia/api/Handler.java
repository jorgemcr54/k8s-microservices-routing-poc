package co.com.bancolombia.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class Handler {

    @Value("${pod.labels.flow:unknown}")
    private String flow;

    @Value("${pod.labels.layer:unknown}")
    private String layer;

    public Mono<ServerResponse> listenGETLabels(ServerRequest serverRequest) {
        Map<String, String> labels = Map.of(
                "flow", flow,
                "layer", layer
        );
        return ServerResponse.ok().bodyValue(labels);
    }
}
