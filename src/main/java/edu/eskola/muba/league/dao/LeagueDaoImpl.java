package edu.eskola.muba.league.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.eskola.muba.league.entity.League;


@Repository
public class LeagueDaoImpl implements LeagueDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addLeague(League league) {
		sessionFactory.getCurrentSession().save(league);
	}

	@Override
	public League getLeague(int leagueId) {
		 @SuppressWarnings("unchecked")
		 TypedQuery<League> query=sessionFactory.getCurrentSession().createQuery("from League L WHERE L.leagueId ='"+leagueId+ "'");
		 League league = query.getSingleResult();
		 return league;
	}

	@Override
	public List<League> getActiveLeagues(int teamId) {
		@SuppressWarnings("unchecked")
	     TypedQuery<League> query=sessionFactory.getCurrentSession().createQuery("SELECT L from League L "+
	     		"INNER JOIN LeagueConnector LC ON L.leagueId=LC.leagueId "+ 
	     		"INNER JOIN Team T ON LC.teamId=T.teamId " + 
	     		"WHERE T.teamId ='"+ teamId+"' AND L.started = true AND L.endDate > NOW()");
	     return query.getResultList();
	}
	
	@Override
	public List<League> getFinishedLeagues(int teamId){
		@SuppressWarnings("unchecked")
	     TypedQuery<League> query=sessionFactory.getCurrentSession().createQuery("SELECT L from League L "+
	     		"INNER JOIN LeagueConnector LC ON L.leagueId=LC.leagueId "+ 
	     		"INNER JOIN Team T ON LC.teamId=T.teamId " + 
	     		"WHERE T.teamId ='"+ teamId+"' AND L.endDate < NOW()");
	     return query.getResultList();
	}
	@Override
	public List<League> getNotStartedLeagues(int teamId){
		@SuppressWarnings("unchecked")
	     TypedQuery<League> query=sessionFactory.getCurrentSession().createQuery("SELECT L from League L "+
	     		"INNER JOIN LeagueConnector LC ON L.leagueId=LC.leagueId "+ 
	     		"INNER JOIN Team T ON LC.teamId=T.teamId " + 
	     		"WHERE T.teamId ='"+ teamId+"' AND L.started = false");
	     return query.getResultList();
	}
	@Override
	public List<Integer> getAvailableLeagues(int teamId){
		@SuppressWarnings("unchecked")
	     TypedQuery<Integer> query=sessionFactory.getCurrentSession().createQuery(
	    		 "SELECT DISTINCT(LC.leagueId) from LeagueConnector LC INNER JOIN League L ON L.leagueId=LC.leagueId WHERE L.started = false and"+
	    		 " LC.leagueId NOT IN(SELECT distinct(LC.leagueId) from LeagueConnector LC INNER JOIN League L ON L.leagueId=LC.leagueId"+
	    		 " WHERE  L.started = false and LC.teamId = '"+teamId+"')");
	     return query.getResultList();
	}
	
	
	@Override
	public int getLastLeagueId() {
		@SuppressWarnings("unchecked")
		TypedQuery<League> query=sessionFactory.getCurrentSession().createQuery("from League order by leagueId desc").setMaxResults(1);
		League league = query.getSingleResult();
		return league.getLeagueId();
		
	}

	@Override
	public boolean checkIfHost(int leagueId, int userTeamId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Integer> query=sessionFactory.getCurrentSession().createQuery("select hostTeam from League L where L.leagueId = '"+ leagueId + "'");
	    int hostId= Math.toIntExact(query.getSingleResult());
		return (hostId==userTeamId);
	}

	@Override
	public int getNeededTeams(int leagueId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Integer> query=sessionFactory.getCurrentSession().createQuery("select stages from League L where L.leagueId = '"+ leagueId + "'");
		int stages = query.getSingleResult();
		return (stages==2 ? 4:8);
	}

	@Override
	public void startLeague(int leagueId) {
		@SuppressWarnings("unchecked")
		Query query=sessionFactory.getCurrentSession().createQuery("UPDATE League set started = true where leagueId = '"+leagueId+"'");
		query.executeUpdate();
	}

	@Override
	public void changeLeagueDates(int leagueId, Date startDate, Date endDate) {
		@SuppressWarnings("unchecked")
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start=dateFormat.format(startDate);
		String end=dateFormat.format(endDate);
		Query query=sessionFactory.getCurrentSession().createQuery("UPDATE League set startDate = '"+start+"', endDate = '"+end+"' where leagueId = '"+leagueId+"'");
		query.executeUpdate();
	}

	@Override
	public void deleteLeague(int leagueId) {
		@SuppressWarnings("unchecked") 
		Query query = sessionFactory.getCurrentSession().createQuery("delete from League where leagueId = '" + leagueId + "'");
		int result = query.executeUpdate();
	}

}
