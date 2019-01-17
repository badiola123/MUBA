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

/**
 * This class handles actions on /team page, /payerInfo page and /playerTrain
 * page
 * 
 * @author MUBA team
 * @version Final version
 */

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

	private final Map<String, Runnable> skillValues = new HashMap<>();

	/**
	 * This function enables receiving required skill by string key
	 */
	private void addOptionsToMap() {
		skillValues.put("RESISTANCE", () -> skillToTrain = trainingCharacteristics.getResistance());
		skillValues.put("BALLCONTROL", () -> skillToTrain = trainingCharacteristics.getBallControl());
		skillValues.put("DEFENSE", () -> skillToTrain = trainingCharacteristics.getDefense());
		skillValues.put("LONGSHOOT", () -> skillToTrain = trainingCharacteristics.getLongShoot());
		skillValues.put("SHORTSHOOT", () -> skillToTrain = trainingCharacteristics.getShortShoot());
	}

	private static final String HOME_ADDRESS = "redirect:/login/home.html";
	private static final String SESS_USER = "sessUser";
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private static final String PLAYER_TRAIN = "playerTrain";

	/**
	 * This function handles directing to /team page
	 * 
	 * @param request is used to get the session and to put attributes
	 * @param redir   is used to put attribute in case of redirecting to /home page
	 * @return is a direction either to the /team or /home (if the user is not
	 *         logged)
	 */
	@GetMapping(path = "/goToTeam")
	public String goToMatch(HttpServletRequest request, RedirectAttributes redir) {
		String direct = HOME_ADDRESS;
		User user = (User) request.getSession().getAttribute(SESS_USER);
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
				int position = each.getPosition() + 1;
				request.setAttribute("position" + position, each.getPlayerId());
			}
		} else {
			redir.addFlashAttribute("warning", "login.warning");
		}
		return direct;
	}

	/**
	 * This function saves the new positions of initial players
	 * 
	 * @param position1 is a name of player on position 1
	 * @param position2 is a name of player on position 2
	 * @param position3 is a name of player on position 3
	 * @param position4 is a name of player on position 4
	 * @param position5 is a name of player on position 5
	 * @param redir     is used to put flash attributes
	 * @return is a String leading to /team page
	 */
	@PostMapping(path = "/save")
	public String save(@RequestParam("position1") String position1, @RequestParam("position2") String position2,
			@RequestParam("position3") String position3, @RequestParam("position4") String position4,
			@RequestParam("position5") String position5, RedirectAttributes redir) {
		String direct = "redirect:/team/goToTeam.html";
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
			redir.addFlashAttribute(SUCCESS, "team.success");
		} else {
			redir.addFlashAttribute(ERROR, "team.error");
		}
		return direct;
	}

	/**
	 * This functions checks if one player is not appearing in more than one place
	 * 
	 * @param positions list of names of player on following positions
	 * @return it returns true if each player has unique position
	 */
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

	/**
	 * This function redirects to the /playerInfo page
	 * 
	 * @param playerId id of the player we want to check
	 * @param request  is used to get the session and to put attributes
	 * @param redir    is used to put flash attributes
	 * @return it returns ModelAndView object directing to /playerInfo page
	 */
	@GetMapping(path = "/player")
	public ModelAndView player(@RequestParam("playerId") int playerId, HttpServletRequest request,
			RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(HOME_ADDRESS);
		User user = (User) request.getSession().getAttribute(SESS_USER);
		if (user != null) {
			modelAndView.setViewName("playerInfo");
			Player player = playerService.getPlayer(playerId);
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			request.setAttribute("player", player);
			request.setAttribute("chars", chars);
		} else {
			redir.addFlashAttribute("warning", "login.warning");

		}
		return modelAndView;
	}

	/**
	 * This function redirects to the /playerTrain page
	 * 
	 * @param playerId id of the player we want to train
	 * @param request  is used to get the session and to put attributes
	 * @return is a direction either to the /playerTrain or /home (if the user is
	 *         not logged)
	 */
	@GetMapping(path = "/train")
	public String train(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		String direct = HOME_ADDRESS;
		User user = (User) request.getSession().getAttribute(SESS_USER);
		if (user != null) {
			direct = PLAYER_TRAIN;
			Team team = teamService.getTeamByUserId(user.getUserId());
			Player player = playerService.getPlayer(playerId);
			Characteristics chars = characteristicsService.getCurrentCharacteristics(playerId);
			request.setAttribute("player", player);
			request.setAttribute("chars", chars);
			request.setAttribute("team", team);
		}
		return direct;
	}

	/**
	 * This function is responsible for improving players' skills
	 * 
	 * @param playerId id of the player we want to train
	 * @param request  is used to get the session and to put attributes
	 * @param skill    is a string key used later to obtain required sill values and
	 *                 update right table in database
	 * @return is a direction either to the /playerTrain or /home (if the user is
	 *         not logged)
	 */
	private String trainSkill(int playerId, HttpServletRequest request, String skill) {
		String direct = HOME_ADDRESS;
		User user = (User) request.getSession().getAttribute(SESS_USER);
		if (user != null) {
			direct = PLAYER_TRAIN;
			trainingCharacteristics = characteristicsService.getCurrentCharacteristics(playerId);
			Team team = teamService.getTeamByUserId(user.getUserId());
			addOptionsToMap();
			skillValues.get(skill).run();
			int currentSkill = skillToTrain;
			int price = currentSkill * 10000;
			int budget = team.getBudget();
			budget -= price;
			if (budget >= 0 && currentSkill < 100) {
				teamService.updateBudget(team.getTeamId(), budget);
				characteristicsService.updateCharacteristic(trainingCharacteristics.getId(), skill, currentSkill + 1);
				request.setAttribute(SUCCESS, "train.success");
			} else {
				request.setAttribute(ERROR, "train.error");
			}
			train(playerId, request);
		}
		return direct;
	}

	/**
	 * This function is responsible for improving players' resistance skill
	 * 
	 * @param playerId id of the player we want to train
	 * @param request  is used to get the session and to put attributes
	 * @return is a direction either to the /playerTrain or /home (if the user is
	 *         not logged)
	 */
	@PostMapping(path = "/trainResistance")
	public String trainResistance(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId, request, "RESISTANCE");
	}

	/**
	 * This function is responsible for improving players' defense skill
	 * 
	 * @param playerId id of the player we want to train
	 * @param request  is used to get the session and to put attributes
	 * @return is a direction either to the /playerTrain or /home (if the user is
	 *         not logged)
	 */
	@PostMapping(path = "/trainDefense")
	public String trainDefense(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId, request, "DEFENSE");
	}

	/**
	 * This function is responsible for improving players' long shoot skill
	 * 
	 * @param playerId id of the player we want to train
	 * @param request  is used to get the session and to put attributes
	 * @return is a direction either to the /playerTrain or /home (if the user is
	 *         not logged)
	 */
	@PostMapping(path = "/trainLongShoot")
	public String trainLongShoot(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId, request, "LONGSHOOT");
	}

	/**
	 * This function is responsible for improving players' short shoot skill
	 * 
	 * @param playerId id of the player we want to train
	 * @param request  is used to get the session and to put attributes
	 * @return is a direction either to the /playerTrain or /home (if the user is
	 *         not logged)
	 */
	@PostMapping(path = "/trainShortShoot")
	public String trainShortShoot(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId, request, "SHORTSHOOT");
	}

	/**
	 * This function is responsible for improving players' ball control skill
	 * 
	 * @param playerId id of the player we want to train
	 * @param request  is used to get the session and to put attributes
	 * @return is a direction either to the /playerTrain or /home (if the user is
	 *         not logged)
	 */
	@PostMapping(path = "/trainBallControl")
	public String trainBallControl(@RequestParam("playerId") int playerId, HttpServletRequest request) {
		return trainSkill(playerId, request, "BALLCONTROL");
	}
}