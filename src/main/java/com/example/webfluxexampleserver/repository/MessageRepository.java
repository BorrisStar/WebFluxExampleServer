package com.example.webfluxexampleserver.repository;

import com.example.webfluxexampleserver.domain.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}
