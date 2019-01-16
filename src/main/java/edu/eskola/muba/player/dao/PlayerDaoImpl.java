package edu.eskola.muba.player.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.player.entity.Player;

@Repository

public class PlayerDaoImpl implements PlayerDao {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addPlayer(Player player) {
		sessionFactory.getCurrentSession().save(player);
	}

	@Override
	public Player getPlayer(int playerId) {
		@SuppressWarnings("unchecked")

		TypedQuery<Player> query = sessionFactory.getCurrentSession()
				.createQuery("from Player P WHERE P.playerId = :playerId");
		query.setParameter("playerId", playerId);
		Player player = query.getSingleResult();
		return player;
	}

	@Override
	public List<Player> getTeamPlayers(int teamId) {
		@SuppressWarnings("unchecked")

		TypedQuery<Player> query = sessionFactory.getCurrentSession()
				.createQuery("from Player P WHERE P.teamId =:teamId");
		query.setParameter("teamId", teamId);
		return query.getResultList();
	}


	@Override
	public List<Player> getInitialTeamPlayers(int teamId) {
		@SuppressWarnings("unchecked")

		TypedQuery<Player> query = sessionFactory.getCurrentSession()
				.createQuery("from Player P WHERE P.teamId = :teamId AND P.initialFive=true");
		query.setParameter("teamId", teamId);
		return query.getResultList();
	}

	@Override
	public boolean checkPlayer(int playerId) {
		@SuppressWarnings("unchecked")

		TypedQuery<Player> query = sessionFactory.getCurrentSession()
				.createQuery("from Player P WHERE P.playerId =:playerId");
		query.setParameter("playerId", playerId);
		Player player = query.getSingleResult();

		return player != null ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateInitialPosition(int position, int playerId) {
		Query<Player> query;
		if (position == 0)
			query = sessionFactory.getCurrentSession().createQuery(
					"update Player set INITIALFIVE = FALSE, PLAYING = FALSE, POSITION = NULL where playerId = :playerId");
		else {
			query = sessionFactory.getCurrentSession()
					.createQuery("update Player set INITIALFIVE = TRUE, PLAYING = TRUE, POSITION = :position where playerId = :playerId");
			query.setParameter("position", position);
		}
		query.setParameter("playerId", playerId);
		query.executeUpdate();
	}

	@Override
	public int getLastId() {
		@SuppressWarnings("unchecked")
		TypedQuery<Player> query = sessionFactory.getCurrentSession()
				.createQuery("from Player order by playerId desc").setMaxResults(1);
		int id = query.getSingleResult().getPlayerId();
		return id;
	}

	@Override
	public List<Player> getAllPlayers() {
		@SuppressWarnings("unchecked")
		TypedQuery<Player> query = sessionFactory.getCurrentSession().createQuery("from Player");
		return query.getResultList();
	}
}
