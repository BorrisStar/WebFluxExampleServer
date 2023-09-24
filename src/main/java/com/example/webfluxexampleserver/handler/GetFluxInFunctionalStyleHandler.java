package com.example.webfluxexampleserver.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GetFluxInFunctionalStyleHandler {
    public Mono<ServerResponse> getFluxInFunctionalStyle(ServerRequest request) {
        Long start = request.queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);
        Long count = request.queryParam("count")
                .map(Long::valueOf)
                .orElse(3L);

        Flux<String> data = Flux
                .just("\nHello Reactive!\n",
                        "First element\n",
                        "Second element\n",
                        "Third element\n",
                        "Forth element\n",
                        "there's nothing more\n"
                )
                .skip(start)
                .take(count)
                .map(String::new);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, String.class);
    }
}
