package edu.eskola.muba.characteristics.dao;

import java.util.List;

import edu.eskola.muba.characteristics.entity.Characteristics;

/**
 * DAO of Characteristics
 * 
 * @author MUBA team
 * @version Final version
 */
public interface CharacteristicsDao {
	/**
	 * Tries to add new characteristics
	 * 
	 * @param characteristics Characteristics to introduce
	 */
	void addCharacteristics(Characteristics characteristics);
	
	/**
	 * Tries to update characteristics
	 * 
	 * @param charId Characteristics to update
	 * @param name Specific characteristic to update
	 * @param value New value to be introduced
	 */
	void updateCharacteristic(int charId, String name, int value);
	
	/**
	 * Tries to get current characteristics of a player
	 * 
	 * @param playerId ID of the player which the characteristics are needed
	 * @return The characteristic for the player
	 */
	Characteristics getCurrentCharacteristics(int playerId);
	
	/**
	 * Tries to get all historical characteristics of a player
	 * 
	 * @param playerId ID of the player which the characteristics are needed
	 * @return The characteristics for the player
	 */
	List<Characteristics> getHistoricCharacteristics(int playerId);
	
	/**
	 * Tries to get the characteristics
	 * 
	 * @param characteristicsId The characteristics to be checked
	 * @return {@code true} if there is no characteristic created with that ID.
     * Else {@code false}.
	 */
	boolean checkCharacteristics(int characteristicsId);
}
