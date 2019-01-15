package edu.eskola.muba.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import edu.eskola.muba.league.entity.League;
import edu.eskola.muba.league.service.LeagueService;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.leagueconnector.service.LeagueConnectorService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.apache.commons.lang3.time.DateUtils;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.game.service.GameService;
import edu.eskola.muba.gameMechanics.Match;
import edu.eskola.muba.gameMechanics.PlayerGame;
import edu.eskola.muba.gameMechanics.TeamGame;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.schedule.TimeComponent;
import edu.eskola.muba.stats.entity.Stats;
import edu.eskola.muba.stats.entity.StatsId;
import edu.eskola.muba.stats.service.StatsService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
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
	StatsService statsService = context.getBean(StatsService.class);
	
	
	private League league;
	List<League> leagues;
	Team leagueWinner;
	List<Game> gameList;
	

	@RequestMapping(value = "/goToLeagueList", method = RequestMethod.GET)
	public String goToLeagueList(HttpServletRequest request, RedirectAttributes redir) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			Team userTeam= teamService.getTeamByUserId(user.getUserId());
			String category = request.getParameter("category");
			leagues=leagueService.getActiveLeagues(userTeam.getTeamId());
			HashMap<Integer, Integer> joinedTeamsMap;
			if(category==null) category="running";
			switch (category) {
				case "available":
					List<Integer>leagueIdList;
					leagueIdList=leagueService.getAvailableLeagues(userTeam.getTeamId());
					leagues=getLeaguesFromIdList(leagueIdList);
					joinedTeamsMap=getLeagueJoinedTeams(leagues);
					request.setAttribute("joinedTeamsMap", joinedTeamsMap);
					break;
				case "finished":
					leagues=leagueService.getFinishedLeagues(userTeam.getTeamId());
					break;
				case "notStarted":
					leagues=leagueService.getNotStartedLeagues(userTeam.getTeamId());
					joinedTeamsMap=getLeagueJoinedTeams(leagues);
					request.setAttribute("joinedTeamsMap", joinedTeamsMap);
					break;
				case "running":
				default:
					leagues=leagueService.getActiveLeagues(userTeam.getTeamId());
					break;
			}	
			request.setAttribute("category", category);
			request.setAttribute("leagues", leagues);
			direct = "leagueList";
		}
		else {
			redir.addFlashAttribute("warning", "login.warning");
		}
		return direct;
	}
	
	
	public List<League> getLeaguesFromIdList(List<Integer> leagueIdList) {
		List<League>leagueList=new ArrayList<League>();
		Iterator<Integer> it= leagueIdList.iterator();
		while(it.hasNext()) {
			leagueList.add(leagueService.getLeague(it.next()));
		}
		return leagueList;
	}



	@RequestMapping(value = "/leagueActions", method = RequestMethod.GET)
	public ModelAndView manageLeagueActions(HttpServletRequest request, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			Team userTeam= teamService.getTeamByUserId(user.getUserId());
			int userTeamId=userTeam.getTeamId();
			String action = request.getParameter("action");
			String redirectionCategory="";
			if(action!=null) {
				int leagueId=Integer.parseInt(request.getParameter("leagueId"));
				if(action.equals("leave")) {
					boolean host=leagueService.checkIfHost(leagueId, userTeamId);
					List<LeagueConnector> leagueTeams=leagueConnectorService.getLeagueTeams(leagueId);
					int nTeams=leagueTeams.size();
					if(host == true && nTeams==1) {
						leagueConnectorService.leaveLeague(leagueId, userTeamId);
						leagueService.deleteLeague(leagueId);
						modelAndView.addObject("info", "league.deleted");
					}else if(host==true) {
						modelAndView.addObject("info", "league.cantLeave");
					}else {
						leagueConnectorService.leaveLeague(leagueId, userTeamId);
						modelAndView.addObject("info", "league.left");
					}
					
					redirectionCategory="notStarted";
				}
				if(action.equals("join")) {
					int members=leagueConnectorService.getRegisteredTeams(leagueId);
					int neededTeams=leagueService.getNeededTeams(leagueId);
					if(members<neededTeams) {
						LeagueConnector lc= new LeagueConnector(leagueId, userTeamId);
						leagueConnectorService.addLeagueConnector(lc);
						members=leagueConnectorService.getRegisteredTeams(leagueId);
						if(members==neededTeams) {
							startLeague(leagueId);
						}
					}else {
						modelAndView.addObject("error", "league.full");
					}
					redirectionCategory="available";
				}
			}
			modelAndView.setViewName("leagueList");
			modelAndView.addObject("category", redirectionCategory);
		}else {
			redir.addFlashAttribute("warning", "login.warning");
		}
		
		return modelAndView;
	}

	public void startLeague(int leagueId) {
		Date leagueStartDate = new Date(); //League start date
		Date leagueEndDate;
		Date gamesDate=DateUtils.addMinutes(leagueStartDate, 30);
		League league=leagueService.getLeague(leagueId);
		int stages=league.getStages();
		int daysToChange;
		int teamsAtStage;
		if(stages==2) {
			daysToChange=3;
			teamsAtStage=4;
		}
		else {
			daysToChange=7;
			teamsAtStage=8;
		}
		leagueEndDate=DateUtils.addDays(leagueStartDate, daysToChange);
		leagueEndDate=DateUtils.addMinutes(leagueEndDate, 90); //League end date
		leagueService.changeLeagueDates(leagueId, leagueStartDate, leagueEndDate);
		leagueService.startLeague(leagueId);
		int localTeamId;
		int visitorTeamId;
		List<LeagueConnector> leagueConnectorList = leagueConnectorService.getLeagueTeams(leagueId);
		Iterator<LeagueConnector> it=leagueConnectorList.iterator();
		for(int stage=1;stage<=stages;stage++) {
			
			for(int gameNumber=1;gameNumber<=(teamsAtStage/2);gameNumber++) {
				if(stage==1) {
					localTeamId=it.next().getTeamId();
					visitorTeamId=it.next().getTeamId();
				}else {
					localTeamId=0;
					visitorTeamId=0;
				}
				final Game game = new Game(0,gamesDate,localTeamId,visitorTeamId,leagueId,0,0,stage,"","","");
				gameService.addGame(game);
				Runnable task = () -> {
					playGame(game);
				};
				TimeComponent tc = new TimeComponent();
				tc.scheduling(task, game.getGameDate());
				
				DateUtils.addDays(gamesDate, 1);
			}
			teamsAtStage/=2;
		}
	}
	
	public void playGame(Game game) {
		List<Characteristics> localChars = new ArrayList<>();
		List<Characteristics> visitorChars = new ArrayList<>();
		Team localTeam = teamService.getTeam(game.getLocalTeamId());
		Team visitorTeam = teamService.getTeam(game.getVisitorTeamId());
		List<Player> localPlayers = playerService.getInitialTeamPlayers(localTeam.getTeamId());
		List<Player> visitorPlayers = playerService.getInitialTeamPlayers(visitorTeam.getTeamId());
		doChars(localChars, localPlayers);
		doChars(visitorChars, visitorPlayers);
		TeamGame localTeamGame = new TeamGame(localTeam.getTeamName(), localChars);
		TeamGame visitorTeamGame = new TeamGame(visitorTeam.getTeamName(), visitorChars);
		Match match = new Match(localTeamGame, visitorTeamGame);

		match.startMatch();

		gameService.updateGame(game.getGameId(), "LOCALTEAMRESULT", Integer.toString(match.getTeamApoints()));
		gameService.updateGame(game.getGameId(), "VISITORTEAMRESULT", Integer.toString(match.getTeamBpoints()));
		gameService.updateGame(game.getGameId(), "ENLOGS", match.getMatchLogs());
		gameService.updateGame(game.getGameId(), "ESLOGS", match.getMatchLogs());
		gameService.updateGame(game.getGameId(), "BQLOGS", match.getMatchLogs());
		gameService.updateGame(game.getGameId(), "PLAYED", Integer.toString(1));

		for (Player each : localPlayers) {
			PlayerGame playerGame = localTeamGame.getPlayerById(each.getPlayerId());
			StatsId statsId = new StatsId(each.getPlayerId(), game.getGameId());
			Stats stats = new Stats(statsId, playerGame.getTwoPointsScored(), playerGame.getTwoPointsShot(),
					playerGame.getThreePointsScored(), playerGame.getThreePointsShot(), playerGame.getOffRebound(),
					playerGame.getDeffRebound(), playerGame.getSteals(), playerGame.getBlocks());
			statsService.addStats(stats);
		}
		
		for (Player each : visitorPlayers) {
			PlayerGame playerGame = visitorTeamGame.getPlayerById(each.getPlayerId());
			StatsId statsId = new StatsId(each.getPlayerId(), game.getGameId());
			Stats stats = new Stats(statsId, playerGame.getTwoPointsScored(), playerGame.getTwoPointsShot(),
					playerGame.getThreePointsScored(), playerGame.getThreePointsShot(), playerGame.getOffRebound(),
					playerGame.getDeffRebound(), playerGame.getSteals(), playerGame.getBlocks());
			statsService.addStats(stats);
		}

		if (game.getLocalTeamResult() > game.getVisitorTeamResult())
			gameService.moveTeamUp(game.getLocalTeamId(), game.getLeagueId());
		else
			gameService.moveTeamUp(game.getVisitorTeamId(), game.getLeagueId());
	}

	private void doChars(List<Characteristics> chars, List<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			chars.add(characteristicsService.getCurrentCharacteristics(players.get(i).getPlayerId()));
		}
	}

	public HashMap<Integer, Integer> getLeagueJoinedTeams(List<League> leagues) {
		HashMap<Integer, Integer> map 
	    = new HashMap<Integer,Integer>();
		Iterator<League> it= leagues.iterator();
		League l;
		int numTeams;
		while(it.hasNext()) {
			l=it.next();
			numTeams=leagueConnectorService.getRegisteredTeams(l.getLeagueId());
			map.put(l.getLeagueId(), numTeams);
		}
		return map;
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
	
	

	@RequestMapping(value = "/newLeague", method = RequestMethod.POST)
	public ModelAndView newLeague(HttpServletRequest request, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute("sessUser");
		if(user!=null) 	modelAndView.setViewName("newLeague");
		else redir.addFlashAttribute("warning", "login.warning");
		return modelAndView;
	}
	
	@RequestMapping(value = "/confirmLeague", method = RequestMethod.POST)
	public ModelAndView confirmLeague(HttpServletRequest request, @RequestParam("leagueName")String leagueName, @RequestParam("leagueDesc")String leagueDesc, @RequestParam("teamAmount")int teamAmount,  RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute("sessUser");
		if(user!=null) {
			Team userTeam= teamService.getTeamByUserId(user.getUserId());
			int stages = (teamAmount==8) ? 3 : 2;
			Date date = new Date();
			League league= new League(0,userTeam.getTeamId(), false, date,date, leagueName, leagueDesc, stages, 0 );
			leagueService.addLeague(league);
			int teamId = teamService.getTeamByUserId(user.getUserId()).getTeamId();
			leagueConnectorService.addLeagueConnector(new LeagueConnector(leagueService.getLastLeagueId(),teamId));
			modelAndView.setViewName("redirect:/league/goToLeagueList.html");
		}else redir.addFlashAttribute("warning", "login.warning");
		return modelAndView;
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
		Team t=new Team(-1,"NoTeam",0,0);
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

}
