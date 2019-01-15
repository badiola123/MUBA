package edu.eskola.muba.game.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.user.entity.User;

@Repository
public class GameDaoImpl implements GameDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addGame(Game game) {
		sessionFactory.getCurrentSession().save(game);
	}

	@Override
	public Game getGame(int gameId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Game> query = sessionFactory.getCurrentSession()
				.createQuery("from Game G WHERE G.gameId ='" + gameId + "'");
		Game game = query.getSingleResult();
		return game;
	}

	@Override
	public List<Game> getLeagueGamesByStage(int leagueId, int stage) {
		@SuppressWarnings("unchecked")
		TypedQuery<Game> query = sessionFactory.getCurrentSession()
				.createQuery("from Game G where G.leagueId = '" + leagueId + "' and G.stage = '" + stage + "'");
		return query.getResultList();
	}

	@Override
	public List<Game> getLeagueGames(int leagueId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Game> query = sessionFactory.getCurrentSession()
				.createQuery("from Game G where G.leagueId = '" + leagueId + "'");
		return query.getResultList();
	}

	@Override
	public Game getLastPlayedGame(int teamId) {
		@SuppressWarnings("unchecked")
		// (from GAME G where G.localTeamId = :teamId OR G.visitorTeamId = :teamId)
		TypedQuery<Game> query1 = sessionFactory.getCurrentSession()
				.createQuery("from Game B where B.played = TRUE AND B.localTeamId = :teamId order by B.gameDate DESC");
		@SuppressWarnings("unchecked")
		TypedQuery<Game> query2 = sessionFactory.getCurrentSession().createQuery(
				"from Game B where B.played = TRUE AND B.visitorTeamId = :teamId order by B.gameDate DESC");
		query1.setParameter("teamId", teamId);
		query2.setParameter("teamId", teamId);
		Game game1;
		Game game2;
		try {
			game1 = query1.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			game1 = null;
		}
		try {
			game2 = query2.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			game2 = null;
		}
		Game game;
		if (game1 == null && game2 == null)
			game = null;
		else if (game1 == null)
			game = game2;
		else if (game2 == null)
			game = game1;
		else if (game1.getGameDate().compareTo(game2.getGameDate()) > 0)
			game = game1;
		else
			game = game2;
		return game;
	}

	@Override
	public Game nextGame(int teamId) {
		@SuppressWarnings("unchecked")
		// (from GAME G where G.localTeamId = :teamId OR G.visitorTeamId = :teamId)
		TypedQuery<Game> query1 = sessionFactory.getCurrentSession()
				.createQuery("from Game B where B.played = '0' AND B.localTeamId = :teamId order by B.gameDate ASC");
		@SuppressWarnings("unchecked")
		TypedQuery<Game> query2 = sessionFactory.getCurrentSession()
				.createQuery("from Game B where B.played = '0' AND B.visitorTeamId = :teamId order by B.gameDate ASC");
		query1.setParameter("teamId", teamId);
		query2.setParameter("teamId", teamId);
		Game game1;
		Game game2;
		try {
			game1 = query1.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			game1 = null;
		}
		try {
			game2 = query2.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			game2 = null;
		}
		Game game;
		if (game1 == null && game2 == null)
			game = null;
		else if (game1 == null)
			game = game2;
		else if (game2 == null)
			game = game1;
		else if (game1.getGameDate().compareTo(game2.getGameDate()) > 0)
			game = game1;
		else
			game = game2;
		return game;
	}

	@Override
	public void updateGame(int gameId, String key, String value) {
		@SuppressWarnings("unchecked")
		Query<Game> query = sessionFactory.getCurrentSession()
				.createQuery("update Game set "+key+" = :value where gameId = :gameId");
		query.setParameter("gameId", gameId);
		query.setParameter("value", value);
		query.executeUpdate();

	}

	@Override
	public void moveTeamUp(int teamId, int leagueId) {
		//select * from Game G where G.played=0 and G.leagueId = 3 and (G.localTeamId=0 or G.visitorTeamId=0)

	}

}
