package com.bootcamp.microserviceAtm.microserviceAtm.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class MovementTransferDto {

  @NotBlank
  @Id
  private String id;
  @NotEmpty(message = "Campo nomAccountOrigin no puede ser vacio")
  private String nomAccountOrigin;
  @NotEmpty(message = "Campo numAccountOrigin no puede ser vacio")
  private String numAccountOrigin;
  @NotEmpty(message = "Campo nomAccountDestine no puede ser vacio")
  private String nomAccountDestine;
  @NotEmpty(message = "Campo numAccountDestine no puede ser vacio")
  private String numAccountDestine;
  @NotEmpty(message = "Campo typeMovement no puede ser vacio")
  private String typeMovement;
  @NotEmpty(message = "Campo balanceTransaction no puede ser vacio")
  private Double balanceTransaction;
  @NotEmpty(message = "Campo commission no puede ser vacio")
  private Double commission;
  @NotEmpty(message = "Campo createdAt no puede ser vacio")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date createdAt;

  public MovementTransferDto() {
  }

  public MovementTransferDto(
      @NotEmpty(message = "Field nomAccountOrigin no puede ser vacio") String nomAccountOrigin,
      @NotEmpty(message = "Campo numAccountOrigin no puede ser vacio") String numAccountOrigin,
      @NotEmpty(message = "Campo nomAccountDestine no puede ser vacio") String nomAccountDestine,
      @NotEmpty(message = "Campo numAccountDestine no puede ser vacio") String numAccountDestine,
      @NotEmpty(message = "Campo typeMovement no puede ser vacio") String typeMovement,
      @NotEmpty(message = "Campo balanceTransaction no puede ser vacio") Double balanceTransaction,
      @NotEmpty(message = "Campo commission no puede ser vacio") Double commission,
      @NotEmpty(message = "Campo createdAt no puede ser vacio") Date createdAt) {
    this.nomAccountOrigin = nomAccountOrigin;
    this.numAccountOrigin = numAccountOrigin;
    this.nomAccountDestine = nomAccountDestine;
    this.numAccountDestine = numAccountDestine;
    this.typeMovement = typeMovement;
    this.balanceTransaction = balanceTransaction;
    this.commission = commission;
    this.createdAt = createdAt;
  }
}
