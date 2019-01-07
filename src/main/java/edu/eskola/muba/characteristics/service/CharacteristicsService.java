package edu.eskola.muba.characteristics.service;

import java.util.List;

import edu.eskola.muba.characteristics.entity.Characteristics;

public interface CharacteristicsService {
	void addCharacteristics(Characteristics characteristics);
	Characteristics getCurrentCharacteristics(int playerId);
	List<Characteristics> getHistoricCharacteristics(int playerId);
	boolean checkCharacteristics(int characteristicsId);
}
