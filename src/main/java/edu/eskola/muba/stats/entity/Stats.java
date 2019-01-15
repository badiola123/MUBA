package edu.eskola.muba.stats.entity;



import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STATS")
public class Stats {

    @EmbeddedId
    private StatsId statsId;
    
    public Stats(StatsId statsId) {
    	this.statsId = statsId;
    }
    
	
	public Stats(StatsId statsId, int twoPointsScored, int twoPointsShot, int threePointsScored, int threePointsShot,
			int offRebound, int deffRebound, int steals, int blocks) {
		this.statsId = statsId;
		this.twoPointsScored = twoPointsScored;
		this.twoPointsShot = twoPointsShot;
		this.threePointsScored = threePointsScored;
		this.threePointsShot = threePointsShot;
		this.offRebound = offRebound;
		this.deffRebound = deffRebound;
		this.steals = steals;
		this.blocks = blocks;
	}


	@Column(name = "TWOPOINTSCORED")
	private int twoPointsScored;
	
	@Column(name = "TWOPOINTSHOT")
	private int twoPointsShot;
	
	@Column(name = "THREEPOINTSCORED")
	private int threePointsScored;
	
	@Column(name = "THREEPOINTSHOT")
	private int threePointsShot;
	
	@Column(name = "OFFREBOUND")
	private int offRebound;
	
	@Column(name = "DEFFREBOUND")
	private int deffRebound;
	
	@Column(name = "STEALS")
	private int steals;
	
	@Column(name = "BLOCKS")
	private int blocks;
 
	public StatsId getStatsId() {
		return statsId;
	}

	public int getTwoPointsScored() {
		return twoPointsScored;
	}

	public int getTwoPointsShot() {
		return twoPointsShot;
	}

	public int getThreePointsScored() {
		return threePointsScored;
	}

	public int getThreePointsShot() {
		return threePointsShot;
	}

	public int getOffRebound() {
		return offRebound;
	}

	public int getDeffRebound() {
		return deffRebound;
	}

	public int getSteals() {
		return steals;
	}

	public int getBlocks() {
		return blocks;
	}

}

