package com.bootcamp.microserviceAtm.microserviceAtm.convertion;


import com.bootcamp.microserviceAtm.microserviceAtm.models.documents.Movement;
import com.bootcamp.microserviceAtm.microserviceAtm.models.dto.MovementBasicDto;
import com.bootcamp.microserviceAtm.microserviceAtm.models.dto.MovementTransferDto;
import org.springframework.stereotype.Controller;

@Controller
public class ConvertionsAtm {

	/*public SavingAccountDto toSavingAccountDto(SavingAccount savingAccount) {
		SavingAccountDto dto = new SavingAccountDto();
		dto.setNumAccount(savingAccount.getNumAccount());
		dto.setNomAccount(savingAccount.getNomAccount());
		dto.setTypeAccount(savingAccount.getTypeAccount());
		dto.setCurrentBalance(savingAccount.getCurrentBalance());
		dto.setStatus(savingAccount.getStatus());
		dto.setCreatedAt(savingAccount.getCreatedAt());
		dto.setUpdatedAt(savingAccount.getUpdatedAt());
		return dto;
	}*/
	
	/*public SavingAccount toSavingAccount(SavingAccountDto dto) {
		SavingAccount savingAccount = new SavingAccount();
		savingAccount.setNomBank(dto.getNomBank());
		savingAccount.setNumAccount(dto.getNumAccount());
		savingAccount.setNomAccount(dto.getNomAccount());
		savingAccount.setTypeAccount(dto.getTypeAccount());
		savingAccount.setCurrentBalance(dto.getCurrentBalance());
		savingAccount.setStatus(dto.getStatus());
		savingAccount.setCreatedAt(dto.getCreatedAt());
		savingAccount.setUpdatedAt(dto.getUpdatedAt());
		savingAccount.setCantTransactions(dto.getCantTransactions());
		return savingAccount;
	}*/

	public Movement toMovement(MovementBasicDto dto) {
		Movement movement = new Movement();
		movement.setNumAccount(dto.getNumAccount());
		movement.setTypeMovement(dto.getTypeMovement());
		movement.setBalanceTransaction(dto.getBalanceTransaction());
		movement.setCommission(dto.getCommission());
		movement.setCreatedAt(dto.getCreatedAt());
		return movement;
	}

	public Movement toMovement(MovementTransferDto dto) {
		Movement movement = new Movement();
		movement.setNumAccount(dto.getNumAccountDestine());
		movement.setTypeMovement(dto.getTypeMovement());
		movement.setBalanceTransaction(dto.getBalanceTransaction());
		movement.setCommission(dto.getCommission());
		movement.setCreatedAt(dto.getCreatedAt());
		return movement;
	}

	
}
