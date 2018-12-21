package edu.eskola.muba.team.dao;



import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.team.entity.Team;

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
		TypedQuery<Team> query = sessionFactory.getCurrentSession().createQuery("from TEAM T WHERE T.TEAMID = '" + teamId + "'");
		Team team = query.getSingleResult();
		return team;
	}

	@Override
	public Team getTeamByUserId(int userId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Team> query = sessionFactory.getCurrentSession().createQuery("from Team T WHERE T.userId = " + userId);
		Team team = query.getSingleResult();
		return team;
	}

}
