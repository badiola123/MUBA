package edu.eskola.muba.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import edu.eskola.muba.gameMechanics.Match;
import edu.eskola.muba.gameMechanics.TeamGame;
import java.util.List;
import java.util.Map;

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
@RequestMapping("team")
public class TeamController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	TeamService teamService = context.getBean(TeamService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);

	private List<Player> initialPlayers;

	@RequestMapping(value = "/goToTeam", method = RequestMethod.GET)
	public String goToMatch(HttpServletRequest request, RedirectAttributes redir) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			direct = "team";
			Team team = teamService.getTeamByUserId(user.getUserId());
			List<Player> players = playerService.getTeamPlayers(team.getTeamId());
			initialPlayers = playerService.getInitialTeamPlayers(team.getTeamId());
			Map<Integer, String> playersNames = new LinkedHashMap<Integer, String>();
			for (Player each : players) {
				playersNames.put(each.getPlayerId(), each.getName() + " " + each.getSurname());
			}
			request.setAttribute("team", team);
			request.setAttribute("players", playersNames);
			for (Player each : initialPlayers) {
				if (each.getPosition() == 1)
					request.setAttribute("position1", each.getPlayerId());
				if (each.getPosition() == 2)
					request.setAttribute("position2", each.getPlayerId());
				if (each.getPosition() == 3)
					request.setAttribute("position3", each.getPlayerId());
				if (each.getPosition() == 4)
					request.setAttribute("position4", each.getPlayerId());
				if (each.getPosition() == 5)
					request.setAttribute("position5", each.getPlayerId());
			}
		}
		else {
			redir.addFlashAttribute("error","redirect.error");
		}
		return direct;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("position1") String position1, @RequestParam("position2") String position2,
			@RequestParam("position3") String position3, @RequestParam("position4") String position4,
			@RequestParam("position5") String position5, RedirectAttributes redir) {
		String direct = "redirect:/team/goToTeam.html";
		ArrayList<String> positions = new ArrayList<String>();
		positions.add(position1);
		positions.add(position2);
		positions.add(position3);
		positions.add(position4);
		positions.add(position5);
		if (validatePositions(positions)) {
			for (Player each : initialPlayers)
				playerService.updateInitialPosition(0, each.getPlayerId());
			for (int i = 0; i < positions.size(); i++) {
				String current = positions.get(i);
				int playerId = Integer.parseInt(current.substring(0, current.indexOf('=')));
				playerService.updateInitialPosition(i + 1, playerId);
			}
			redir.addFlashAttribute("success", "team.success");
		}
		else {
			redir.addFlashAttribute("error", "team.error");
		}
		return direct;
	}

	private boolean validatePositions(ArrayList<String> positions) {
		boolean result = true;
		for (int i = 0; i < positions.size(); i++) {
			for (int j = i + 1; j < positions.size(); j++) {
				if (positions.get(i).equals(positions.get(j)))
					result = false;
			}
		}
		return result;
	}

	@RequestMapping(value = "/player", method = RequestMethod.GET)
	public String player(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		String direct = "playerInfo";
		Player player = playerService.getPlayer(playerId);
		Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
		request.setAttribute("player", player);
		request.setAttribute("chars", chars);
		return direct;
	}

	@RequestMapping(value = "/train", method = RequestMethod.GET)
	public String train(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			direct = "playerTrain";
			Team team = teamService.getTeamByUserId(user.getUserId());
			Player player = playerService.getPlayer(playerId);
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			request.setAttribute("player", player);
			request.setAttribute("chars", chars);
			request.setAttribute("team", team);
		}
		return direct;
	}
		
	@RequestMapping(value = "/trainResistance", method = RequestMethod.POST)
	public String trainResistance(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			direct = "playerTrain";
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			Team team = teamService.getTeamByUserId(user.getUserId());
			int currentResistance = chars.getResistance();
			int price = currentResistance*10000;
			int budget = team.getBudget();
			budget -= price;
			if(budget >= 0 && currentResistance < 100) {
				teamService.updateBudget(team.getTeamId(), budget);
				characteristicsService.updateCharacteristic(chars.getId(), "RESISTANCE", currentResistance+1);
				request.setAttribute("success", "train.success");
			}
			else {
				request.setAttribute("error", "train.error");
			}
			train(playerId,request);
		}
		return direct;
	}
	
	@RequestMapping(value = "/trainDefense", method = RequestMethod.POST)
	public String trainDefense(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			direct = "playerTrain";
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			Team team = teamService.getTeamByUserId(user.getUserId());
			int currentSkill = chars.getDefense();
			int price = currentSkill*10000;
			int budget = team.getBudget();
			budget -= price;
			if(budget >= 0 && currentSkill < 100) {
				teamService.updateBudget(team.getTeamId(), budget);
				characteristicsService.updateCharacteristic(chars.getId(), "DEFENSE", currentSkill+1);
				request.setAttribute("success", "train.success");
			}
			else {
				request.setAttribute("error", "train.error");
			}
			train(playerId,request);
		}
		return direct;
	}
	
	@RequestMapping(value = "/trainLongShoot", method = RequestMethod.POST)
	public String trainLongShoot(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			direct = "playerTrain";
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			Team team = teamService.getTeamByUserId(user.getUserId());
			int currentSkill = chars.getLongShoot();
			int price = currentSkill*10000;
			int budget = team.getBudget();
			budget -= price;
			if(budget >= 0 && currentSkill < 100) {
				teamService.updateBudget(team.getTeamId(), budget);
				characteristicsService.updateCharacteristic(chars.getId(), "LONGSHOOT", currentSkill+1);
				request.setAttribute("success", "train.success");
			}
			else {
				request.setAttribute("error", "train.error");
			}
			train(playerId,request);
		}
		return direct;
	}
	
	@RequestMapping(value = "/trainShortShoot", method = RequestMethod.POST)
	public String trainShortShoot(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			direct = "playerTrain";
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			Team team = teamService.getTeamByUserId(user.getUserId());
			int currentSkill = chars.getShortShoot();
			int price = currentSkill*10000;
			int budget = team.getBudget();
			budget -= price;
			if(budget >= 0 && currentSkill < 100) {
				teamService.updateBudget(team.getTeamId(), budget);
				characteristicsService.updateCharacteristic(chars.getId(), "SHORTSHOOT", currentSkill+1);
				request.setAttribute("success", "train.success");
			}
			else {
				request.setAttribute("error", "train.error");
			}
			train(playerId,request);
		}
		return direct;
	}
	
	@RequestMapping(value = "/trainBallControl", method = RequestMethod.POST)
	public String trainBallControl(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			direct = "playerTrain";
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			Team team = teamService.getTeamByUserId(user.getUserId());
			int currentSkill = chars.getBallControl();
			int price = currentSkill*10000;
			int budget = team.getBudget();
			budget -= price;
			if(budget >= 0 && currentSkill < 100) {
				teamService.updateBudget(team.getTeamId(), budget);
				characteristicsService.updateCharacteristic(chars.getId(), "BALLCONTROL", currentSkill+1);
				request.setAttribute("success", "train.success");
			}
			else {
				request.setAttribute("error", "train.error");
			}
			train(playerId,request);
		}
		return direct;
	}

}