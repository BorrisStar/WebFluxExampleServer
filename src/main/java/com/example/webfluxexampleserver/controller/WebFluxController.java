package com.example.webfluxexampleserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/webflux")
@RequiredArgsConstructor
public class WebFluxController {

    @GetMapping("/flux")
    public Flux<String> getFlux(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "3") Long count
    ) {
        return Flux.just(
                        "\nHello Reactive!\n",
                        "First element\n",
                        "Second element\n",
                        "Third element\n",
                        "Forth element\n",
                        "there's nothing more\n"
                )
                .skip(start)
                .take(count)
                .map(String::new);
    }

    @GetMapping("/best")
    public Mono<String> getId() throws InterruptedException {
        return Mono.just("143360");
    }

    @GetMapping("/error")
    public Mono<String> getErrorForTest() {return Mono.error(new RuntimeException("Invalid request"));}

    @PostMapping(value = "/id", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> getName(@RequestBody String id) {
        return getMono("143360", id, "Expert");
    }

    @PostMapping(value = "/name", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> getPosition(@RequestBody String name) {

        return getMono("Expert", name, "Java Developer");
    }

    @PostMapping(value = "/position", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> getSalary(@RequestBody String position) {
        return getMono("Java Developer", position, "1000000");
    }


    private static Mono<String> getMono(String reference, String inputParameter, String result) {
        if (reference.equals(inputParameter)) {
            return Mono.just(result);
        } else {
            return Mono.error(new RuntimeException("Invalid parameter " + inputParameter));
        }
    }
}
