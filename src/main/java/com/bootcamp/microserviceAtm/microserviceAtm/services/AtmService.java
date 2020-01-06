package com.bootcamp.microserviceAtm.microserviceAtm.services;


import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Atm;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.CurrentAccount;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Movement;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.SavingAccount;
import com.bootcamp.microserviceAtm.microserviceAtm.models.dto.MovementBasicDto;
import com.bootcamp.microserviceAtm.microserviceAtm.models.dto.MovementTransferDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AtmService {

  Flux<Atm> findAll();
  Mono<Atm> saveAtm(Atm atm);

  Mono<MovementBasicDto> saveMovement(MovementBasicDto movementBasicDto);
  Flux<Movement> findAllMovement();
  Flux<Movement> findMovByNumAccount(String numAccount);

  Mono<SavingAccount> transferSavingAccountToCurrentAccount(MovementTransferDto movementTransferDto);
  Mono<CurrentAccount> transferCurrentAccountToSavingAccount(MovementTransferDto movementTransferDto);

}
