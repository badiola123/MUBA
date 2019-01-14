package edu.eskola.muba.leagueconnector.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.TransactionManager;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.league.entity.League;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.team.entity.Team;

@Repository
public class LeagueConnectorDaoImpl implements LeagueConnectorDao {

	@Autowired
	private SessionFactory sessionFactory;
	private TransactionManager transactionManager;
	
	@Override
	public void addLeagueConnector(LeagueConnector leagueConnector) {
		sessionFactory.getCurrentSession().save(leagueConnector);
		
	}

	@Override
	public List<LeagueConnector> getTeamLeagues(int teamId) {
		 @SuppressWarnings("unchecked")
	     TypedQuery<LeagueConnector> query=sessionFactory.getCurrentSession().createQuery("from LeagueConnector LC where LC.teamId = '"+ teamId + "'");
	     return query.getResultList();
	}

	@Override
	public List<LeagueConnector> getLeagueTeams(int leagueId) {
		 @SuppressWarnings("unchecked")
	     TypedQuery<LeagueConnector> query=sessionFactory.getCurrentSession().createQuery("from LeagueConnector LC where LC.leagueId = '"+ leagueId + "'");
	     return query.getResultList();
	}
	@Override
	public int getRegisteredTeams(int leagueId) {
		 @SuppressWarnings("unchecked")
	     TypedQuery<Long> query=sessionFactory.getCurrentSession().createQuery("select count(distinct LC.teamId) from LeagueConnector LC where LC.leagueId = '"+ leagueId + "'");
	     return Math.toIntExact(query.getSingleResult());
	}

	@Override
	public void leaveLeague(int leagueId, int userTeamId) {	
		@SuppressWarnings("unchecked") 
		 Query query = sessionFactory.getCurrentSession().createQuery("delete from LeagueConnector where leagueId = '" + leagueId + "' AND teamId = '"+ userTeamId+"'");
		 int result = query.executeUpdate();
	}
}
