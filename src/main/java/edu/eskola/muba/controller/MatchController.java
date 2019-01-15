package edu.eskola.muba.controller;

import java.util.ArrayList;
import edu.eskola.muba.gameMechanics.Match;
import edu.eskola.muba.gameMechanics.PlayerGame;
import edu.eskola.muba.gameMechanics.TeamGame;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.TaskScheduler;
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
import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.game.service.GameService;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.stats.entity.Stats;
import edu.eskola.muba.stats.entity.StatsId;
import edu.eskola.muba.stats.service.StatsService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
import edu.eskola.muba.team.service.TeamServiceImpl;
import edu.eskola.muba.user.entity.User;
import schedule.TimeComponent;

@Controller
@RequestMapping("match")
public class MatchController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	TeamService teamService = context.getBean(TeamService.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);
	GameService gameService = context.getBean(GameService.class);
	StatsService statsService = context.getBean(StatsService.class);

	private Team localTeam;
	private Team visitorTeam;
	List<Player> localPlayers;
	List<Player> visitorPlayers;

	@RequestMapping(value = "/goToMatch", method = RequestMethod.GET)
	public String goToMatch(HttpServletRequest request, RedirectAttributes redir, Locale locale) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			Team yourTeam = teamService.getTeamByUserId(user.getUserId());
			Game game = gameService.getLastPlayedGame(yourTeam.getTeamId());
			localTeam = teamService.getTeam(game.getLocalTeamId());
			visitorTeam = teamService.getTeam(game.getVisitorTeamId());

			localPlayers = playerService.getInitialTeamPlayers(localTeam.getTeamId());
			visitorPlayers = playerService.getInitialTeamPlayers(visitorTeam.getTeamId());
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

			Game nextGame = gameService.nextGame(yourTeam.getTeamId());
			if (nextGame != null)
				request.setAttribute("nextMatch", nextGame.getGameDate().toString());

			direct = "match";
		} else {
			redir.addFlashAttribute("error", "redirect.error");
		}
		return direct;
	}

	@RequestMapping(value = "/play", method = RequestMethod.GET)
	public String play(HttpServletRequest request, RedirectAttributes redir, Locale locale) {
		goToMatch(request, redir, locale);

		Game game = gameService.getGame(8);
		Runnable task = () -> {
			System.out.println("ODPALAMYYYYYYYYYY");
			playGame(game);
		};
		TimeComponent tc = new TimeComponent();
		tc.scheduling(task, game.getGameDate());
		return "match";
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

}
