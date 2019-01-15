package edu.eskola.muba.transaction.service;

import java.util.List;

import edu.eskola.muba.transaction.entity.Transaction;

public interface TransactionService {
	void add(Transaction transaction);
	List<Transaction> listTransactions();
}
