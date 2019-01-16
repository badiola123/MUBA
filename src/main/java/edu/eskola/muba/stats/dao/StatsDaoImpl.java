package edu.eskola.muba.stats.dao;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.stats.entity.Stats;

@Repository
public class StatsDaoImpl implements StatsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addStats(Stats stats) {
		sessionFactory.getCurrentSession().save(stats);		
	}

	@Override
	public Stats getStats(int playerId, int gameId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Stats> query = sessionFactory.getCurrentSession()
				.createQuery("from Stats WHERE GAMEID=:gameId AND PLAYERID=:playerId");
		query.setParameter("gameId", gameId);
		query.setParameter("playerId", playerId);
		return query.getSingleResult();
	}

	@Override
	public void updateStats(int playerId, int gameId, String key, String value) {
		@SuppressWarnings("unchecked")
		Query<Stats> query = sessionFactory.getCurrentSession()
				.createQuery("update Stats set "+key+" = :value WHERE GAMEID=:gameId AND PLAYERID=:playerId");
		query.setParameter("gameId", gameId);
		query.setParameter("playerId", playerId);
		query.setParameter("value", value);
		query.executeUpdate();
		
	}

}
