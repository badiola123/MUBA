package edu.eskola.muba.characteristics.service;

import java.util.List;

import edu.eskola.muba.characteristics.entity.Characteristics;


/**
 * Service of Characteristics
 * 
 * @author MUBA team
 * @version Final version
 * @see edu.eskola.muba.characteristics.dao.CharacteristicsDao
 */
public interface CharacteristicsService {
	void addCharacteristics(Characteristics characteristics);
	void updateCharacteristic(int charId, String name, int value);
	Characteristics getCurrentCharacteristics(int playerId);
	List<Characteristics> getHistoricCharacteristics(int playerId);
	boolean checkCharacteristics(int characteristicsId);
}
