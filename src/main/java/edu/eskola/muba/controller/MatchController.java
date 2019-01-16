package edu.eskola.muba.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.game.service.GameService;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.stats.service.StatsService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
import edu.eskola.muba.user.entity.User;

@Controller
@RequestMapping("match")
public class MatchController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	TeamService teamService = context.getBean(TeamService.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);
	GameService gameService = context.getBean(GameService.class);
	StatsService statsService = context.getBean(StatsService.class);

	@RequestMapping(value = "/goToMatch", method = RequestMethod.GET)
	public String goToMatch(HttpServletRequest request, RedirectAttributes redir, Locale locale) {
		String direct = "redirect:/login/home.html";
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
	
	@RequestMapping(value ="/showGame", method = RequestMethod.POST)
	public String showGame(@RequestParam("gameId") int gameId, HttpServletRequest request, RedirectAttributes redir, Locale locale) {
		String direct = "redirect:/login/home.html";
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
			request.setAttribute("matchLogs", game.getEsLogs());
		if (locale.getLanguage().equals("en"))
			request.setAttribute("matchLogs", game.getEnLogs());
		if (locale.getLanguage().equals("eu"))
			request.setAttribute("matchLogs", game.getBqLogs());
	}

}
