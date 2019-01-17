package edu.eskola.muba.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.game.service.GameService;
import edu.eskola.muba.league.service.LeagueService;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.stats.service.StatsService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
import edu.eskola.muba.user.entity.User;

/**
 * This class performs displaying game information on the /match page
 * @author MUBA team
 * @version Final version
 */

@Controller
@RequestMapping("match")
public class MatchController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	TeamService teamService = context.getBean(TeamService.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);
	GameService gameService = context.getBean(GameService.class);
	StatsService statsService = context.getBean(StatsService.class);
	LeagueService leagueService = context.getBean(LeagueService.class);
	
	private static final String homeAddress = "redirect:/login/home.html";
	private static final String matchLogs = "matchLogs";

	/**
	 * This functions handles redirecting to the /match page
	 * @param request is used to get the session and to put attributes 
	 * @param redir is used to put attribute in case of redirecting to /home page
	 * @param locale is used to define in which language the logs should be displayed
	 * @return is a direction either to the /match or /home (if the user is not logged)
	 */
	@GetMapping(path = "/goToMatch")
	public String goToMatch(HttpServletRequest request, RedirectAttributes redir, Locale locale) {
		String direct = homeAddress;
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			Team yourTeam = teamService.getTeamByUserId(user.getUserId());
			Game game = gameService.getLastPlayedGame(yourTeam.getTeamId());
			if(game!=null)
				displayPage(game,request,locale);

			Game nextGame = gameService.nextGame(yourTeam.getTeamId());
			if (nextGame != null)
				request.setAttribute("nextMatch", nextGame.getGameDate().toString());
			else 
				request.setAttribute("nextMatch", "noGame");

			direct = "match";
		} else {
			redir.addFlashAttribute("warning", "login.warning");
		}
		return direct;
	}
	
	/**
	 * This function is used to display game from the league
	 * 
	 * @param gameId is the id of the game we want to display
	 * @param request request is used to get the session and to put attributes 
	 * @param redir is used to put attribute in case of redirecting to /home page
	 * @param locale locale is used to define in which language the logs should be displayed
	 * @return is a direction either to the /match or /home (if the user is not logged)
	 */
	@PostMapping(value ="/showGame")
	public String showGame(@RequestParam("gameId") int gameId, HttpServletRequest request, RedirectAttributes redir, Locale locale) {
		String direct = homeAddress;
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			Game game = gameService.getGame(gameId);
			
			displayPage(game,request,locale);

			direct = "match";
		} else {
			redir.addFlashAttribute("warning", "login.warning");
		}
		return direct;
	}

	/**
	 * This function is responsible for copying attributes to the request
	 * 
	 * @param game data of this game will be displayed on the /match
	 * @param request request is used to get the session and to put attributes 
	 * @param locale locale is used to define in which language the logs should be displayed
	 */
	private void displayPage(Game game, HttpServletRequest request, Locale locale) {
		Team localTeam = teamService.getTeam(game.getLocalTeamId());
		Team visitorTeam = teamService.getTeam(game.getVisitorTeamId());
		List<Player> localPlayers = playerService.getInitialTeamPlayers(localTeam.getTeamId());
		List<Player> visitorPlayers = playerService.getInitialTeamPlayers(visitorTeam.getTeamId());
		request.setAttribute("localTeam", localTeam);
		request.setAttribute("visitorTeam", visitorTeam);
		request.setAttribute("localPlayers", localPlayers);
		request.setAttribute("visitorPlayers", visitorPlayers);
		request.setAttribute("score", game.getLocalTeamResult() + " : " + game.getVisitorTeamResult());
		if (locale.getLanguage().equals("es"))
			request.setAttribute(matchLogs, game.getEsLogs());
		if (locale.getLanguage().equals("en"))
			request.setAttribute(matchLogs, game.getEnLogs());
		if (locale.getLanguage().equals("eu"))
			request.setAttribute(matchLogs, game.getBqLogs());
	}

}
