package edu.eskola.muba.league.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.league.entity.League;
import edu.eskola.muba.user.entity.User;

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
	public List<League> getActiveLeagues() {
		 @SuppressWarnings("unchecked")
	     TypedQuery<League> query=sessionFactory.getCurrentSession().createQuery("from League L where L.startDate < NOW() AND L.endDate > NOW()");
	     return query.getResultList();
	}

}
