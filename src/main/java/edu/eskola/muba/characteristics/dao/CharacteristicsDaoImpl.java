package edu.eskola.muba.characteristics.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.characteristics.entity.Characteristics;

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
		TypedQuery<Characteristics> query = sessionFactory.getCurrentSession().createQuery("from Characteristics WHERE playerId = '" + playerId + "' and cDate = (select max(cDate) from Characteristics where playerId = '" + playerId + "')");
		Characteristics c = query.getSingleResult();
		return c;
	}

	@Override
	public List<Characteristics> getHistoricCharacteristics(int playerId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Characteristics> query = sessionFactory.getCurrentSession().createQuery("from Characteristics c WHERE c.playerId = '" + playerId + "'");
		return query.getResultList();
	}

	@Override
	public boolean checkCharacteristics(int characteristicsId) {
		@SuppressWarnings("unchecked")
	   TypedQuery<Characteristics> query=sessionFactory.getCurrentSession().createQuery("from Characteristics C WHERE C.characteristicsId ='"+characteristicsId+"'");
	   Characteristics characteristics = query.getSingleResult();
	   return characteristics!=null ? true : false;
	}
	
	

}
