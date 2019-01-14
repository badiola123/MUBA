package edu.eskola.muba.controller;

import java.util.ArrayList;
import edu.eskola.muba.gameMechanics.Match;
import edu.eskola.muba.gameMechanics.TeamGame;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
import edu.eskola.muba.team.service.TeamServiceImpl;
import edu.eskola.muba.user.entity.User;

@Controller
@RequestMapping("match")
public class MatchController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	TeamService teamService = context.getBean(TeamService.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);

	private Team yourTeam;
	private Team enemyTeam;
	List<Player> yourPlayers;
	List<Player> enemyPlayers;

	@RequestMapping(value = "/goToMatch", method = RequestMethod.GET)

	public String goToMatch(HttpServletRequest request, RedirectAttributes redir) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {

			yourTeam = teamService.getTeamByUserId(user.getUserId());
			enemyTeam = teamService.getTeamByUserId(3);
			yourPlayers = playerService.getInitialTeamPlayers(yourTeam.getTeamId());
			enemyPlayers = playerService.getInitialTeamPlayers(enemyTeam.getTeamId());
			request.setAttribute("yourTeam", yourTeam);
			request.setAttribute("enemyTeam", enemyTeam);
			request.setAttribute("yourPlayers", yourPlayers);
			request.setAttribute("enemyPlayers", enemyPlayers);
			request.setAttribute("score", "0 : 0");
			direct = "match";
		}
		else {
			redir.addFlashAttribute("error","redirect.error");
		}
		return direct;
	}

	@RequestMapping(value = "/play", method = RequestMethod.GET)

	public String play(HttpServletRequest request, RedirectAttributes redir) {
		goToMatch(request,redir);
		List<Characteristics> yourChars = new ArrayList<>();
		List<Characteristics> enemyChars = new ArrayList<>();
		doChars(yourChars, yourPlayers);
		doChars(enemyChars, enemyPlayers);
		TeamGame yourTeamGame = new TeamGame(yourTeam.getTeamName(), yourChars);
		TeamGame enemyTeamGame = new TeamGame(enemyTeam.getTeamName(), enemyChars);
		Match match = new Match(yourTeamGame, enemyTeamGame);
		match.startMatch();
		request.setAttribute("score", +match.getTeamApoints() + " : " + match.getTeamBpoints());
		request.setAttribute("matchLogs", match.getMatchLogs());
		return "match";
	}
	
	private void doChars(List<Characteristics> chars, List<Player> players) {
		for(int i=0; i<players.size(); i++) {
			chars.add( characteristicsService.getCurrentCharacteristics(players.get(i).getPlayerId()) );
		}
	}

}
