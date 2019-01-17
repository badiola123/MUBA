package edu.eskola.muba.transaction.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of Transaction
 * 
 * @author MUBA team
 * @version Final version
 */

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

	/**
	 * Empty constructor of Transaction
	 */
	public Transaction() {}
	  
	/**
	 * Constructor with the parameters to set to the transaction
	 * 
	 * @param buyerTeamId Id of the team that has bought the player
	 * @param sellerTeamId Id of the team that has sold the player
	 * @param playerId Id of the player taking part
	 * @param transactionDate Date when the transaction has been done
	 * @param amount Amount paid for the player
	 */
	public Transaction(int buyerTeamId, int sellerTeamId, int playerId, Date transactionDate, double amount) {
		this.buyerTeamId = buyerTeamId;
	    this.sellerTeamId = sellerTeamId;
	    this.playerId = playerId;
	    this.transactionDate = transactionDate;
	    this.amount = amount;
	}

	/**
	 * 
	 * @return Returns the buyer team's id
	 */
	public int getBuyerTeamId() {
		return buyerTeamId;
	}

	/**
	 * 
	 * @return Return the seller team's id
	 */
	public int getSellerTeamId() {
		return sellerTeamId;
	}

	/**
	 * 
	 * @return Returns the player id
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * 
	 * @return Returns the transaction date
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * 
	 * @return Returns the amount of the transaction
	 */
	public double getAmount() {
		return amount;
	}
}
