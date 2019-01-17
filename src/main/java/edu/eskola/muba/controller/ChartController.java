package edu.eskola.muba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.characteristics.service.CharacteristicsService;
import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.game.service.GameService;
import edu.eskola.muba.league.service.LeagueService;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.player.service.PlayerService;
import edu.eskola.muba.stats.entity.Stats;
import edu.eskola.muba.stats.service.StatsService;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.team.service.TeamService;

/**
 * This class catches all the requests directed to /ajax and handles the required actions JSON creation when AJAX is used to update data.
 *
 * @author MUBA team
 * @version Final version
 * The Controller annotation is used to specify that this class is a controller that handles requests
 */

@Controller
@RequestMapping("ajax")
public class ChartController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	TeamService teamService = context.getBean(TeamService.class);
	LeagueService leagueService = context.getBean(LeagueService.class);
	PlayerService playerService = context.getBean(PlayerService.class);
	GameService gameService = context.getBean(GameService.class);
	CharacteristicsService characteristicsService = context.getBean(CharacteristicsService.class);
	StatsService statsService = context.getBean(StatsService.class);
	String jsonStartString = "{ \"data\" : " ;
	
	/**
	 * Catches the request for /ajax/playerInfo_charactChart which is requested in the player information display page for the characteristics chart.
	 * 
	 * @param playerId The ID of the player which the characteristic data has to be taken for the chart.
	 * @return An string in JSON format is returned with the characteristics data.
	 */
	@GetMapping(value = "/playerInfo_charactChart")
	@ResponseBody
	public String playerInfoCharacteristicsChartData(int playerId) {
		String json = createJsonPlayerCharacteristics(playerId);
		return jsonStartString + json + "}";
	}
	
	/**
	 * Generates the JSON file String with the player characteristics data.
	 * 
	 * @param playerId The ID of the player for which the characteristics have to be taken.
	 * @return An string with the data list to introduce in the JSON
	 */
	private String createJsonPlayerCharacteristics(int playerId) {
		Characteristics c = characteristicsService.getCurrentCharacteristics(playerId);
		List<Integer> list = new ArrayList<>();
		list.add(c.getResistance());
		list.add(c.getBallControl());
		list.add(c.getDefense());
		list.add(c.getLongShoot());
		list.add(c.getShortShoot());
		
		return new Gson().toJson(list);
	}
	
	/**
	 * Catches the request for /ajax/homePage_gameData/ which is requested in the Home Page to get game data
	 * 
	 * @return An string in JSON format is returned with the online game data.
	 */
	@GetMapping(value = "/homePage_gameData")
	@ResponseBody
	public String homePageGameData() {
		String json = createJsonMUBAData();
		return jsonStartString + json + "}";
	}
	
	/**
	 * Generates the JSON file String with the online game data.
	 * 
	 * @return An string with the data list to introduce in the JSON
	 */
	private String createJsonMUBAData() {
		int teams = teamService.getAllTeams().size();
		int leagues = leagueService.getAllLeagues().size();
		int games = gameService.getAllGames().size();
		int players = playerService.getAllPlayers().size();
		List<Integer> list = new ArrayList<>();
		list.add(leagues);
		list.add(teams);
		list.add(games);
		list.add(players);
		
		return new Gson().toJson(list);
	}
	
	/**
	 * Catches the request for /ajax/playerInfo_tableData which is requested in the player information display page for the statistics table.
	 * 
	 * @param playerId The ID of the player which the statistics data has to be taken for the table.
	 * @return An string in JSON format is returned with the statistics data.
	 */
	@GetMapping(value = "/playerInfo_tableData")
	@ResponseBody
	public String playerInfoTableData(int playerId) {
		String json = createJsonPlayerStats(playerId);
		return jsonStartString + json + "}";
	}
	
	/**
	 * Generates the JSON file String with the player statistics data.
	 * 
	 * @param playerId The ID of the player for which the statistics have to be taken.
	 * @return An string with the data list to introduce in the JSON
	 */
	private String createJsonPlayerStats(int playerId) {
		List<Stats> stats = statsService.getAllStatsOfPlayer(playerId);
		Player p = playerService.getPlayer(playerId);
		List<List<String>> list = new ArrayList<>();

		for(int i = 0; i < stats.size(); i++) {
			List<String> statsList = new ArrayList<>();
			Game g = gameService.getGame(stats.get(i).getGameId());
			int vsTeamId = -1;
			if(g.getVisitorTeamId() != p.getTeamId()) {
				vsTeamId = g.getVisitorTeamId();
			}else {
				vsTeamId = g.getLocalTeamId();
			}
			Team vsT = teamService.getTeam(vsTeamId);
			//Date
			statsList.add(g.getGameDate().toString());
			//Vs Team
			statsList.add(vsT.getTeamName());
			//TimePlayed
			statsList.add("-");
			//FT
			statsList.add("-");
			//2PT
			double shot = (double) stats.get(i).getTwoPointsShot();
			double scored = (double) stats.get(i).getTwoPointsScored();
			double calcPercentage = 0;
			if(shot > 0) calcPercentage = ((scored/shot)*100);
			statsList.add(stats.get(i).getTwoPointsScored()+"/" + stats.get(i).getTwoPointsShot()+ " " + round(calcPercentage, 2) + "%");
			//3PT
			shot = (double) stats.get(i).getThreePointsShot();
			scored = (double) stats.get(i).getThreePointsScored();
			calcPercentage = 0;
			if(shot > 0) calcPercentage = ((scored/shot)*100);
			statsList.add(stats.get(i).getThreePointsScored()+"/" + stats.get(i).getThreePointsShot()+ " " + round(calcPercentage, 2) + "%");
			//DR
			statsList.add(Integer.toString(stats.get(i).getDeffRebound()));
			//OR
			statsList.add(Integer.toString(stats.get(i).getOffRebound()));
			//S
			statsList.add(Integer.toString(stats.get(i).getSteals()));
			//B
			statsList.add(Integer.toString(stats.get(i).getBlocks()));
			//A
			statsList.add("-");
			//F
			statsList.add("-");
			//Val
			statsList.add(Integer.toString(getStatsValoration(stats.get(i))));
			list.add(statsList);
		}
		list.add(createTotalStatsJson(stats));
		
		return new Gson().toJson(list);
	}
	
	/**
	 * Generates a list with the total statistics of all the games played.
	 * 
	 * @param stats All the statistics of a player.
	 * @return A list with the total statistics data.
	 */
	private List<String> createTotalStatsJson(List<Stats> stats) {
		List<String> statsList = new ArrayList<>();
		int scored2 = 0;
		int shot2 = 0;
		int scored3 = 0;
		int shot3 = 0;
		int dr = 0;
		int or = 0;
		int s = 0;
		int b = 0;
		int val = 0;

		//Date
		statsList.add("Total");
		//Vs Team
		statsList.add("");
		//TimePlayed
		statsList.add("-");
		//FT
		statsList.add("-");
		//2PT
		
		for(int i = 0; i< stats.size(); i++) {
			scored2 += stats.get(i).getTwoPointsScored();
			shot2 += stats.get(i).getTwoPointsShot();
			scored3 += stats.get(i).getThreePointsScored();
			shot3 += stats.get(i).getThreePointsShot();
			dr += stats.get(i).getDeffRebound();
			or += stats.get(i).getOffRebound();
			s += stats.get(i).getSteals();
			b += stats.get(i).getBlocks();
			val += getStatsValoration(stats.get(i));
		}
		double calcPercentage = 0;
		if(shot2 > 0) calcPercentage = ((double)scored2/(double)shot2)*100;
		statsList.add(scored2+"/" + shot2+ "\t" + round(calcPercentage, 2) + "%");
		//3PT
		calcPercentage = 0;
		if(shot3 > 0) calcPercentage = ((double)scored3/(double)shot3)*100;
		statsList.add(scored3+"/" + shot3+ "\t" + round(calcPercentage, 2)+ "%");
		//DR
		statsList.add(Integer.toString(dr));
		//OR
		statsList.add(Integer.toString(or));
		//S
		statsList.add(Integer.toString(s));
		//B
		statsList.add(Integer.toString(b));
		//A
		statsList.add("-");
		//F
		statsList.add("-");
		//Val
		statsList.add(Integer.toString(val));
		return statsList;
	}
	
	/**
	 * Calculates the total valoration value of a player.
	 * 
	 * @param stat The statistic from which the valoration has to be calculated
	 * @return the valoration value.
	 */
	private int getStatsValoration(Stats stat) {
		int val = 0;
		val = (stat.getTwoPointsScored()*2) - (stat.getTwoPointsShot()) + (stat.getThreePointsScored()*3) - (stat.getThreePointsShot()) + stat.getBlocks() + stat.getDeffRebound() + stat.getOffRebound() + stat.getSteals();
		return val;
	}
	
	/**
	 * Rounds a double value to a limited decimal
	 * 
	 * @param value The double value that has to be rounded.
	 * @param places The number of decimals that want to be left.
	 * @return The double value with only the wanted decimals.
	 */
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
