package edu.eskola.muba.league.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.eskola.muba.league.dao.LeagueDao;
import edu.eskola.muba.league.entity.League;

@Service
public class LeagueServiceImpl implements LeagueService{

	@Autowired
	LeagueDao leagueDao;
	
	@Transactional
	@Override
	public void addLeague(League league) {
		leagueDao.addLeague(league);
	}

	@Transactional
	@Override
	public League getLeague(int leagueId) {
		return leagueDao.getLeague(leagueId);
	}

	@Transactional
	@Override
	public List<League> getActiveLeagues() {
		return leagueDao.getActiveLeagues();
	}

}
