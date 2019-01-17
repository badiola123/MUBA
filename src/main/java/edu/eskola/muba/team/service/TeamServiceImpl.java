package edu.eskola.muba.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.eskola.muba.team.dao.TeamDao;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.user.dao.UserDao;

/**
 * Service implementation of Team
 * 
 * @author MUBA team
 * @version Final version
 * @see TeamDao
 */

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	TeamDao teamDao;

	@Transactional
	@Override
	public void addTeam(Team team) {
		teamDao.addTeam(team);
	}

	@Transactional
	@Override
	public Team getTeam(int teamId) {
		return teamDao.getTeam(teamId);
	}

	@Transactional
	@Override
	public Team getTeamByUserId(int userId) {
		return teamDao.getTeamByUserId(userId);
	}


	@Transactional
	@Override
	public void updateBudget(int teamId, int budget) {
		teamDao.updateBudget(teamId, budget);
	}
	
	@Transactional
	@Override
	public List<Team> getAllTeams() {
		return teamDao.getAllTeams();
	}

}