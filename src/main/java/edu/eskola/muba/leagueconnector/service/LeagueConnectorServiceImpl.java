package edu.eskola.muba.leagueconnector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.eskola.muba.league.entity.League;
import edu.eskola.muba.leagueconnector.dao.LeagueConnectorDao;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.team.entity.Team;

@Service
public class LeagueConnectorServiceImpl implements LeagueConnectorService{

	@Autowired
	LeagueConnectorDao leagueConnectorDao;
	
	@Transactional
	@Override
	public void addLeagueConnector(LeagueConnector leagueConnector) {
		leagueConnectorDao.addLeagueConnector(leagueConnector);
	}

	@Transactional
	@Override
	public List<LeagueConnector> getTeamLeagues(int teamId) {
		return leagueConnectorDao.getTeamLeagues(teamId);
	}

	@Transactional
	@Override
	public List<LeagueConnector> getLeagueTeams(int leagueId) {
		return leagueConnectorDao.getLeagueTeams(leagueId);
	}
	
	@Transactional
	@Override
	public int getRegisteredTeams(int leagueId) {
		return leagueConnectorDao.getRegisteredTeams(leagueId);
	}

	@Transactional
	@Override
	public void leaveLeague(int leagueId, int userTeamId) {
		leagueConnectorDao.leaveLeague(leagueId, userTeamId);	
	}

}
