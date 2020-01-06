package com.bootcamp.microserviceAtm.microserviceAtm.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServiceConfig {

  @Value("${config.base.endpoint}")
  private String pathSavingAccount;

	@Value("${config.base.endpoint2}")
	private String pathCurrentAccount;

	@Bean
	@Qualifier("savingAccount")
	public WebClient webClientSavingAccount() {
		return WebClient.create(pathSavingAccount);
	}

	@Bean
	@Qualifier("currentAccount")
	public WebClient webClientCurrentAccount() {
		return WebClient.create(pathCurrentAccount);
	}


}
