package co.com.bancolombia.api;

import co.com.bancolombia.model.transversal.TransversalResponse;
import co.com.bancolombia.usecase.invokecommonservice.InvokeCommonServiceUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
@TestPropertySource(properties = "api.base-path=/api/v1/dep")
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private InvokeCommonServiceUseCase useCase;

    @Test
    void testListenGETLegacy() {
        when(useCase.invoke("legacy")).thenReturn(
                Mono.just(TransversalResponse.builder().flow("legacy").layer("consumer").build()));

        webTestClient.get()
                .uri("/api/v1/dep/legacy")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.flow").isEqualTo("legacy")
                .jsonPath("$.layer").isEqualTo("consumer");
    }

    @Test
    void testListenGETTarget() {
        when(useCase.invoke("target")).thenReturn(
                Mono.just(TransversalResponse.builder().flow("target").layer("consumer").build()));

        webTestClient.get()
                .uri("/api/v1/dep/target")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.flow").isEqualTo("target")
                .jsonPath("$.layer").isEqualTo("consumer");
    }
}
