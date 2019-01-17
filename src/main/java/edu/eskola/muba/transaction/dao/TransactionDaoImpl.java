package edu.eskola.muba.transaction.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.transaction.entity.Transaction;

/**
 * DAO implementation of Transaction
 * 
 * @author MUBA team
 * @version Final version
 * @see TransactionDao
 */

@Repository
public class TransactionDaoImpl implements TransactionDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void add(Transaction transaction) {
		sessionFactory.getCurrentSession().save(transaction);
	}

	@Override
	public List<Transaction> listTransactions() {
		@SuppressWarnings("unchecked")
		TypedQuery<Transaction> query=sessionFactory.getCurrentSession().createQuery("from Transaction");
	    return query.getResultList();
	}
}
