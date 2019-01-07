package edu.eskola.muba.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.player.service.PlayerService;

@Controller
@RequestMapping("/playerStatistics")
public class PlayerStatisticsController {
	
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfig.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);
	
	@RequestMapping(value = "/playerStatistics", method = RequestMethod.GET)
	public ModelAndView getPlayerCharacteristics() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("playerStatistics");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	@ResponseBody
	public String getChartData() {
		int playerId = 1;
		String json = createJsonPlayerCharacteristics(playerId);
		return "{ \"data\" : " + json + "}";
	}
	
	private String createJsonPlayerCharacteristics(int playerId) {
		Characteristics c = characteristicsService.getCurrentCharacteristics(playerId);
		
		JsonObject json = new JsonObject();
		json.addProperty("names", "[ \"Resistance\", \"Ball Controll\", \"Defense\", \"Long Shoot\", \"Short Shoot\"]");
		/*json.addProperty("data", "[" + Integer.toString(c.getResistance()) + ", " 
		+ Integer.toString(c.getBallControl()) + ", " + Integer.toString(c.getDefense()) + ", "
		+ Integer.toString(c.getLongShoot()) + ", " + Integer.toString(c.getShortShoot()) + "]");*/
		List<Integer> list = new ArrayList<Integer>();
		list.add(c.getResistance());
		list.add(c.getBallControl());
		list.add(c.getDefense());
		list.add(c.getLongShoot());
		list.add(c.getShortShoot());
		
		/*json.addProperty("data", "[" + Integer.toString(c.getResistance()) + ", " 
				+ Integer.toString(c.getBallControl()) + ", " + Integer.toString(c.getDefense()) + ", "
				+ Integer.toString(c.getLongShoot()) + ", " + Integer.toString(c.getShortShoot()) + "]");
		*/
		
		return new Gson().toJson(list);
	}
	/*@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ModelAndView selectPlayer() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("playerStatistics");
		int playerId = 1;

		if(playerService.checkPlayer(playerId)) {
			Player p = playerService.getPlayer(playerId);
			Characteristics c = characteristicsService.getCurrentCharacteristics(playerId);
			modelAndView.addObject("player", p);
			modelAndView.addObject("characteristics", c);
		}
		
		return modelAndView;
	}*/
}
