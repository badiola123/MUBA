package edu.eskola.muba.transaction.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BUYERTEAMID")
	private int buyerTeamId;
	
	@Id
	@Column(name = "SELLERTEAMID")
	private int sellerTeamId;
	
	@Id
	@Column(name = "PLAYERID")
	private int playerId;
	
	@Id
	@Column(name = "TRANSACTIONDATE")
	private Date transactionDate;

	@Column(name = "AMOUNT")
	private double amount;

	public Transaction() {}
	  
	public Transaction(int buyerTeamId, int sellerTeamId, int playerId, Date transactionDate, double amount) {
		this.buyerTeamId = buyerTeamId;
	    this.sellerTeamId = sellerTeamId;
	    this.playerId = playerId;
	    this.transactionDate = transactionDate;
	    this.amount = amount;
	}

	public int getBuyerTeamId() {
		return buyerTeamId;
	}

	public int getSellerTeamId() {
		return sellerTeamId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public double getAmount() {
		return amount;
	}
}
