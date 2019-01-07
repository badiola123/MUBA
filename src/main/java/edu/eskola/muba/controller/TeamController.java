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
	public String goToMatch(HttpServletRequest request) {
		String direct = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		if (user != null) {
			direct = "team";
			Team team = teamService.getTeamByUserId(user.getUserId());
			List<Player> players = playerService.getTeamPlayers(team.getTeamId());
			initialPlayers = playerService.getInitialTeamPlayers(team.getTeamId());
			Map<Integer,String> playersNames = new LinkedHashMap<Integer,String>();
			for(Player each: players) {
				playersNames.put(each.getPlayerId(),each.getName() + " " + each.getSurname());
			}
			request.setAttribute("players", playersNames);
			for(Player each : initialPlayers) {
				if(each.getPosition()==1) request.setAttribute("position1", each.getPlayerId());
				if(each.getPosition()==2) request.setAttribute("position2", each.getPlayerId());
				if(each.getPosition()==3) request.setAttribute("position3", each.getPlayerId());
				if(each.getPosition()==4) request.setAttribute("position4", each.getPlayerId());
				if(each.getPosition()==5) request.setAttribute("position5", each.getPlayerId());
			}
		}
		return direct;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("position1")String position1, @RequestParam("position2")String position2, @RequestParam("position3")String position3, @RequestParam("position4")String position4, @RequestParam("position5")String position5) {
		String direct = "redirect:/team/goToTeam.html";
		ArrayList<String> positions = new ArrayList<String>();
		positions.add(position1);
		positions.add(position2);
		positions.add(position3);
		positions.add(position4);
		positions.add(position5);
		if(validatePositions(positions)) {
			for(Player each : initialPlayers) playerService.updateInitialPosition(0, each.getPlayerId());
			for(int i=0; i<positions.size(); i++) {
				String current = positions.get(i);
				int playerId = Integer.parseInt(current.substring(0, current.indexOf('=')));
				playerService.updateInitialPosition(i+1, playerId);
			}
		}
		return direct;
	}
	
	private boolean validatePositions(ArrayList<String> positions) {
		boolean result = true;
		for(int i=0; i < positions.size(); i++) {
			for(int j=i+1; j < positions.size(); j++) {
				if(positions.get(i).equals(positions.get(j))) result = false;
			}
		}
		return result;
	}

}
