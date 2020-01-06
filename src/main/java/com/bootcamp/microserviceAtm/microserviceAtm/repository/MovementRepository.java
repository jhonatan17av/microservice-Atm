package com.bootcamp.microserviceAtm.microserviceAtm.repository;

import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface MovementRepository extends ReactiveMongoRepository<Movement, String> {

    Flux<Movement> findBynumAccount(String numAccount);
    Flux<Movement> findByNumAccountAndCreatedAtBetween(String numAccount, Date date1, Date date2);


}
