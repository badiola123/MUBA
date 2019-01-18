package edu.eskola.muba.team.dao;

import java.util.List;

import edu.eskola.muba.team.entity.Team;

/**
 * DAO of Team
 *
 * @author MUBA team
 * @version Final version
 */
public interface TeamDao {
	
	/**
	 * Adds a new team to the database
	 * 
	 * @param team Team to be added
	 */
	void addTeam(Team team);
	
	/**
	 * Gets the team associated to the team id
	 * 
	 * @param teamId Id to look for
	 * @return Team associated to the id
	 */
	Team getTeam(int teamId);
	
	/**
	 * Gets the team associated to the user id
	 * 
	 * @param userId Id to look for
	 * @return Team associated to the id
	 */
	Team getTeamByUserId(int userId);
	
	/**
	 * Updates the budget of a team
	 * 
	 * @param teamId Id to look for
	 * @param budget New budget
	 */
	void updateBudget(int teamId, int budget);
	
	/**
	 * Gets and returns a list containing all the teams from the database
	 * 
	 * @return A list containing all the registered teams
	 */
	List<Team> getAllTeams();
}