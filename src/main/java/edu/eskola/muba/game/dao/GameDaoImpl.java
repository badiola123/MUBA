package edu.eskola.muba.game.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.user.entity.User;
import edu.eskola.muba.team.entity.Team;
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
		TypedQuery<Game> query = sessionFactory.getCurrentSession()
				.createQuery("from Game G WHERE G.gameId ='" + gameId + "'");
		Game game = query.getSingleResult();
		return game;
	}

	@Override
	public List<Game> getLeagueGamesByStage(int leagueId, int stage) {
		TypedQuery<Game> query = sessionFactory.getCurrentSession()
				.createQuery("from Game G where G.leagueId = '" + leagueId + "' and G.stage = '" + stage + "'");
		return query.getResultList();
	}

	@Override
	public List<Game> getLeagueGames(int leagueId) {
		TypedQuery<Game> query = sessionFactory.getCurrentSession()
				.createQuery("from Game G where G.leagueId = '" + leagueId + "'");
		return query.getResultList();
	}

	@Override
	public Game getLastPlayedGame(int teamId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Game> query = sessionFactory.getCurrentSession()
				.createQuery("from Game G where G.played=1 and (G.localTeamId = :teamId or G.visitorTeamId = :teamId) order by G.gameDate DESC");
		query.setParameter("teamId", teamId);
		Game game;
		try {
			game = query.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			game = null;
		}
		return game;
	}

	@Override
	public Game nextGame(int teamId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Game> query = sessionFactory.getCurrentSession()
				.createQuery("from Game G where G.played=0 and (G.localTeamId = :teamId or G.visitorTeamId = :teamId) order by G.gameDate ASC");
		query.setParameter("teamId", teamId);
		Game game;
		try {
			game = query.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			game = null;
		}
		return game;
	}

	@Override
	public void updateGame(int gameId, String key, String value) {
		@SuppressWarnings("unchecked")
		Query<Game> query = sessionFactory.getCurrentSession()
				.createQuery("update Game set "+key+" = :value where GAMEID = :gameId");
		query.setParameter("gameId", gameId);
		query.setParameter("value", value);
		query.executeUpdate();
	}

	@Override
	public void moveTeamUp(int teamId, int leagueId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Game> query = sessionFactory.getCurrentSession()
				.createQuery("from Game G where G.played=0 and G.leagueId =:leagueId and (G.localTeamId=0 or G.visitorTeamId=0) order by G.gameId ASC");
		query.setParameter("leagueId", leagueId);
		Game newGame = query.setMaxResults(1).getSingleResult();
		if(newGame.getLocalTeamId()==0) {
			@SuppressWarnings("unchecked")
			Query<Game> queryUp = sessionFactory.getCurrentSession()
					.createQuery("update Game set LOCALTEAMID = :teamId where gameId = :gameId");
			queryUp.setParameter("teamId", teamId);
			queryUp.setParameter("gameId", newGame.getGameId());
			queryUp.executeUpdate();
		}
		else {
			@SuppressWarnings("unchecked")
			Query<Game> queryUp = sessionFactory.getCurrentSession()
					.createQuery("update Game set VISITORTEAMID = :teamId where gameId = :gameId");
			queryUp.setParameter("teamId", teamId);
			queryUp.setParameter("gameId", newGame.getGameId());
			queryUp.executeUpdate();
		}
	}

}