package edu.eskola.muba.game.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.user.entity.User;
@Repository
public class GameDaoImpl implements GameDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addGame(Game game) {
		sessionFactory.getCurrentSession().save(game);
	}

	@Override
	public Game getGame(int gameId) {
		 @SuppressWarnings("unchecked")
		 TypedQuery<Game> query=sessionFactory.getCurrentSession().createQuery("from Game G WHERE G.gameId ='"+gameId+"'");
		 Game game = query.getSingleResult();
		 return game;
	}

	@Override
	public List<Game> getLeagueGamesByStage(int leagueId, int stage) {
		@SuppressWarnings("unchecked")
	     TypedQuery<Game> query=sessionFactory.getCurrentSession().createQuery("from Game G where G.leagueId = '"+ leagueId + "' and G.stage = '"+ stage + "'");
	     return query.getResultList();
	}
	
	@Override
	public List<Game> getLeagueGames(int leagueId) {
		@SuppressWarnings("unchecked")
	     TypedQuery<Game> query=sessionFactory.getCurrentSession().createQuery("from Game G where G.leagueId = '"+ leagueId + "'");
	     return query.getResultList();
	}

}
