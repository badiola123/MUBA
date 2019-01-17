package edu.eskola.muba.player.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of Player
 * 
 * @author MUBA team
 * @version Final version
 */

@Entity
@Table(name = "PLAYER")
public class Player {

	@Id
	@Column(name = "PLAYERID", insertable = false, updatable = false)
	private int playerId;
	
	@Column(name = "TEAMID")
	private int teamId;

	@Column(name = "PLAYERNAME")
	private String name;
	
	@Column(name = "PLAYERSURNAME")
	private String surname;
	
	@Column(name = "INITIALFIVE")
	private boolean initialFive;
	
	@Column(name = "PLAYING")
	private boolean playing;
	
	@Column(name = "POSITION")
	private int position;

	/**
	 * Empty constructor of Player
	 */
	public Player() {}
	
	/**
	 * Constructor with the parameters to set to the player
	 * 
	 * @param teamId Team id the player belongs to
	 * @param name Player's name
	 * @param surname Player's surname
	 * @param initialFive Whether the player is in the initial five or not
	 * @param playing Wheter the player is currently playing or not
	 * @param position Position where the player plays
	 */
	public Player(int teamId, String name, String surname, boolean initialFive, boolean playing, int position) {
		super();
		this.teamId = teamId;
		this.name = name;
		this.surname = surname;
		this.initialFive = initialFive;
		this.playing = playing;
		this.position = position;
	}

	/**
	 * 
	 * @return Returns the team id
	 */
	public int getTeamId() {
		return teamId;
	}

	/**
	 * 
	 * @return Returns the player id
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * 
	 * @return Return the player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return Returns the player's surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * 
	 * @return Returns the initial five boolean
	 */
	public boolean isInitialFive() {
		return initialFive;
	}

	/**
	 * 
	 * @return Returns the playing boolean
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * 
	 * @return Returns the player position
	 */
	public int getPosition() {
		return position;
	}
}
