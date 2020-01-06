package com.bootcamp.microserviceAtm.microserviceAtm.services.serviceDto;

import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Movement;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.SavingAccount;
import reactor.core.publisher.Mono;

public interface IServiceClientDto {

	Mono<Movement> saveMovementSavingA(Movement movement);
	Mono<Movement> saveMovementCurrentA(Movement movement);
	Mono<SavingAccount> findSavingAbyNumAccount(String numAccount);
	Mono<SavingAccount> findCurrentAbyNumAccount(String numAccount);

}
