package edu.eskola.muba.stats.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.stats.entity.Stats;
import edu.eskola.muba.stats.entity.StatsId;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.user.entity.User;

@Repository
public class StatsDaoImpl implements StatsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addStats(Stats stats) {
		sessionFactory.getCurrentSession().save(stats);		
	}

	@Override
	public Stats getStats(StatsId statsId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Stats> query = sessionFactory.getCurrentSession()
				.createQuery("from Stats WHERE GAMEID=:gameId AND PLAYERID=:playerId");
		query.setParameter("gameId", statsId.getGameId());
		query.setParameter("playerId", statsId.getPlayerId());
		return query.getSingleResult();
	}

	@Override
	public void updateStats(StatsId statsId, String key, String value) {
		@SuppressWarnings("unchecked")
		Query<Stats> query = sessionFactory.getCurrentSession()
				.createQuery("update Stats set "+key+" = :value WHERE GAMEID=:gameId AND PLAYERID=:playerId");
		query.setParameter("gameId", statsId.getGameId());
		query.setParameter("playerId", statsId.getPlayerId());
		query.setParameter("value", value);
		query.executeUpdate();
		
	}

}
