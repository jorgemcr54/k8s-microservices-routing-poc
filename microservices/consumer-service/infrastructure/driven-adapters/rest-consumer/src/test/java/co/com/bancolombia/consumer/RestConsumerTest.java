package co.com.bancolombia.consumer;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;

class RestConsumerTest {

    private static RestConsumer restConsumer;
    private static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
        var webClient = WebClient.builder().baseUrl(mockBackEnd.url("/").toString()).build();
        restConsumer = new RestConsumer(webClient, webClient, "/api/mci/labels");
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    @DisplayName("invoke with legacy mode calls the legacy WebClient.")
    void invokeWithLegacyMode() {
        mockBackEnd.enqueue(new MockResponse()
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpStatus.OK.value())
                .setBody("{\"flow\": \"legacy\", \"layer\": \"consumer\"}"));

        StepVerifier.create(restConsumer.invoke("legacy"))
                .expectNextMatches(r -> "legacy".equals(r.getFlow()) && "consumer".equals(r.getLayer()))
                .verifyComplete();
    }

    @Test
    @DisplayName("invoke with target mode calls the target WebClient.")
    void invokeWithTargetMode() {
        mockBackEnd.enqueue(new MockResponse()
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpStatus.OK.value())
                .setBody("{\"flow\": \"target\", \"layer\": \"consumer\"}"));

        StepVerifier.create(restConsumer.invoke("target"))
                .expectNextMatches(r -> "target".equals(r.getFlow()) && "consumer".equals(r.getLayer()))
                .verifyComplete();
    }
}
