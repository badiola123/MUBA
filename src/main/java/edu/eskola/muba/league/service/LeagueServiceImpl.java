package edu.eskola.muba.league.service;

import java.util.Date;
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
	public List<League> getActiveLeagues(int teamId) {
		return leagueDao.getActiveLeagues(teamId);
	}
	
	@Transactional
	@Override
	public List<Integer> getAvailableLeagues(int teamId) {
		return leagueDao.getAvailableLeagues(teamId);
	}
	
	@Transactional
	@Override
	public List<League> getNotStartedLeagues(int teamId) {
		return leagueDao.getNotStartedLeagues(teamId);
	}
	
	@Transactional
	@Override
	public List<League> getFinishedLeagues(int teamId) {
		return leagueDao.getFinishedLeagues(teamId);
	}
	@Transactional
	@Override
	public int getLastLeagueId() {
		return leagueDao.getLastLeagueId();
	}

	@Transactional
	@Override
	public boolean checkIfHost(int leagueId, int userTeamId) {
		return leagueDao.checkIfHost(leagueId, userTeamId);
	}
	@Transactional
	@Override
	public int getNeededTeams(int leagueId) {
		return leagueDao.getNeededTeams(leagueId);
	}
	@Transactional
	@Override
	public void startLeague(int leagueId) {
		leagueDao.startLeague(leagueId);
	}
	@Transactional
	@Override
	public void changeLeagueDates(int leagueId, Date startDate, Date endDate) {
		leagueDao.changeLeagueDates(leagueId, startDate, endDate);
	}
	@Transactional
	@Override
	public void deleteLeague(int leagueId) {
		leagueDao.deleteLeague(leagueId);
	}
	@Transactional
	@Override
	public List<League> getAllLeagues() {
		return leagueDao.getAllLeagues();
	}

	@Transactional
	@Override
	public void updateLeague(int leagueId, String key, String value) {
		leagueDao.updateLeague(leagueId, key, value);
	}

}
