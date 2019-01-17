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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.apache.commons.lang3.time.DateUtils;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.game.service.GameService;
import edu.eskola.muba.gamemechanics.Match;
import edu.eskola.muba.gamemechanics.PlayerGame;
import edu.eskola.muba.gamemechanics.TeamGame;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.schedule.TimeComponent;
import edu.eskola.muba.stats.entity.Stats;
import edu.eskola.muba.stats.service.StatsService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
import edu.eskola.muba.user.entity.User;

/**
 * This class catches all the requests directed to /league and handles the required actions to manage league related pages.
 *
 * @author MUBA team
 * @version Final version
 * @see The Controller annotation is used to specify that this class is a controller that handles requests
 * @see The RequestMapping annotation is used to map the methods to a path and specify the requet method (GET or POST)
 */

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
	
	private static final String HOME_REDIRECTION = "redirect:/login/home.html";
	private static final String SESS_USER = "sessUser";
	private static final String CATEGORY = "category";
	private static final String WARNING= "warning";
	private static final String LOGIN_WARNING= "login.warning";

	private League league;
	List<League> leagues;
	Team leagueWinner;
	List<Game> gameList;
	
	/**
	 * Catches the request for /league/goToLeagueList with GET method, requested to get a list of leagues of any category
	 * 
	 * @param request Used to check if the user is logged and which category is selected to show leagues.
	 * @param redir Attributes that need to be passed between redirects are added to it
	 * @return A string containing the redirecting address
	 */
	@GetMapping(value = "/goToLeagueList")
	public String goToLeagueList(HttpServletRequest request, RedirectAttributes redir) {
		String direct = HOME_REDIRECTION;
		User user = (User) request.getSession().getAttribute(SESS_USER);
		if (user != null) {
			Team userTeam= teamService.getTeamByUserId(user.getUserId());
			String category = request.getParameter(CATEGORY);
			leagues=leagueService.getActiveLeagues(userTeam.getTeamId());
			HashMap<Integer, Integer> joinedTeamsMap;
			if(category==null) category="running";
			switch (category) {
				case "available":
					List<Integer>leagueIdList;
					leagueIdList=leagueService.getAvailableLeagues(userTeam.getTeamId());
					leagues=getLeaguesFromIdList(leagueIdList);
					joinedTeamsMap=(HashMap<Integer, Integer>) getLeagueJoinedTeams(leagues);
					request.setAttribute("joinedTeamsMap", joinedTeamsMap);
					break;
				case "finished":
					leagues=leagueService.getFinishedLeagues(userTeam.getTeamId());
					break;
				case "notStarted":
					leagues=leagueService.getNotStartedLeagues(userTeam.getTeamId());
					joinedTeamsMap=(HashMap<Integer, Integer>) getLeagueJoinedTeams(leagues);
					request.setAttribute("joinedTeamsMap", joinedTeamsMap);
					break;
				case "running":
				default:
					leagues=leagueService.getActiveLeagues(userTeam.getTeamId());
					break;
			}	
			request.setAttribute(CATEGORY, category);
			request.setAttribute("leagues", leagues);
			direct = "leagueList";
		}
		else {
			redir.addFlashAttribute(WARNING, LOGIN_WARNING);
		}
		return direct;
	}
	
	/**
	 * Used when available to register league list is requested. Gets a list of league objects
	 * from a list of league IDs.
	 * 
	 * @param leagueIdList A list of league IDs to get league objects.
	 * @return A list of league objects.
	 */
	public List<League> getLeaguesFromIdList(List<Integer> leagueIdList) {
		List<League>leagueList=new ArrayList<>();
		Iterator<Integer> it= leagueIdList.iterator();
		while(it.hasNext()) {
			leagueList.add(leagueService.getLeague(it.next()));
		}
		return leagueList;
	}


	/**
	 * Catches the request for /league/leagueActions with GET method, requested when an option 
	 * of any league is selected inside the league list view. Depending on the attribute got in
	 * the request, a different action is performed (join or leave). Also controls league starting 
	 * and game scheduling process when a league gets full.
	 * 
	 * @param request Used to check if the user is logged and getting the action selected by the user.
	 * @param redir Attributes that need to be passed between redirects are added to it
	 * @return An instance of ModelAndView containing the view and needed attributes
	 */
	@GetMapping(value = "/leagueActions")
	public ModelAndView manageLeagueActions(HttpServletRequest request, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(HOME_REDIRECTION);
		User user = (User) request.getSession().getAttribute(SESS_USER);
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
					if(host  && nTeams==1) {
						leagueConnectorService.leaveLeague(leagueId, userTeamId);
						leagueService.deleteLeague(leagueId);
						modelAndView.addObject("info", "league.deleted");
					}else if(host) {
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
			modelAndView.addObject(CATEGORY, redirectionCategory);
		}else {
			redir.addFlashAttribute(WARNING, LOGIN_WARNING);
		}
		
		return modelAndView;
	}
	/**
	 * Used to start a league and all involving games, as well as setting dates and scheduling 
	 * games to run at the specified date.
	 * 
	 * @param leagueId League ID of the league to start.
	 */
	public void startLeague(int leagueId) {
		Date leagueStartDate = new Date(); //League start date
		Date leagueEndDate;
		Date gamesDate=DateUtils.addSeconds(leagueStartDate, 5);
		int stages=leagueService.getLeague(leagueId).getStages();
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
					localTeamId=-1;
					visitorTeamId=-1;
				}
				
				Game game = new Game(0,gamesDate,localTeamId,visitorTeamId,leagueId,0,0,stage,"","","",false);
				gameService.addGame(game);
				final Game lastGame=gameService.getLastGame();
				Runnable task = () -> playGame(lastGame);	
				TimeComponent tc = new TimeComponent();
				tc.scheduling(task, lastGame.getGameDate());
				
				gamesDate=DateUtils.addSeconds(gamesDate, 5);
			}
			teamsAtStage/=2;
		}
	}
	
	/**
	 * Runnable task that plays the match. After the match finishes, updates the corresponding game entry
	 * and passes the winner team to the following stage.
	 * 
	 * @param game Game that must be run
	 */
	public void playGame(Game game) {
		List<Characteristics> localChars = new ArrayList<>();
		List<Characteristics> visitorChars = new ArrayList<>();
		game = gameService.getGame(game.getGameId());
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
		updateGameStatus(match, game);
		game = gameService.getGame(game.getGameId());

		for (Player each : localPlayers) {
			PlayerGame playerGame = localTeamGame.getPlayerById(each.getPlayerId());
			Stats stats = new Stats(each.getPlayerId(), game.getGameId(),playerGame.getTwoPointsScored(), playerGame.getTwoPointsShot(),
					playerGame.getThreePointsScored(), playerGame.getThreePointsShot(), playerGame.getOffRebound(),
					playerGame.getDeffRebound(), playerGame.getSteals(), playerGame.getBlocks());
			statsService.addStats(stats);
		}
		
		for (Player each : visitorPlayers) {
			PlayerGame playerGame = visitorTeamGame.getPlayerById(each.getPlayerId());
			Stats stats = new Stats(each.getPlayerId(), game.getGameId(), playerGame.getTwoPointsScored(), playerGame.getTwoPointsShot(),
					playerGame.getThreePointsScored(), playerGame.getThreePointsShot(), playerGame.getOffRebound(),
					playerGame.getDeffRebound(), playerGame.getSteals(), playerGame.getBlocks());
			statsService.addStats(stats);
		}

		if (game.getLocalTeamResult() > game.getVisitorTeamResult()) {
			teamService.updateBudget(game.getLocalTeamId(), localTeam.getBudget()+10000);
			if(gameService.moveTeamUp(game.getLocalTeamId(), game.getLeagueId())) {
				teamService.updateBudget(game.getLocalTeamId(), localTeam.getBudget()+100000);
				leagueService.updateLeague(game.getLeagueId(), "WINNERTEAM", Integer.toString(game.getLocalTeamId()));
			}
		}
		else {
			teamService.updateBudget(game.getVisitorTeamId(), visitorTeam.getBudget()+10000);
			if(gameService.moveTeamUp(game.getVisitorTeamId(), game.getLeagueId())) {
				teamService.updateBudget(game.getVisitorTeamId(), visitorTeam.getBudget()+100000);
				leagueService.updateLeague(game.getLeagueId(), "WINNERTEAM", Integer.toString(game.getVisitorTeamId()));
			}
		}
	}

	/**
	 * Updates in the database the game entry after the match finishes.
	 * 
	 * @param match The match already played.
	 * @param game The game corresponding to the match updated in database.
	 */
	private void updateGameStatus(Match match, Game game) {
		gameService.updateGame(game.getGameId(), "LOCALTEAMRESULT", Integer.toString(match.getTeamApoints()));
		gameService.updateGame(game.getGameId(), "VISITORTEAMRESULT", Integer.toString(match.getTeamBpoints()));
		gameService.updateGame(game.getGameId(), "ENLOGS", match.getMatchLogs());
		gameService.updateGame(game.getGameId(), "ESLOGS", match.getMatchLogs());
		gameService.updateGame(game.getGameId(), "BQLOGS", match.getMatchLogs());
		gameService.updateGame(game.getGameId(), "PLAYED", Integer.toString(1));
	}

	/**
	 * Gets a list of characteristics regarding to the list of players passed.
	 * 
	 * @param chars List of characteristics passed empty to return filled
	 * @param players List of players of which characteristics are requested
	 */
	private void doChars(List<Characteristics> chars, List<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			chars.add(characteristicsService.getCurrentCharacteristics(players.get(i).getPlayerId()));
		}
	}

	/**
	 * Gets a map of the league IDs with the number of teams already joined to that league.
	 * 
	 * @param leagues List of leagues of which joined team number is required
	 * @return Map with league ID as key and number of teams joined as value.
	 */
	public Map<Integer, Integer> getLeagueJoinedTeams(List<League> leagues) {
		HashMap<Integer, Integer> map 
	    = new HashMap<>();
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

	/**
	 * Catches the request for /league/goToLeague with GET method, requested when the user
	 * selects a specific league to view.
	 * 
	 * @param request Used to check if the user is logged and to get the league ID.
	 * @return A string with the redirection address.
	 */
	@GetMapping(value = "/goToLeague")
	public String goToLeague(HttpServletRequest request) {
		String direct = HOME_REDIRECTION;
		User user = (User) request.getSession().getAttribute(SESS_USER);
		String leagueIdStr = request.getParameter("leagueId");
		if (user != null && leagueIdStr != null) {
			
			int leagueId=Integer.parseInt(leagueIdStr);
			league=leagueService.getLeague(leagueId);
			if(league.getWinnerTeam()!=-1) {
				leagueWinner=teamService.getTeam(league.getWinnerTeam());
			}else {
				leagueWinner=getImaginaryTeam();
			}
			int stages=league.getStages();
			List<ArrayList<Team>> listStageTeamList= new ArrayList<>();
			for(int stageNumber=1; stageNumber<=stages; stageNumber++) {
				listStageTeamList.add((ArrayList<Team>) getStageTeamsOrdered(leagueId, stageNumber));
			}
			gameList=gameService.getLeagueGames(leagueId);
			HashMap<Integer, String> teamMap=(HashMap<Integer, String>) getTeamMap();
			
			request.setAttribute("teamMap", teamMap);
			request.setAttribute("gameList", gameList);
			request.setAttribute("league", league);
			request.setAttribute("leagueWinner", leagueWinner);
			request.setAttribute("listStageTeamList", listStageTeamList);
			direct = "league";
		}
		return direct;
	}
	
	/**
	 * Catches the request for /league/newLeague with POST method, requested when the user selects the 
	 * league creation button.
	 * 
	 * @param request Used to check if the user is logged in.
	 * @param redir Attributes that need to be passed between redirects are added to it
	 * @return An instance of ModelAndView containing the view and needed attributes
	 */
	@PostMapping(value = "/newLeague")
	public ModelAndView newLeague(HttpServletRequest request, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(HOME_REDIRECTION);
		User user = (User) request.getSession().getAttribute(SESS_USER);
		if(user!=null) 	modelAndView.setViewName("newLeague");
		else redir.addFlashAttribute(WARNING, LOGIN_WARNING);
		return modelAndView;
	}
	
	/**
	 * Catches the request for /league/confirmLeague with POST method, requested when the user interacts with any of the buttons of the view of new league creation.
	 * a new league creation.
	 * 
	 * @param request Used to check if the user is logged and to get the action selected 
	 * @param leagueName Name of the league introduced by user
	 * @param leagueDesc Description of the league introduced by user
	 * @param teamAmount Number of teams that the league will have
	 * @param action Action of the button pressed
	 * @param redir Attributes that need to be passed between redirects are added to it
	 * @return An instance of ModelAndView containing the view and needed attributes
	 */
	@PostMapping(value = "/confirmLeague")
	public ModelAndView confirmLeague(HttpServletRequest request, @RequestParam("leagueName")String leagueName, @RequestParam("leagueDesc")String leagueDesc, @RequestParam("teamAmount")int teamAmount,@RequestParam("action")String action,  RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(HOME_REDIRECTION);
		User user = (User) request.getSession().getAttribute(SESS_USER);
		if(user!=null) {
			if(action.equals("confirmLeague")) {
				Team userTeam= teamService.getTeamByUserId(user.getUserId());
				int stages = (teamAmount==8) ? 3 : 2;
				Date date = new Date();
				League newLeague= new League(0,userTeam.getTeamId(), false, date,date, leagueName, leagueDesc, stages, -1 );
				leagueService.addLeague(newLeague);
				int teamId = teamService.getTeamByUserId(user.getUserId()).getTeamId();
				leagueConnectorService.addLeagueConnector(new LeagueConnector(leagueService.getLastLeagueId(),teamId));
			}
			modelAndView.setViewName("redirect:/league/goToLeagueList.html");
			
		}else redir.addFlashAttribute(WARNING, LOGIN_WARNING);
		return modelAndView;
	}
	
	/**
	 * Gets an ordered list of teams involved in all the games of a league's specific stage for the league schedule visualization.
	 * @param leagueId League of which the teams are required
	 * @param stage	Stage of which teams are required
	 * @return
	 */
	public List<Team> getStageTeamsOrdered(int leagueId, int stage){
		List<Team> stageTeams=new ArrayList<>();
		List<Game> leagueGames;
		Game game;
		leagueGames=gameService.getLeagueGamesByStage(leagueId, stage);
		Iterator<Game> itr = leagueGames.iterator();
		while(itr.hasNext()) {
			 game = itr.next();
			 if(game.getLocalTeamId()!=-1) {
				 stageTeams.add(teamService.getTeam(game.getLocalTeamId()));
			 }else {
				 stageTeams.add(getImaginaryTeam());
			 }
			 if(game.getVisitorTeamId()!=-1) {
				 stageTeams.add(teamService.getTeam(game.getVisitorTeamId()));
			 }else {
				 stageTeams.add(getImaginaryTeam());
			 }
		}
		return stageTeams;
		
	}
	
	/**
	 * Creates and return an "imaginary" team, used to represent that the team for a game has not been defined yet.
	 * 
	 * @return Team 
	 */
	public Team getImaginaryTeam() {
		return new Team(-1,"NoTeam",0,0);
	}
	
	/**
	 * Creates and return a map of team IDs and its regarding team object, required in the view for visualization.
	 * 
	 * @return Map of team ID with its team object
	 */
	public Map<Integer, String> getTeamMap(){
		HashMap<Integer, String> teamMap 
	    = new HashMap<>();
		List<LeagueConnector> leagueParticipants=leagueConnectorService.getLeagueTeams(league.getLeagueId());
		Iterator<LeagueConnector> it= leagueParticipants.iterator();
		while(it.hasNext()) {
			LeagueConnector lc=it.next();
			teamMap.put(lc.getTeamId(), teamService.getTeam(lc.getTeamId()).getTeamName());
		}
		return teamMap;
	}

}
