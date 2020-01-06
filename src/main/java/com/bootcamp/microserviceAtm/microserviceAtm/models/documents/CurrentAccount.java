package com.bootcamp.microserviceAtm.microserviceAtm.models.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Document(collection = "currentAccounts")
public class CurrentAccount {

  @NotBlank
  @Id
  private String id;
  @NotEmpty(message = "Field nomBank should not be empty")
  private String nomBank;
  @NotEmpty(message = "Field numAccount should not be empty")
  private String numAccount;
  @NotEmpty(message = "Field nomAccount should not be empty")
  private String nomAccount;
  @NotEmpty(message = "Field typeAccount should not be empty")
  private String typeAccount;
  @NotEmpty(message = "Field currentBalance should not be empty")
  @Min(value = 0, message = "Valor minimo 0")
  private Double currentBalance;
  @NotEmpty(message = "Field status should not be empty")
  private String status;
  @NotEmpty(message = "Field createdAt should not be empty")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date createdAt;
  @NotEmpty(message = "Field updatedAt should not be empty")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date updatedAt;
  @NotEmpty(message = "Field cantTransactions should not be empty")
  private Integer cantTransactions;

  public CurrentAccount() {
  }

  public CurrentAccount(@NotEmpty(message = "Field nomBank should not be empty")
                            String nomBank,
                        @NotEmpty(message = "Field numAccount should not be empty")
                            String numAccount,
                        @NotEmpty(message = "Field nomAccount should not be empty")
                            String nomAccount,
                        @NotEmpty(message = "Field typeAccount should not be empty")
                            String typeAccount,
                        @NotEmpty(message = "Field currentBalance should not be empty")
                        @Min(value = 0, message = "Valor minimo 0")
                            Double currentBalance,
                        @NotEmpty(message = "Field status should not be empty")
                            String status,
                        @NotEmpty(message = "Field createdAt should not be empty")
                            Date createdAt,
                        @NotEmpty(message = "Field updatedAt should not be empty")
                            Date updatedAt,
                        @NotEmpty(message = "Field cantTransactions should not be empty")
                            Integer cantTransactions) {
    this.nomBank = nomBank;
    this.numAccount = numAccount;
    this.nomAccount = nomAccount;
    this.typeAccount = typeAccount;
    this.currentBalance = currentBalance;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.cantTransactions = cantTransactions;
  }
}
