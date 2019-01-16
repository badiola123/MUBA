package edu.eskola.muba.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/playerInfo_charactChart", method = RequestMethod.GET)
	@ResponseBody
	public String playerInfo_characteristicsChartData(int playerId, HttpServletRequest request) {
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
	
	@RequestMapping(value = "/homePage_gameData", method = RequestMethod.GET)
	@ResponseBody
	public String homePage_gameData(HttpServletRequest request) {
		String json = createJsonMUBAData();
		return "{ \"data\" : " + json + "}";
	}

	private String createJsonMUBAData() {
		int teams = teamService.getAllTeams().size();
		int leagues = leagueService.getAllLeagues().size();
		int games = gameService.getAllGames().size();
		int players = playerService.getAllPlayers().size();
		List<Integer> list = new ArrayList<Integer>();
		list.add(leagues);
		list.add(teams);
		list.add(games);
		list.add(players);
		
		return new Gson().toJson(list);
	}
	
	@RequestMapping(value = "/playerInfo_tableData", method = RequestMethod.GET)
	@ResponseBody
	public String playerInfo_tableData(int playerId, HttpServletRequest request) {
		String json = createJsonPlayerStats(playerId);
		return "{ \"data\" : " + json + "}";
	}
	
	private String createJsonPlayerStats(int playerId) {
		List<Stats> stats = statsService.getAllStatsOfPlayer(playerId);
		Player p = playerService.getPlayer(playerId);
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> stats_list = new ArrayList<String>();

		for(int i = 0; i < stats.size(); i++) {
			stats_list = new ArrayList<String>();
			Game g = gameService.getGame(stats.get(i).getGameId());
			int vsTeamId = -1;
			if(g.getVisitorTeamId() != p.getTeamId()) {
				vsTeamId = g.getVisitorTeamId();
			}else {
				vsTeamId = g.getLocalTeamId();
			}
			Team vsT = teamService.getTeam(vsTeamId);
			//Date
			stats_list.add(g.getGameDate().toString());
			//Vs Team
			stats_list.add(vsT.getTeamName());
			//TimePlayed
			stats_list.add("-");
			//FT
			stats_list.add("-");
			//2PT
			double shot = (double) stats.get(i).getTwoPointsShot();
			double scored = (double) stats.get(i).getTwoPointsScored();

			double calcPercentage = ((scored/shot)*100);
			stats_list.add(stats.get(i).getTwoPointsScored()+"/" + stats.get(i).getTwoPointsShot()+ " " + round(calcPercentage, 2) + "%");
			//3PT
			shot = (double) stats.get(i).getThreePointsShot();
			scored = (double) stats.get(i).getThreePointsScored();
			calcPercentage = ((scored/shot)*100);
			stats_list.add(stats.get(i).getThreePointsScored()+"/" + stats.get(i).getThreePointsShot()+ " " + round(calcPercentage, 2) + "%");
			//DR
			stats_list.add(Integer.toString(stats.get(i).getDeffRebound()));
			//OR
			stats_list.add(Integer.toString(stats.get(i).getOffRebound()));
			//S
			stats_list.add(Integer.toString(stats.get(i).getSteals()));
			//B
			stats_list.add(Integer.toString(stats.get(i).getBlocks()));
			//A
			stats_list.add("-");
			//F
			stats_list.add("-");
			//Val
			stats_list.add(Integer.toString(getStatsValoration(stats.get(i))));
			list.add(stats_list);
		}
		list.add(createTotalStatsJson(stats));
		
		return new Gson().toJson(list);
	}
	
	private List<String> createTotalStatsJson(List<Stats> stats) {
		List<String> stats_list = new ArrayList<String>();
		//Date
		stats_list.add("Total");
		//Vs Team
		stats_list.add("");
		//TimePlayed
		stats_list.add("-");
		//FT
		stats_list.add("-");
		//2PT
		int scored2 = 0, shot2 = 0, scored3 = 0, shot3 = 0, dr = 0, or = 0, s = 0, b = 0, val = 0;
		
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
		double calcPercentage = ((double)scored2/(double)shot2)*100;
		stats_list.add(scored2+"/" + shot2+ "\t" + round(calcPercentage, 2) + "%");
		//3PT
		calcPercentage = ((double)scored3/(double)shot3)*100;
		stats_list.add(scored3+"/" + shot3+ "\t" + round(calcPercentage, 2)+ "%");
		//DR
		stats_list.add(Integer.toString(dr));
		//OR
		stats_list.add(Integer.toString(or));
		//S
		stats_list.add(Integer.toString(s));
		//B
		stats_list.add(Integer.toString(b));
		//A
		stats_list.add("-");
		//F
		stats_list.add("-");
		//Val
		stats_list.add(Integer.toString(val));
		return stats_list;
	}

	private int getStatsValoration(Stats stat) {
		int val = 0;
		val = (stat.getTwoPointsScored()*2) - (stat.getTwoPointsShot()) + (stat.getThreePointsScored()*3) - (stat.getThreePointsShot()) + stat.getBlocks() + stat.getDeffRebound() + stat.getOffRebound() + stat.getSteals();
		return val;
	}
	
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
