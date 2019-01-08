package edu.eskola.muba.characteristics.dao;

import java.util.List;

import edu.eskola.muba.characteristics.entity.Characteristics;

public interface CharacteristicsDao {
	void addCharacteristics(Characteristics characteristics);
	Characteristics getCurrentCharacteristics(int playerId);
	List<Characteristics> getHistoricCharacteristics(int playerId);
	boolean checkCharacteristics(int characteristicsId);
}
