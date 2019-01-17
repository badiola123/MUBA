package edu.eskola.muba.team.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.user.dao.UserDao;

/**
 * DAO implementation of Team
 * 
 * @author MUBA team
 * @version Final version
 * @see TeamDao
 */

@Repository
public class TeamDaoImpl implements TeamDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addTeam(Team team) {
		sessionFactory.getCurrentSession().save(team);
	}

	@Override
	public Team getTeam(int teamId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Team> query = sessionFactory.getCurrentSession().createQuery("from Team T WHERE T.teamId = :teamId");
		query.setParameter("teamId", teamId);
		Team team = query.getSingleResult();
		return team;
	}

	@Override
	public Team getTeamByUserId(int userId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Team> query = sessionFactory.getCurrentSession().createQuery("from Team T WHERE T.userId = :userId");
		query.setParameter("userId", userId);
		Team team = query.getSingleResult();
		return team;
	}

	@Override
	public void updateBudget(int teamId, int budget) {
		@SuppressWarnings("unchecked")
		Query<Team> query = sessionFactory.getCurrentSession()
					.createQuery("update Team set BUDGET = :budget where TEAMID = :teamId");
		query.setParameter("teamId", teamId);
		query.setParameter("budget", budget);
		query.executeUpdate();
	}

	@Override
	public List<Team> getAllTeams() {
		@SuppressWarnings("unchecked")
		Query<Team> query = sessionFactory.getCurrentSession()
				.createQuery("from Team");
		return query.getResultList();
	}

}