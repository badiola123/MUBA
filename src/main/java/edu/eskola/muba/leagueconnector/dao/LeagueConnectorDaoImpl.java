package edu.eskola.muba.leagueconnector.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.leagueconnector.entity.LeagueConnector;

/**
 * DAO implementation of LeagueConnector
 * 
 * @author MUBA team
 * @version Final version
 * @see LeagueConnector
 */
@Repository
public class LeagueConnectorDaoImpl implements LeagueConnectorDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addLeagueConnector(LeagueConnector leagueConnector) {
		sessionFactory.getCurrentSession().save(leagueConnector);
		
	}

	@Override
	public List<LeagueConnector> getTeamLeagues(int teamId) {
		 @SuppressWarnings("unchecked")
	     TypedQuery<LeagueConnector> query=sessionFactory.getCurrentSession().createQuery("from LeagueConnector LC where LC.teamId = :teamId");
		 query.setParameter(teamId, teamId);
		 return query.getResultList();
	}

	@Override
	public List<LeagueConnector> getLeagueTeams(int leagueId) {
		 @SuppressWarnings("unchecked")
	     TypedQuery<LeagueConnector> query=sessionFactory.getCurrentSession().createQuery("from LeagueConnector LC where LC.leagueId = :leagueId");
		 query.setParameter(leagueId, leagueId);
		 return query.getResultList();
	}
	@Override
	public int getRegisteredTeams(int leagueId) {
		 @SuppressWarnings("unchecked")
	     TypedQuery<Long> query=sessionFactory.getCurrentSession().createQuery("select count(distinct LC.teamId) from LeagueConnector LC where LC.leagueId = :leagueId");
		 query.setParameter(leagueId, leagueId);
		 return Math.toIntExact(query.getSingleResult());
	}

	@Override
	public void leaveLeague(int leagueId, int userTeamId) {	
		Query query = sessionFactory.getCurrentSession().createQuery("delete from LeagueConnector where leagueId = :leagueId' AND teamId = :userTeamId");
		query.setParameter(leagueId, leagueId);
		query.setParameter(userTeamId, userTeamId);
		query.executeUpdate();
	}
}
