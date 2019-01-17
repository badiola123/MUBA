package edu.eskola.muba.transaction.service;

import java.util.List;

import edu.eskola.muba.transaction.entity.Transaction;

/**
 * Service of Transaction
 * 
 * @author MUBA team
 * @version Final version
 * @see TransactionDao
 */

public interface TransactionService {
	void add(Transaction transaction);
	List<Transaction> listTransactions();
}
