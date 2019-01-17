package edu.eskola.muba.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
import edu.eskola.muba.user.entity.User;

@Controller
@RequestMapping("team")
public class TeamController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	TeamService teamService = context.getBean(TeamService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);
	private List<Player> initialPlayers;
	private Characteristics trainingCharacteristics;
	private int skillToTrain;
	
	public static Map<String, Runnable> skillValues = new HashMap<String, Runnable>();
	private void addOptionsToMap() {
		skillValues.put("RESISTANCE", () -> skillToTrain= trainingCharacteristics.getResistance());
		skillValues.put("BALLCONTROL", () -> skillToTrain= trainingCharacteristics.getBallControl());
		skillValues.put("DEFENSE", () -> skillToTrain= trainingCharacteristics.getDefense());
		skillValues.put("LONGSHOOT", () -> skillToTrain= trainingCharacteristics.getLongShoot());
		skillValues.put("SHORTSHOOT", () -> skillToTrain= trainingCharacteristics.getShortShoot());
	}
	
	private static final String homeAddress = "redirect:/login/home.html";
	private static final String sessUser = "sessUser";
	private static final String success = "success";
	private static final String error = "error";
	private static final String playerTrain = "playerTrain";



	@GetMapping(path = "/goToTeam")
	public String goToMatch(HttpServletRequest request, RedirectAttributes redir) {
		String direct = homeAddress;
		User user = (User) request.getSession().getAttribute(sessUser);
		if (user != null) {
			direct = "team";
			Team team = teamService.getTeamByUserId(user.getUserId());
			List<Player> players = playerService.getTeamPlayers(team.getTeamId());
			initialPlayers = playerService.getInitialTeamPlayers(team.getTeamId());
			Map<Integer, String> playersNames = new LinkedHashMap<>();
			for (Player each : players) {
				playersNames.put(each.getPlayerId(), each.getName() + " " + each.getSurname());
			}
			request.setAttribute("team", team);
			request.setAttribute("players", playersNames);
			for (Player each : initialPlayers) {
				int position = each.getPosition()+1;
				request.setAttribute("position"+position, each.getPlayerId());
			}
		} else {
			redir.addFlashAttribute("warning", "login.warning");
		}
		return direct;
	}

	@PostMapping(path = "/save")
	public String save(@RequestParam("position1") String position1, @RequestParam("position2") String position2,
			@RequestParam("position3") String position3, @RequestParam("position4") String position4,
			@RequestParam("position5") String position5, RedirectAttributes redir) {
		String direct = homeAddress;
		ArrayList<String> positions = new ArrayList<>();
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
			redir.addFlashAttribute(success, "team.success");
		} else {
			redir.addFlashAttribute(error, "team.error");
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

	@GetMapping(path = "/player")
	public ModelAndView player(@RequestParam("playerId") int playerId, HttpServletRequest request,
			RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(homeAddress);
		User user = (User) request.getSession().getAttribute(sessUser);
		if (user != null) {
			modelAndView.setViewName("playerInfo");
			Player player = playerService.getPlayer(playerId);
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			request.setAttribute("player", player);
			request.setAttribute("chars", chars);
		} else
			redir.addFlashAttribute("warning", "login.warning");
		return modelAndView;
	}

	@GetMapping(path = "/train")
	public String train(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		String direct = homeAddress;
		User user = (User) request.getSession().getAttribute(sessUser);
		if (user != null) {
			direct = playerTrain;
			Team team = teamService.getTeamByUserId(user.getUserId());
			Player player = playerService.getPlayer(playerId);
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			request.setAttribute("player", player);
			request.setAttribute("chars", chars);
			request.setAttribute("team", team);
		}
		return direct;
	}

	private String trainSkill(int playerId, HttpServletRequest request, String skill) {
		String direct = homeAddress;
		User user = (User) request.getSession().getAttribute(sessUser);
		if (user != null) {
			direct = playerTrain;
			trainingCharacteristics = characteristicsService.getCurrentCharacteristics(playerId);
			Team team = teamService.getTeamByUserId(user.getUserId());
			addOptionsToMap();
			skillValues.get(skill).run();
			int currentSkill = skillToTrain;
			System.out.println("curr skill"+currentSkill);
			int price = currentSkill * 10000;
			int budget = team.getBudget();
			budget -= price;
			if (budget >= 0 && currentSkill < 100) {
				teamService.updateBudget(team.getTeamId(), budget);
				characteristicsService.updateCharacteristic(trainingCharacteristics.getId(), skill, currentSkill + 1);
				request.setAttribute(success, "train.success");
			} else {
				request.setAttribute(error, "train.error");
			}
			train(playerId, request);
		}
		return direct;
	}

	@PostMapping(path = "/trainResistance")
	public String trainResistance(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId,request,"RESISTANCE");
	}

	@PostMapping(path = "/trainDefense")
	public String trainDefense(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId,request,"DEFENSE");
	}

	@PostMapping(path = "/trainLongShoot")
	public String trainLongShoot(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId,request,"LONGSHOOT");
	}

	@PostMapping(path = "/trainShortShoot")
	public String trainShortShoot(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId,request,"SHORTSHOOT");
	}

	@PostMapping(path = "/trainBallControl")
	public String trainBallControl(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId,request,"BALLCONTROL");
	}
}