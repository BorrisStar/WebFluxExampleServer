package com.example.webfluxexampleserver.controller;


import com.example.webfluxexampleserver.domain.Message;
import com.example.webfluxexampleserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/webflux/r2dbc")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public Flux<Message> getAll() {
        return messageService.getAll();
    }

    @PostMapping
    public Mono<Message> addOne(@RequestBody Message message) {
        return messageService.addOne(message);
    }
}
