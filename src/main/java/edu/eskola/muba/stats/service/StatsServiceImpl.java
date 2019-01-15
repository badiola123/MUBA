package edu.eskola.muba.stats.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.eskola.muba.stats.dao.StatsDao;
import edu.eskola.muba.stats.entity.Stats;
import edu.eskola.muba.stats.entity.StatsId;

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
	public Stats getStats(StatsId statsId) {
		return statsDao.getStats(statsId);
	}

	@Transactional
	@Override
	public void updateStats(StatsId statsId, String key, String value) {
		statsDao.updateStats(statsId, key, value);
	}

}
