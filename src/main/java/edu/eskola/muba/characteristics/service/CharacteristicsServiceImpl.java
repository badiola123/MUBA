package edu.eskola.muba.characteristics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.eskola.muba.characteristics.dao.CharacteristicsDao;
import edu.eskola.muba.characteristics.entity.Characteristics;

@Service
public class CharacteristicsServiceImpl implements CharacteristicsService{

	@Autowired
	CharacteristicsDao characteristicsDao;
	
	@Transactional
	@Override
	public void addCharacteristics(Characteristics characteristics) {
		characteristicsDao.addCharacteristics(characteristics);
	}

	@Transactional
	@Override
	public Characteristics getCurrentCharacteristics(int playerId) {
		return characteristicsDao.getCurrentCharacteristics(playerId);
	}

	@Transactional
	@Override
	public List<Characteristics> getHistoricCharacteristics(int playerId) {
		return characteristicsDao.getHistoricCharacteristics(playerId);
	}

	@Transactional
	@Override
	public boolean checkCharacteristics(int characteristicsId) {
		return characteristicsDao.checkCharacteristics(characteristicsId);
	}
	
	
}
