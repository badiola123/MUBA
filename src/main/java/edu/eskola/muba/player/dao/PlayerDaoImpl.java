package edu.eskola.muba.player.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.player.entity.Player;

@Repository
public class PlayerDaoImpl implements PlayerDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addPlayer(Player player) {
		sessionFactory.getCurrentSession().save(player);
	}

	@Override
	public Player getPlayer(int playerId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Player> query = sessionFactory.getCurrentSession().createQuery("from Player P WHERE P.playerId = '" + playerId + "'");
		Player player = query.getSingleResult();
		return player;
	}

	@Override
	public List<Player> getTeamPlayers(int teamId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Player> query = sessionFactory.getCurrentSession().createQuery("from Player P WHERE P.teamId = '" + teamId + "'");
		return query.getResultList();
	}
	
	@Override
	public List<Player> getInitialTeamPlayers(int teamId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Player> query = sessionFactory.getCurrentSession().createQuery("from Player P WHERE P.teamId = '" + teamId + "' AND P.initialFive=true");
		return query.getResultList();
	}

	@Override
	public boolean checkPlayer(int playerId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Player> query=sessionFactory.getCurrentSession().createQuery("from Player P WHERE P.playerId ='"+playerId+"'");
		Player player = query.getSingleResult();
		return player!=null ? true : false;
	}
}
