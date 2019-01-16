package edu.eskola.muba.stats.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.eskola.muba.stats.dao.StatsDao;
import edu.eskola.muba.stats.entity.Stats;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	StatsDao statsDao;

	@Transactional
	@Override
	public void addStats(Stats stats) {
		statsDao.addStats(stats);
	}

	@Transactional
	@Override
	public Stats getStats(int playerId, int gameId) {
		return statsDao.getStats(playerId,gameId);
	}

	@Transactional
	@Override
	public void updateStats(int playerId, int gameId, String key, String value) {
		statsDao.updateStats(playerId, gameId, key, value);
	}

}
