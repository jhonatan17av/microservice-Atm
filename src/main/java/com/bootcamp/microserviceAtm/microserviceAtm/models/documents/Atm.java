package com.bootcamp.microserviceAtm.microserviceAtm.models.documents;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "atm")
public class Atm {

  private String Id;
  private String bank;
  private Double commission;
  private Date dateCreated;
  private Date dateUpdated;

  public Atm(){}

  public Atm(String bank, Double commission, Date dateCreated, Date dateUpdated) {
    this.bank = bank;
    this.commission = commission;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }
}
