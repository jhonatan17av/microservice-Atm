package com.bootcamp.microserviceAtm.microserviceAtm.services;

import com.bootcamp.microserviceAtm.microserviceAtm.convertion.ConvertionsAtm;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Atm;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.CurrentAccount;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Movement;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.SavingAccount;
import com.bootcamp.microserviceAtm.microserviceAtm.models.dto.MovementBasicDto;
import com.bootcamp.microserviceAtm.microserviceAtm.models.dto.MovementTransferDto;
import com.bootcamp.microserviceAtm.microserviceAtm.repository.AtmRepository;
import com.bootcamp.microserviceAtm.microserviceAtm.repository.MovementRepository;
import com.bootcamp.microserviceAtm.microserviceAtm.services.serviceDto.IServiceClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AtmServiceImpl implements AtmService
{

  @Autowired
  private AtmRepository repoAtm;
  @Autowired
  private MovementRepository repoMov;
  @Autowired
  private IServiceClientDto serviceClient;
  @Autowired
  private ConvertionsAtm conv;

  @Override
  public Flux<Atm> findAll() {
    return repoAtm.findAll();
  }

  @Override
  public Mono<Atm> saveAtm(Atm atm) {
    return repoAtm.save(atm);
  }

  @Override
  public Mono<MovementBasicDto> saveMovement(MovementBasicDto movementBasicDto) {

    if (movementBasicDto.getNomAccount().equalsIgnoreCase("Cuenta de Ahorro")) {
      return serviceClient.findSavingAbyNumAccount(movementBasicDto.getNumAccount())
          .flatMap(savingAccount -> {
            double comi = 15.0;

            if (savingAccount.getNomBank().equalsIgnoreCase("Bcp")) comi = 0.0;

            movementBasicDto.setCommission(comi);
            movementBasicDto.setCreatedAt(new Date());

            return repoMov.save(conv.toMovement(movementBasicDto))
                .flatMap(movement -> {
                  movement.setNumAccount(movementBasicDto.getNumAccount());
                  movement.setTypeMovement(movementBasicDto.getTypeMovement());
                  movement.setBalanceTransaction(movementBasicDto.getBalanceTransaction() + movementBasicDto.getCommission());
                  movement.setCommission(movementBasicDto.getCommission());
                  movement.setCreatedAt(new Date());
                  return serviceClient.saveMovementSavingA(movement);
                }).flatMap(movement -> {
                  movementBasicDto.setNumAccount(movement.getNumAccount());
                  return Mono.just(movementBasicDto);
                });
          }).switchIfEmpty(Mono.empty());
    } else if (movementBasicDto.getNomAccount().equalsIgnoreCase("Cuenta Corriente")) {
      return serviceClient.findCurrentAbyNumAccount(movementBasicDto.getNumAccount())
          .flatMap(currentAccount -> {
            double comi = 15.0;

            if (currentAccount.getNomBank().equalsIgnoreCase("Bcp")) comi = 0.0;

            movementBasicDto.setCommission(comi);
            movementBasicDto.setCreatedAt(new Date());
            return repoMov.save(conv.toMovement(movementBasicDto))
                .flatMap(movement -> {
                  movement.setNumAccount(movementBasicDto.getNumAccount());
                  movement.setTypeMovement(movementBasicDto.getTypeMovement());
                  movement.setBalanceTransaction(movementBasicDto.getBalanceTransaction() + movementBasicDto.getCommission());
                  movement.setCommission(movementBasicDto.getCommission());
                  movement.setCreatedAt(new Date());
                  return serviceClient.saveMovementCurrentA(movement);
                }).flatMap(movement -> {
                  movementBasicDto.setNumAccount(movement.getNumAccount());
                  return Mono.just(movementBasicDto);
                });
          }).switchIfEmpty(Mono.empty());
    }

    return Mono.just(movementBasicDto);

  }

  @Override
  public Flux<Movement> findAllMovement() {
    return repoMov.findAll();
  }

  @Override
  public Flux<Movement> findMovByNumAccount(String numAccount) {
    return repoMov.findBynumAccount(numAccount);
  }

  @Override
  public Mono<SavingAccount> transferSavingAccountToCurrentAccount(MovementTransferDto movementTransferDto) {

    return serviceClient.findSavingAbyNumAccount(movementTransferDto.getNumAccountOrigin())
        .flatMap(savingAccount -> {

          serviceClient.findCurrentAbyNumAccount(movementTransferDto.getNumAccountDestine())
              .flatMap(currentAccount -> {
                double comi = 10.0;
                if (currentAccount.getNomBank().equalsIgnoreCase(savingAccount.getNomBank())) comi = 0.0;
                movementTransferDto.setTypeMovement("retiro");
                movementTransferDto.setCommission(comi);
                movementTransferDto.setCreatedAt(new Date());
                return serviceClient.saveMovementSavingA(conv.toMovement(movementTransferDto))
                    .flatMap(movement -> {
                      movement.setNumAccount(movementTransferDto.getNumAccountOrigin());
                      movement.setTypeMovement(movementTransferDto.getTypeMovement());
                      movement.setBalanceTransaction(movementTransferDto.getBalanceTransaction() + movementTransferDto.getCommission());
                      movement.setCommission(movementTransferDto.getCommission());
                      movement.setCreatedAt(new Date());
                      return serviceClient.saveMovementCurrentA(movement);
                    }).flatMap(movement -> {
                      savingAccount.setNumAccount(movement.getNumAccount());
                      return Mono.just(savingAccount);
                    });
              });
          return Mono.just(savingAccount);
        });
  }

  @Override
  public Mono<CurrentAccount> transferCurrentAccountToSavingAccount(MovementTransferDto movementTransferDto) {
    return serviceClient.findCurrentAbyNumAccount(movementTransferDto.getNumAccountOrigin())
        .flatMap(currentAccount -> {

          serviceClient.findSavingAbyNumAccount(movementTransferDto.getNumAccountDestine())
              .flatMap(savingAccount -> {
                double comi = 10.0;
                if (currentAccount.getNomBank().equalsIgnoreCase(savingAccount.getNomBank())) comi = 0.0;
                movementTransferDto.setTypeMovement("retiro");
                movementTransferDto.setCommission(comi);
                movementTransferDto.setCreatedAt(new Date());
                return serviceClient.saveMovementCurrentA(conv.toMovement(movementTransferDto))
                    .flatMap(movement -> {
                      movement.setNumAccount(movementTransferDto.getNumAccountOrigin());
                      movement.setTypeMovement(movementTransferDto.getTypeMovement());
                      movement.setBalanceTransaction(movementTransferDto.getBalanceTransaction() + movementTransferDto.getCommission());
                      movement.setCommission(movementTransferDto.getCommission());
                      movement.setCreatedAt(new Date());
                      return serviceClient.saveMovementSavingA(movement);
                    }).flatMap(movement -> {
                      return Mono.just(currentAccount);
                    });
              });
          return Mono.just(currentAccount);
        });
  }
}
