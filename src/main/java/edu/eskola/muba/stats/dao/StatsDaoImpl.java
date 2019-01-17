package edu.eskola.muba.stats.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.stats.entity.Stats;

/**
 * DAO implementation of Stats
 * 
 * @author MUBA team
 * @version Final version
 * @see StatsDao
 */
@Repository
public class StatsDaoImpl implements StatsDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final String gameIdString = "gameId";
	private static final String playerIdString = "playerId";
	
	@Override
	public void addStats(Stats stats) {
		sessionFactory.getCurrentSession().save(stats);		
	}

	@Override
	public Stats getStats(int playerId, int gameId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Stats> query = sessionFactory.getCurrentSession()
				.createQuery("from Stats WHERE GAMEID=:gameId AND PLAYERID=:playerId");
		query.setParameter(gameIdString, gameId);
		query.setParameter(playerIdString, playerId);
		return query.getSingleResult();
	}

	@Override
	public void updateStats(int playerId, int gameId, String key, String value) {
		@SuppressWarnings("unchecked")
		Query<Stats> query = sessionFactory.getCurrentSession()
				.createQuery("update Stats set "+key+" = :value WHERE GAMEID=:gameId AND PLAYERID=:playerId");
		query.setParameter(gameIdString, gameId);
		query.setParameter(playerIdString, playerId);
		query.setParameter("value", value);
		query.executeUpdate();
	}

	@Override
	public List<Stats> getAllStatsOfPlayer(int playerId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Stats> query = sessionFactory.getCurrentSession()
				.createQuery("from Stats WHERE PLAYERID=:playerId");
		query.setParameter(playerIdString, playerId);
		return query.getResultList();
	}

	@Override
	public List<Stats> getAllStatsOfTeamOfGame(int teamId, int gameId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Stats> query = sessionFactory.getCurrentSession()
				.createQuery("from Stats WHERE TEAMID=:teamId AND GAMEID=:gameId");
		query.setParameter("teamId", teamId);
		query.setParameter(gameIdString, gameId);
		return query.getResultList();
	}

}
