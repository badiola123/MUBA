package edu.eskola.muba.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.team.service.TeamService;

@Controller
@RequestMapping("ajax")
public class ChartController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	TeamService teamService = context.getBean(TeamService.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);
	
	@RequestMapping(value = "/playerInfo_charactChart", method = RequestMethod.GET)
	@ResponseBody
	public String playerInfo_characteristicsChartData(int playerId, HttpServletRequest request) {
		String json = createJsonPlayerCharacteristics(playerId);
		return "{ \"data\" : " + json + "}";
	}
	
	@RequestMapping(value = "/playerInfo_characteristicsData", method = RequestMethod.GET)
	@ResponseBody
	public String playerPage_characteristicsChartData(@RequestParam("playerId")int playerId, HttpServletRequest request) {
		String json = createJsonPlayerCharacteristics(playerId);
		return "{ \"data\" : " + json + "}";
	}
	
	private String createJsonPlayerCharacteristics(int playerId) {
		Characteristics c = characteristicsService.getCurrentCharacteristics(playerId);
		List<Integer> list = new ArrayList<Integer>();
		list.add(c.getResistance());
		list.add(c.getBallControl());
		list.add(c.getDefense());
		list.add(c.getLongShoot());
		list.add(c.getShortShoot());
		
		return new Gson().toJson(list);
	}
}
