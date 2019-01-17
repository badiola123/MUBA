package edu.eskola.muba.characteristics.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.characteristics.entity.Characteristics;

/**
 * DAO implementation of Characteristics
 * 
 * @author MUBA team
 * @version Final version
 * @see CharacteristicsDao
 */
@Repository
public class CharacteristicsDaoImpl implements CharacteristicsDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addCharacteristics(Characteristics characteristics) {
		sessionFactory.getCurrentSession().save(characteristics);
	}

	@Override
	public Characteristics getCurrentCharacteristics(int playerId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Characteristics> query = sessionFactory.getCurrentSession()
				.createQuery("from Characteristics c WHERE c.playerId = :playerId ");
		query.setParameter("playerId", playerId);
		return query.getSingleResult();
	}

	@Override
	public List<Characteristics> getHistoricCharacteristics(int playerId) {
		@SuppressWarnings("unchecked")

		TypedQuery<Characteristics> query = sessionFactory.getCurrentSession()
				.createQuery("from Characteristics c WHERE c.playerId = :playerId");
		query.setParameter("playerId", playerId);
		return query.getResultList();
	}

	@Override
	public boolean checkCharacteristics(int characteristicsId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Characteristics> query = sessionFactory.getCurrentSession()
				.createQuery("from Characteristics C WHERE C.characteristicsId =:characteristicsId");
		query.setParameter("characteristicsId", characteristicsId);
		Characteristics characteristics = query.getSingleResult();
		return characteristics != null ? true : false;
	}



	@SuppressWarnings("unchecked")
	@Override
	public void updateCharacteristic(int charId, String name, int value) {
		Query<Characteristics> query = sessionFactory.getCurrentSession()
				.createQuery("update Characteristics set "+name +" = :value where CHARACTERISTICSID = :charId");
		query.setParameter("value", value);
		query.setParameter("charId", charId);
		query.executeUpdate();
	}

}