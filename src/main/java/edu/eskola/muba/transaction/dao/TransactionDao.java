package edu.eskola.muba.transaction.dao;

import java.util.List;

import edu.eskola.muba.transaction.entity.Transaction;

/**
 * DAO of Transaction
 *
 * @author MUBA team
 * @version Final version
 */

public interface TransactionDao {
	
	/**
	 * Adds a new transaction to the database
	 * 
	 * @param transaction Transaction to be added
	 */
	void add(Transaction transaction);
	
	/**
	 * Gets and returns a list containing all the transactions from the database
	 * 
	 * @return A list containing all the registered transactions
	 */
	List<Transaction> listTransactions();
}
