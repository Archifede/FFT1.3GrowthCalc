package logic;

import gui.components.ValuesConstants;

/**
 * 
 * This class stores the state of the Stats of a character
 * 
 * @author Only Brad
 *
 */
public class Stats implements ValuesConstants {
	
	private final double[] stats;
	
	public Stats(double[] rawStats) {
		
		this.stats = new double[rawStats.length];
		
		for(int i=0;i<rawStats.length;i++)
			
			this.stats[i] = rawStats[i];

	}
	
	/**
	 * recalculate the stats based of the current stats, the level, and the job
	 * 
	 * @param currentLevel the current level
	 * @param nextLevel this next level
	 * @param c No idea why this is called "c", ask Archifede
	 */
	public void computeStat(int currentLevel, int nextLevel, int c) {
		
		for(int i=0;i<stats.length;i++)
			
			this.stats[i] = Calculus.computeStat(this.stats[i],currentLevel,nextLevel,c);
	}
	
	/**
	 * returns the value of a specific stat
	 * 
	 * @param stat the stat that you need need (use ValuesConstant constant)
	 */
	public double getStats(int stat) {
		
		return this.stats[stat];
	}
}
