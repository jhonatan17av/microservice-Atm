package com.bootcamp.microserviceAtm.microserviceAtm.controller;

import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Atm;
import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Movement;
import com.bootcamp.microserviceAtm.microserviceAtm.models.dto.MovementBasicDto;
import com.bootcamp.microserviceAtm.microserviceAtm.services.AtmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@Api(value = "Controller-Atm", description = "Methods on Controller to Atm")
@RequestMapping("/atm")
public class AtmRestController {

  @Autowired
  private AtmService atmService;

  /*
   * .
   * This method list Atm
   */
  @ApiOperation(value = "Get Atm", notes = "Returns all Atm")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Exits atm's information")
  })
  @GetMapping
  public Mono<ResponseEntity<Flux<Atm>>> findAll() {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(atmService.findAll()))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * .
   * This method save Atm
   */
  @ApiOperation(value = "Save Atm", notes = "Returns one Atm")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Exits a atm")
  })
  @PostMapping
  public Mono<ResponseEntity<Atm>> saveAtm(@RequestBody Atm atm) {
    return Mono.just(atm)
        .flatMap(atm1 -> {
          return atmService.saveAtm(atm)
              .map(atm2 -> ResponseEntity.created(URI.create("/atm"))
                  .contentType(MediaType.APPLICATION_JSON).body(atm2));
        });
  }

  /**
   * .
   * This method save Movement
   */
  @ApiOperation(value = "Save Movement", notes = "Returns one Movement")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Exits a Movement")
  })
  @PostMapping("/saveMov")
  public Mono<ResponseEntity<MovementBasicDto>> saveMov(@RequestBody MovementBasicDto movementBasicDto) {
    return Mono.just(movementBasicDto)
        .flatMap(movementBasicDto1 -> {
          return atmService.saveMovement(movementBasicDto)
              .map(a -> ResponseEntity.created(URI.create("/atm"))
                  .contentType(MediaType.APPLICATION_JSON).body(a));
        });
  }

  /**
   * .
   * This method list Movement
   */
  @ApiOperation(value = "list Movement", notes = "Returns list of Movement")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Exits a Movement")
  })
  @GetMapping("/movements")
  public Mono<ResponseEntity<Flux<Movement>>> findAllMovement() {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(atmService.findAllMovement()))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * .
   * This method list Movement by NumAccount
   */
  @ApiOperation(value = "list Movement by NumAccount", notes = "Returns list of Movement by NumAccount")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Exits a Movement")
  })
  @GetMapping("/movements/{numAccount}")
  public Mono<ResponseEntity<Flux<Movement>>> findMovByNumAccount(@PathVariable String numAccount) {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(atmService.findMovByNumAccount(numAccount)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


}
