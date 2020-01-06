package com.bootcamp.microserviceAtm.microserviceAtm.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class MovementBasicDto {

  @NotBlank
  @Id
  private String id;
  @NotEmpty(message = "Field nomAccountOrigin should not be empty")
  private String nomAccount;
  @NotEmpty(message = "Field numAccountOrigin should not be empty")
  private String numAccount;
  @NotEmpty(message = "Field typeMovement should not be empty")
  private String typeMovement;
  @NotEmpty(message = "Field balanceTransaction should not be empty")
  private Double balanceTransaction;
  @NotEmpty(message = "Field commission should not be empty")
  private Double commission;
  @NotEmpty(message = "Field createdAt should not be empty")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date createdAt;

  public MovementBasicDto() {
  }

  public MovementBasicDto(
      @NotEmpty(message = "Field nomAccountOrigin should not be empty") String nomAccount,
      @NotEmpty(message = "Field numAccountOrigin should not be empty") String numAccount,
      @NotEmpty(message = "Field typeMovement should not be empty") String typeMovement,
      @NotEmpty(message = "Field balanceTransaction should not be empty") Double balanceTransaction,
      @NotEmpty(message = "Field commission should not be empty") Double commission,
      @NotEmpty(message = "Field createdAt should not be empty") Date createdAt) {
    this.nomAccount = nomAccount;
    this.numAccount = numAccount;
    this.typeMovement = typeMovement;
    this.balanceTransaction = balanceTransaction;
    this.commission = commission;
    this.createdAt = createdAt;
  }
}
