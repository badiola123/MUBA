package edu.eskola.muba.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.gamemechanics.OurRandom;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;
import edu.eskola.muba.transaction.entity.Transaction;
import edu.eskola.muba.transaction.service.TransactionService;
import edu.eskola.muba.user.entity.User;
import edu.eskola.muba.user.service.UserService;

/**
 * This class catches all the requests directed to /login and handles the required actions like the log in, log out and the register processes.
 *
 * @author MUBA team
 * @version Final version
 * @see Controller annotation is used to specify that this class is a controller that handles requests
 * @see RequestMapping annotation is used to map the methods to a path and specify the requet method (GET or POST)
 * @see SessionAttributes annotation is used to specify the attributes with session scope that are used
 */

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
	private static final String SESS_USER_ATTR = "sessUser";
	private static final String WARNING_ALERT = "warning";
	
	/**
	 * Catches the request for /login/home which is requested to load the home page at startup
	 * 
	 * @return A string containing the view that has to be loaded
	 */
	@GetMapping(value = "/home")
	public String homePage() {
		return "home"; 
	}
	
	/**
	 * Catches the request for /login/login which is requested to process a log in request using the POST method
	 * 
	 * @param username Username entered by the user with which the log in is performed
	 * @param password Password entered by the user with which the log in is performed
	 * @return ModelAndView object which holds the model and the view to be displayed
	 * @see RequestParam annotation is used to get the parameters needed from the web request
	 */
	@PostMapping(value = "/login")
	public ModelAndView checkUser(@RequestParam("username")String username, @RequestParam("password")String password) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		User user = userService.checkUser(username, password);
		if(user != null) {
			modelAndView.addObject(SESS_USER_ATTR, user);
			modelAndView.addObject("success", "login.success");
		}
		else {
			modelAndView.addObject("error", "login.error");
		}
		
		return modelAndView;
	}
	
	/**
	 * Catches the request for /login/logout which is requested to process a log out request
	 * 
	 * @param status Used to end the user session
	 * @return ModelAndView instance containing the view to be displayed
	 */
	@GetMapping(value = "/logout")
	public ModelAndView logoutUser(SessionStatus status) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/loggedOut.html");
		status.setComplete();
		return modelAndView;
		}
	
	/**
	 * Catches the request for /login/loggedOut which is requested to complete a log out request
	 * 
	 * @return ModelAndView instance containing the view and an attribute used to show an alert showing the successful log out
	 */
	@GetMapping(value = "/loggedOut")
	public ModelAndView loggedOut() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("success", "logout.success");
		return modelAndView;
	}

	/**
	 * Catches the request for /login/register which is requested to load the register page.
	 * However, if a logged user is not found, the main page is loaded with an alert.
	 * 
	 * @param request Used to check if a user attribute exists which shows that someone is logged in
	 * @param redir Attributes that need to be passed between redirects are added to it
	 * @return An instance of ModelAndView containing the view
	 */
	@PostMapping(value = "/register")
	public ModelAndView register(HttpServletRequest request, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute(SESS_USER_ATTR);
		
		if(user==null) modelAndView.setViewName("register");
		else redir.addFlashAttribute(WARNING_ALERT, "logout.warning");
		
		return modelAndView;
	}
	
	/**
	 * Called when the register form is filled to check the data and proceed with the register.
	 * 
	 * @param regUserInfo Username and the password to be registered
	 * @param regTeamName Team name to be registered
	 * @param regPlayer1 Team's 1st player's name and surname
	 * @param regPlayer2 Team's 2nd player's name and surname
	 * @param regPlayer3 Team's 3rd player's name and surname
	 * @param regPlayer4 Team's 4th player's name and surname
	 * @param regPlayer5 Team's 5th player's name and surname
	 * @param regPlayer6 Team's 6th player's name and surname
	 * @param regPlayer7 Team's 7th player's name and surname
	 * @param regPlayer8 Team's 8th player's name and surname
	 * @param regPlayer9 Team's 9th player's name and surname
	 * @param regPlayer10 Team's 10th player's name and surname
	 * @param request Used to check if there is a logged user
	 * @param redir Attributes that need to be passed between redirects are added to it
	 * @return Instance of ModelAndView containing the view
	 */
	@PostMapping(value = "/validateRegister")
	public ModelAndView validateRegister(@RequestParam(value="regUserInfo[]")String[] regUserInfo, @RequestParam("regTeamName")String regTeamName,
										 @RequestParam(value="regPlayer1[]")String[] regPlayer1, @RequestParam(value="regPlayer2[]")String[] regPlayer2,
										 @RequestParam(value="regPlayer3[]")String[] regPlayer3, @RequestParam(value="regPlayer4[]")String[] regPlayer4,
										 @RequestParam(value="regPlayer5[]")String[] regPlayer5, @RequestParam(value="regPlayer6[]")String[] regPlayer6,
										 @RequestParam(value="regPlayer7[]")String[] regPlayer7, @RequestParam(value="regPlayer8[]")String[] regPlayer8,
										 @RequestParam(value="regPlayer9[]")String[] regPlayer9, @RequestParam(value="regPlayer10[]")String[] regPlayer10,
										 HttpServletRequest request, RedirectAttributes redir) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute(SESS_USER_ATTR);
		
		if(user==null) {
			
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
			
			validData(modelAndView, redir, regUserInfo, regTeamName, playerList);
		}
		else redir.addFlashAttribute(WARNING_ALERT, "logout.warning");
		
		return modelAndView;
	}

	/**
	 * Calls other functions which check the data validity.
	 * Their results are checked and, if the data is valid, it is inserted to the database.
	 * Otherwise, the register page is set to be loaded.
	 * 
	 * @param modelAndView Instance created in the caller function to manage the view to be loaded with its attributes
	 * @param redir Attributes that need to be passed between redirects are added to it
	 * @param regUserInfo Username and password to be registered
	 * @param regTeamName Team name to be registered
	 * @param list List with all the player's names and surname
	 */
	private void validData(ModelAndView modelAndView, RedirectAttributes redir, String[] regUserInfo, String regTeamName, List<String[]> list) {
		int resultCheckUsername = checkUsername(modelAndView, regUserInfo);
		int resultCheckTeam = checkTeam(modelAndView, regTeamName);
		int resultCheckPlayer = checkPlayer(modelAndView, list);
		
		if(resultCheckUsername == -1 && resultCheckTeam == 1 && resultCheckPlayer == 1) {
			createWithData(regUserInfo, regTeamName, list);
			redir.addFlashAttribute("info", "register.success");
		}
		else modelAndView.setViewName("register");
	}

	/**
	 * Manages the calls to the functions that create the different database inputs and the parameters needed to call those functions
	 * 
	 * @param regUserInfo Username and password to be registered
	 * @param regTeamName Team name to be registered
	 * @param list List with all the player's names and surname
	 */
	private void createWithData(String[] regUserInfo, String regTeamName, List<String[]> list) {		
		createUser(regUserInfo);
		int userId = userService.checkUsername(regUserInfo[0]);
		
		createTeam(regTeamName, userId);
		int teamId = teamService.getTeamByUserId(userId).getTeamId();
		
		createPlayers(list, teamId);
	}

	/**
	 * Loops through the player's list and inserts them into the database.
	 * Additionally, it calls the functions to create these players characteristics and the transaction that links them to the team
	 * 
	 * @param list List with all the player's names and surname
	 * @param teamId Team id to which the players are from
	 */
	private void createPlayers(List<String[]> list, int teamId) {
		for(int i = 0; i < list.size(); i++) {
			String[] playerData = list.get(i);
			boolean initialFive = false;
			if(i<5) initialFive = true;
			Player player = new Player(teamId, playerData[0], playerData[1], initialFive, false, i<5? i+2 : 1);
			playerService.addPlayer(player);
			int playerId = playerService.getLastId();
			createCharacteristics(playerId);
			createTransaction(playerId, teamId);
		}
	}

	/**
	 * Creates and adds a new transaction to the database
	 * 
	 * @param playerId Player id to link to the team
	 * @param teamId Team id to which the player is from
	 */
	private void createTransaction(int playerId, int teamId) {
		Date date = new Date();
		Transaction transaction = new Transaction(teamId, teamId, playerId, date, 0);
		transactionService.add(transaction);
	}

	/**
	 * A player's characteristics are created and inserted to the database.
	 * Each characteristic's value is selected randomly in a range
	 * 
	 * @param playerId Player id whose characteristics are created
	 */
	private void createCharacteristics(int playerId) {
		OurRandom ourRandom = new OurRandom();
		int resistance = ourRandom.randomBetween(50, 75);
		int height = ourRandom.randomBetween(50, 75);
		int ballControl = ourRandom.randomBetween(50, 75);
		int defense = ourRandom.randomBetween(50, 75);
		int longShoot = ourRandom.randomBetween(50, 75);
		int shortShoot = ourRandom.randomBetween(50, 75);
		int age = ourRandom.randomBetween(18, 35);
		Date cDate = new Date();
		
		Characteristics characteristics = new Characteristics(resistance, height, ballControl, defense, longShoot, shortShoot, age, cDate, playerId);
		characteristicsService.addCharacteristics(characteristics);
	}

	/**
	 * A new team is created and added to the database
	 * 
	 * @param regTeamName Name of the team to be created
	 * @param userId User id the team belongs to
	 */
	private void createTeam(String regTeamName, int userId) {
		Team team = new Team(regTeamName, 10000000, userId);
		teamService.addTeam(team);
	}

	/**
	 * A new user is created and inserted to the database
	 * 
	 * @param regUserInfo Username and password of the user to be created
	 */
	private void createUser(String[] regUserInfo) {
		User user = new User(regUserInfo[0], regUserInfo[1]);
		userService.add(user);
	}

	/**
	 * Checks if the player's names and surnames fulfill the requirements
	 * 
	 * @param modelAndView Instance of ModelAndView which is used, if needed, to add a warning attribute to display an alert later
	 * @param list List with all the player's names and surname
	 * @return 1 if it is ok and 0 if it is wrong
	 */
	private int checkPlayer(ModelAndView modelAndView, List<String[]> list) {
		int result = 1;
		for(int i = 0; i < list.size(); i++) {
			String[] playerData = list.get(i);
			if((playerData[0].length() >=15) || (playerData[1].length() >=15)) {
				result = 0;
				modelAndView.addObject(WARNING_ALERT, "register.playerLengthError");
			}
		}
		
		return result;
	}
	
	/**
	 * Checks if the user's name is already in use
	 * 
	 * @param modelAndView Instance of ModelAndView which is used, if needed, to add an error attribute to display an alert later
	 * @param regUserInfo Username and password of the user to be created
	 * @return -1 if the username if free and another int if it is taken
	 */
	private int checkUsername(ModelAndView modelAndView, String[] regUserInfo) {
		int result = userService.checkUsername(regUserInfo[0]);
		if(result != -1) modelAndView.addObject("error", "register.usernameError");
		return result;
	}
	
	/**
	 * Checks if the team's name fulfills the requirements
	 * 
	 * @param modelAndView Instance of ModelAndView which is used, if needed, to add an information attribute to display an alert later
	 * @param regTeamName Name of the team to be created
	 * @return 1 if it is ok and 0 if it is wrong
	 */
	private int checkTeam(ModelAndView modelAndView, String regTeamName) {
		int result = 1;
		if(regTeamName.length() >= 10) {
			result = 0;
			modelAndView.addObject("info", "register.teamLengthError");
		}
		return result;
	}
}
