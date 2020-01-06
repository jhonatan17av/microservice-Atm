package com.bootcamp.microserviceAtm.microserviceAtm.repository;

import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Atm;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AtmRepository extends ReactiveMongoRepository<Atm, String> {
}
