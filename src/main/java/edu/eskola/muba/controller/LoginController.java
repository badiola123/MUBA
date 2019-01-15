package edu.eskola.muba.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.gameMechanics.OurRandom;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
import edu.eskola.muba.transaction.entity.Transaction;
import edu.eskola.muba.transaction.service.TransactionService;
import edu.eskola.muba.user.entity.User;
import edu.eskola.muba.user.service.UserService;

@Controller
@SessionAttributes("sessUser")
@RequestMapping("login")
public class LoginController {
	
	
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfig.class);
	UserService userService = context.getBean(UserService.class);
	TeamService teamService = context.getBean(TeamService.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	TransactionService transactionService = context.getBean(TransactionService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(Model m) {
		return "home"; 
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView checkUser(@RequestParam("username")String username, @RequestParam("password")String password) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		User user = userService.checkUser(username, password);
		if(user != null) {
			modelAndView.addObject("sessUser", user);
			modelAndView.addObject("success", "login.success");
		}
		else {
			modelAndView.addObject("error", "login.error");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView checkUser() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutUser(Model m, SessionStatus status) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/loggedOut.html");
		status.setComplete();
		return modelAndView;
		}
	
	@RequestMapping(value = "/loggedOut", method = RequestMethod.GET)
	public ModelAndView loggedOut(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("success", "logout.success");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute("sessUser");
		
		if(user==null) modelAndView.setViewName("register");
		else redir.addFlashAttribute("warning", "logout.warning");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerChangeLang(HttpServletRequest request, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute("sessUser");
		
		if(user==null) modelAndView.setViewName("register");
		else redir.addFlashAttribute("warning", "logout.warning");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/validateRegister", method = RequestMethod.POST)
	public ModelAndView validateRegister(@RequestParam(value="regUserInfo[]")String[] regUserInfo, @RequestParam("regTeamName")String regTeamName,
										 @RequestParam(value="regPlayer1[]")String[] regPlayer1, @RequestParam(value="regPlayer2[]")String[] regPlayer2,
										 @RequestParam(value="regPlayer3[]")String[] regPlayer3, @RequestParam(value="regPlayer4[]")String[] regPlayer4,
										 @RequestParam(value="regPlayer5[]")String[] regPlayer5, @RequestParam(value="regPlayer6[]")String[] regPlayer6,
										 @RequestParam(value="regPlayer7[]")String[] regPlayer7, @RequestParam(value="regPlayer8[]")String[] regPlayer8,
										 @RequestParam(value="regPlayer9[]")String[] regPlayer9, @RequestParam(value="regPlayer10[]")String[] regPlayer10,
										 HttpServletRequest request, RedirectAttributes redir) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute("sessUser");
		
		if(user==null) {
			validData(modelAndView, redir, regUserInfo, regTeamName, 
					  groupPlayers(regPlayer1, regPlayer2, regPlayer3, regPlayer4, regPlayer5,
							       regPlayer6, regPlayer7, regPlayer8, regPlayer9, regPlayer10));
		}
		else redir.addFlashAttribute("warning", "logout.warning");
		
		return modelAndView;
	}

	private List<String[]> groupPlayers(String[] regPlayer1, String[] regPlayer2, String[] regPlayer3, String[] regPlayer4,
			String[] regPlayer5, String[] regPlayer6, String[] regPlayer7, String[] regPlayer8, String[] regPlayer9,
			String[] regPlayer10) {
		
		List<String[]> playerList = new ArrayList<>();
		playerList.add(regPlayer1);
		playerList.add(regPlayer2);
		playerList.add(regPlayer3);
		playerList.add(regPlayer4);
		playerList.add(regPlayer5);
		playerList.add(regPlayer6);
		playerList.add(regPlayer7);
		playerList.add(regPlayer8);
		playerList.add(regPlayer9);
		playerList.add(regPlayer10);
		
		return playerList;
	}

	private void validData(ModelAndView modelAndView, RedirectAttributes redir, String[] regUserInfo, String regTeamName, List<String[]> list) {
		if((checkUsername(modelAndView, regUserInfo) == -1) & (checkTeam(modelAndView, regTeamName) == 1) & (checkPlayer(modelAndView, list) == 1)) {
			createWithData(regUserInfo, regTeamName, list);
			redir.addFlashAttribute("info", "register.success");
		}
		else modelAndView.setViewName("register");
	}

	private void createWithData(String[] regUserInfo, String regTeamName, List<String[]> list) {		
		createUser(regUserInfo);
		int userId = userService.checkUsername(regUserInfo[0]);
		
		createTeam(regTeamName, userId);
		int teamId = teamService.getTeamByUserId(userId).getTeamId();
		
		createPlayers(list, teamId);
	}

	private void createPlayers(List<String[]> list, int teamId) {
		for(int i = 0; i < list.size(); i++) {
			String[] playerData = list.get(i);
			Player player = new Player(teamId, playerData[0], playerData[1], i<5? true : false, false, i<5? i+2 : 1);
			playerService.addPlayer(player);
			int playerId = playerService.getLastId();
			createCharacteristics(playerId);
			createTransaction(playerId, teamId);
		}
	}

	private void createTransaction(int playerId, int teamId) {
		Date date = new Date();
		Transaction transaction = new Transaction(teamId, teamId, playerId, date, 0);
		transactionService.add(transaction);
	}

	private void createCharacteristics(int playerId) {
		OurRandom ourRandom = new OurRandom();
		int resistance = ourRandom.randomBetween(50, 100);
		int height = ourRandom.randomBetween(50, 100);
		int ballControl = ourRandom.randomBetween(50, 100);
		int defense = ourRandom.randomBetween(50, 100);
		int longShoot = ourRandom.randomBetween(50, 100);
		int shortShoot = ourRandom.randomBetween(50, 100);
		int age = ourRandom.randomBetween(18, 40);
		Date cDate = new Date();
		
		Characteristics characteristics = new Characteristics(resistance, height, ballControl, defense, longShoot, shortShoot, age, cDate, playerId);
		characteristicsService.addCharacteristics(characteristics);
	}

	private void createTeam(String regTeamName, int userId) {
		Team team = new Team(regTeamName, 50000000, userId);
		teamService.addTeam(team);
	}

	private void createUser(String[] regUserInfo) {
		User user = new User(regUserInfo[0], regUserInfo[1]);
		userService.add(user);
	}

	private int checkPlayer(ModelAndView modelAndView, List<String[]> list) {
		int result = 1;
		for(int i = 0; i < list.size(); i++) {
			String[] playerData = list.get(i);
			if((playerData[0].length() >=15) || (playerData[1].length() >=15)) {
				result = 0;
				modelAndView.addObject("warning", "register.playerLengthError");
			}
		}
		
		return result;
	}
	
	private int checkUsername(ModelAndView modelAndView, String[] regUserInfo) {
		int result = userService.checkUsername(regUserInfo[0]);
		if(result != -1) modelAndView.addObject("error", "register.usernameError");
		return result;
	}
	
	private int checkTeam(ModelAndView modelAndView, String regTeamName) {
		int result = 1;
		if(regTeamName.length() >= 10) {
			result = 0;
			modelAndView.addObject("info", "register.teamLengthError");
		}
		return result;
	}
}
