package edu.eskola.muba.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.eskola.muba.transaction.dao.TransactionDao;
import edu.eskola.muba.transaction.entity.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;
	
	@Transactional
	@Override
	public void add(Transaction transaction) {
		transactionDao.add(transaction);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transaction> listTransactions() {
		return transactionDao.listTransactions();
	}

}
