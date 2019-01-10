package edu.eskola.muba.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import edu.eskola.muba.gameMechanics.Match;
import edu.eskola.muba.gameMechanics.TeamGame;
import edu.eskola.muba.league.entity.League;
import edu.eskola.muba.league.service.LeagueService;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.leagueconnector.service.LeagueConnectorService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.game.service.GameService;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
import edu.eskola.muba.team.service.TeamServiceImpl;
import edu.eskola.muba.user.entity.User;

@Controller
@RequestMapping("league")
public class LeagueController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	TeamService teamService = context.getBean(TeamService.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);
	LeagueService leagueService = context.getBean(LeagueService.class);
	LeagueConnectorService leagueConnectorService = context.getBean(LeagueConnectorService.class);
	GameService gameService = context.getBean(GameService.class);
	
	
	private League league;
	List<League> leagues;
	Team leagueWinner;
	List<Game> gameList;
	

	@RequestMapping(value = "/goToLeagueList", method = RequestMethod.GET)
	public String goToLeagueList(HttpServletRequest request) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			leagues=leagueService.getActiveLeagues();
			request.setAttribute("leagues", leagues);
			direct = "leagueList";
		}
		return direct;
	}
	
	@RequestMapping(value = "/goToLeague", method = RequestMethod.GET)
	public String goToLeague(HttpServletRequest request) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			String leagueIdStr = request.getParameter("leagueId");
			int leagueId=Integer.parseInt(leagueIdStr);
			league=leagueService.getLeague(leagueId);
			if(league.getWinnerTeam()!=0) {
				leagueWinner=teamService.getTeam(league.getWinnerTeam());
			}else {
				leagueWinner=getImaginaryTeam();
			}
			int stages=league.getStages();
			List<ArrayList<Team>> listStageTeamList= new ArrayList<ArrayList<Team>>();
			for(int stageNumber=1; stageNumber<=stages; stageNumber++) {
				listStageTeamList.add((ArrayList<Team>) getStageTeamsOrdered(leagueId, stageNumber));
			}
			gameList=gameService.getLeagueGames(leagueId);
			HashMap<Integer, String> teamMap=getTeamMap();
			
			request.setAttribute("teamMap", teamMap);
			request.setAttribute("gameList", gameList);
			request.setAttribute("league", league);
			request.setAttribute("leagueWinner", leagueWinner);
			request.setAttribute("listStageTeamList", listStageTeamList);

			
			
			direct = "league";
		}
		return direct;
	}
	
	public List<Team> getStageTeamsOrdered(int leagueId, int stage){
		List<Team> stageTeams=new ArrayList<Team>();
		List<Game> leagueGames=new ArrayList<Game>();
		Game game;
		leagueGames=gameService.getLeagueGamesByStage(leagueId, stage);
		Iterator<Game> itr = leagueGames.iterator();
		while(itr.hasNext()) {
			 game = itr.next();
			 if(game.getLocalTeamId()!=0) {
				 stageTeams.add(teamService.getTeam(game.getLocalTeamId()));
			 }else {
				 stageTeams.add(getImaginaryTeam());
			 }
			 if(game.getVisitorTeamId()!=0) {
				 stageTeams.add(teamService.getTeam(game.getVisitorTeamId()));
			 }else {
				 stageTeams.add(getImaginaryTeam());
			 }
		}
		return stageTeams;
		
	}
	
	public List<Team> getGameResultsWithTeams(int leagueId, int stage){
		List<Team> stageTeams=new ArrayList<Team>();
		List<Game> leagueGames=new ArrayList<Game>();
		Game game;
		leagueGames=gameService.getLeagueGamesByStage(leagueId, stage);
		Iterator<Game> itr = leagueGames.iterator();
		while(itr.hasNext()) {
			 game = itr.next();
			 if(game.getLocalTeamId()!=0) {
				 stageTeams.add(teamService.getTeam(game.getLocalTeamId()));
			 }else {
				 stageTeams.add(getImaginaryTeam());
			 }
			 if(game.getVisitorTeamId()!=0) {
				 stageTeams.add(teamService.getTeam(game.getVisitorTeamId()));
			 }else {
				 stageTeams.add(getImaginaryTeam());
			 }
		}
		return stageTeams;
		
	}
	
	public Team getImaginaryTeam() {
		Team t=new Team(0,"NoTeam",0,0);
		return t;
	}
	
	public HashMap<Integer, String> getTeamMap(){
		HashMap<Integer, String> teamMap 
	    = new HashMap<Integer,String>();
		List<LeagueConnector> leagueParticipants=leagueConnectorService.getLeagueTeams(league.getLeagueId());
		Iterator<LeagueConnector> it= leagueParticipants.iterator();
		while(it.hasNext()) {
			LeagueConnector lc=it.next();
			teamMap.put(lc.getTeamId(), teamService.getTeam(lc.getTeamId()).getTeamName());
		}
		return teamMap;
	}
/*
	@RequestMapping(value = "/play", method = RequestMethod.GET)
	public String play(HttpServletRequest request) {
		goToMatch(request);
		List<Characteristics> yourChars = new ArrayList<>();
		List<Characteristics> enemyChars = new ArrayList<>();
		doChars(yourChars, yourPlayers);
		doChars(enemyChars, enemyPlayers);
		TeamGame yourTeamGame = new TeamGame(yourTeam.getTeamName(), yourChars);
		TeamGame enemyTeamGame = new TeamGame(enemyTeam.getTeamName(), enemyChars);
		Match match = new Match(yourTeamGame, enemyTeamGame);
		match.startMatch();
		request.setAttribute("score", +match.getTeamApoints() + " : " + match.getTeamBpoints());
		return "match";
	}
	
	private void doChars(List<Characteristics> chars, List<Player> players) {
		for(int i=0; i<players.size(); i++) {
			chars.add( characteristicsService.getCurrentCharacteristics(players.get(i).getPlayerId()) );
		}
	}
	*/

}
