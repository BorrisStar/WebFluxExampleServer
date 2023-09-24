package com.example.webfluxexampleserver.config;

import com.example.webfluxexampleserver.handler.GetFluxInFunctionalStyleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration(proxyBeanMethods = false)
public class GetFluxInFunctionalStyleRouter {
    @Bean
    public RouterFunction<ServerResponse> route(GetFluxInFunctionalStyleHandler handler) {
        RequestPredicate route = RequestPredicates
                .GET("/api/v1/webflux/flux-functional")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN));

        return RouterFunctions
                .route(route, handler::getFluxInFunctionalStyle);
    }
}
