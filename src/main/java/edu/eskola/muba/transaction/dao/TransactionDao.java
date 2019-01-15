package edu.eskola.muba.transaction.dao;

import java.util.List;

import edu.eskola.muba.transaction.entity.Transaction;

public interface TransactionDao {
	void add(Transaction transaction);
	List<Transaction> listTransactions();
}
