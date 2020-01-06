package com.bootcamp.microserviceAtm.microserviceAtm.services.serviceDto;

import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Movement;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.SavingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class ServiceClientDtoImpl implements IServiceClientDto {

	@Autowired
	@Qualifier("savingAccount")
	private WebClient clientSavingAccount;

	@Autowired
	@Qualifier("currentAccount")
	private WebClient clientCurrentAccount;

	@Override
	public Mono<Movement> saveMovementSavingA(Movement movement) {
		return clientSavingAccount.post()
				.uri("/saveMov")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.syncBody(movement)
				.retrieve()
				.bodyToMono(Movement.class);
	}

	@Override
	public Mono<SavingAccount> findSavingAbyNumAccount(String numAccount) {
		Map<String, Object> params = new HashMap<>();
		params.put("numAccount", numAccount);
		return clientSavingAccount.get()
				.uri("/numAccount/{numAccount}", params)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccount.class);
	}

	@Override
	public Mono<Movement> saveMovementCurrentA(Movement movement) {
		return clientSavingAccount.post()
				.uri("/saveMov")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.syncBody(movement)
				.retrieve()
				.bodyToMono(Movement.class);
	}

	@Override
	public Mono<SavingAccount> findCurrentAbyNumAccount(String numAccount) {
		Map<String, Object> params = new HashMap<>();
		params.put("numAccount", numAccount);
		return clientSavingAccount.get()
				.uri("/numAccount/{numAccount}", params)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccount.class);
	}

}
