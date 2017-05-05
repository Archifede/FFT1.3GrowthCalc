package logic;

import data.Job;
import gui.components.ValuesConstants;

/**
 * 
 * This class stores the state of the Stats of a character
 * 
 * @author Only Brad
 *
 */
public class Stats implements ValuesConstants {
	
	private double[] stats;
	
	public Stats(double[] rawStats) {
		
		this.stats = new double[rawStats.length];
		
		for(int i=0;i<rawStats.length;i++)
			
			this.stats[i] = rawStats[i];

	}
	
	/**
	 * Clone constructor
	 * 
	 * @param stats
	 */
	public Stats(Stats stats) {
		
		this.setStats(stats);
	}

	/**
	 * recalculate the stats based of the current stats, the level, and the job
	 * 
	 * @param currentLevel the current level
	 * @param nextLevel this next level
	 * @param Job the current job
	 */
	public void computeStat(int currentLevel, int nextLevel, Job job) {
		
		int[] c = new int[5]; //c No idea why this is called "c", ask Archifede
		c[0] = job.getHpC();
		c[1] = job.getMpC();
		c[2] = job.getSpC();
		c[3] = job.getPaC();
		c[4] = job.getMaC();
		
		for(int i=0;i<stats.length;i++)
			
			this.stats[i] = Calculus.computeStat(this.stats[i],currentLevel,nextLevel,c[i]);
	}
	
	/**
	 * returns the value of a specific stat
	 * 
	 * @param stat the stat that you need need (use ValuesConstant constant)
	 */
	public double getStats(int stat) {
		
		return this.stats[stat];
	}
	
	/**
	 * 
	 * @return a pointer to the internal array
	 */
	public double[] getStats() {
		
		return this.stats;
	}
	
	/**
	 * Clone another stat object
	 * 
	 * @param other the other stat object you are cloning
	 */
	public void setStats(Stats other) {
		
		double[] stats = new double[other.stats.length];
		
		for(int i=0;i<stats.length;i++)
			
			stats[i] = other.stats[i];
		
		this.stats = stats;
	}
	
	/**
	 * Modify the internal stats values
	 * 
	 * @param stats the values that are going to replace the older ones
	 */
	public void setStats(double[] stats) {
		
		this.stats = stats;
	}
}
