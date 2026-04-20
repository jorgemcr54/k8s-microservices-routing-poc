package co.com.bancolombia.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    private final String basePath;

    public RouterRest(@Value("${api.base-path:/api/v1/dep}") String basePath) {
        this.basePath = basePath;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET(basePath + "/legacy"), handler::listenGETLegacy)
                .andRoute(GET(basePath + "/target"), handler::listenGETTarget);
    }
}
