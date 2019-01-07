package edu.eskola.muba.player.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAYERID")
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

	
	public Player() {}
	
	public Player(int teamId, String name, String surname, boolean initialFive, boolean playing, int position) {
		super();
		this.teamId = teamId;
		this.name = name;
		this.surname = surname;
		this.initialFive = initialFive;
		this.playing = playing;
		this.position = position;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int id) {
		this.playerId = id;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isInitialFive() {
		return initialFive;
	}

	public void setInitialFive(boolean initialFive) {
		this.initialFive = initialFive;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlayign(boolean playing) {
		this.playing = playing;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	
}
