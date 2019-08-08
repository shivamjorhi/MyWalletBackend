package com.cg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shivam")
public class MyWallet {

	@Id
	private Integer accId;
	@Column(name="AccNumber",length=20)
	private Long accNumber;
	@Column(name="accHolder",length=20)
	private String accName;
	@Column(name="IniBal",length=20)
	private Double iniBal;
	public MyWallet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyWallet(Integer accId, Long accNumber, String accName, Double iniBal) {
		super();
		this.accId = accId;
		this.accNumber = accNumber;
		this.accName = accName;
		this.iniBal = iniBal;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public Long getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(Long accNumber) {
		this.accNumber = accNumber;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Double getIniBal() {
		return iniBal;
	}
	public void setIniBal(Double iniBal) {
		this.iniBal = iniBal;
	}
	
	
}
