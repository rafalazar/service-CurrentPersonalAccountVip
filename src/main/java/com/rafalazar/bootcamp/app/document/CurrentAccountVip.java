package com.rafalazar.bootcamp.app.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="current_account_vip")
public class CurrentAccountVip {
	
	@Id
	private String id;
	private String numAccount;
	private String nameOwner;
	private String dniOwner;
	private Double depositAmount;
	private Double retiro;
	private Double totalAvailable;
	private Date createAt;
	private Date retiroDate;
	private Date depositDate;
	
	public CurrentAccountVip() {
		
	}
	
	public CurrentAccountVip(String numAccount,String nameOwner,String dniOwner, Double totalAvailable) {
		this.numAccount = numAccount;
		this.nameOwner = nameOwner;
		this.dniOwner = dniOwner;
		this.totalAvailable = totalAvailable;
		
	}

}
