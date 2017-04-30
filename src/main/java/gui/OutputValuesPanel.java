package gui;

import java.util.Observable;

import data.Job;
import data.Params;
import logic.Calculus;

/**
 * This is the Panel that will contains the results that are needed depending on the Raw values and
 * the Job
 * 
 * @author Only Brad
 *
 */
final class OutputValuesPanel extends InputValuesPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6484045256188434574L;
	
	public OutputValuesPanel() {
		
		super(new String[]{"Final Raw HP",
				"Final Raw MP",
				"Final Raw Speed",
				"Final Raw Physical Attack",
				"Final Raw Magic"
				});
		
	}
	
	@Override
	public void generateValues() {
		
		try {
			FrameApp frame = (FrameApp) this.getTopLevelAncestor();
			InputValuesPanel values = frame.getInputValues();
			InputSelectionPanel selection = frame.getInputSelection();
			
			int level = Params.STARTING_LEVEL;
			Job selectedJob = Job.valueOf(((String) selection.getJobs().getSelectedItem()).toUpperCase());
			double[] rawStats = new double[MAGIC+1];
			double[] finalRawStats = new double[MAGIC+1];
			
			for(int i=HP;i<=MAGIC;i++)
				
				rawStats[i] = Integer.parseInt(values.getField(i).getText());
		
			finalRawStats[HP] = Calculus.computeStatTo99(rawStats[HP], level, selectedJob.getHpC());
			finalRawStats[MP] = Calculus.computeStatTo99(rawStats[MP], level, selectedJob.getMpC());
			finalRawStats[SPEED] = Calculus.computeStatTo99(rawStats[SPEED], level, selectedJob.getSpC());
	        finalRawStats[PHYSICAL_DAMAGE] = Calculus.computeStatTo99(rawStats[PHYSICAL_DAMAGE], level, selectedJob.getPaC());
	        finalRawStats[MAGIC] = Calculus.computeStatTo99(rawStats[MAGIC], level, selectedJob.getMaC());
	
	        for(int i=HP;i<=MAGIC;i++)
	        	
	        	this.textFields[i].setText(String.valueOf((int)finalRawStats[i]));
	        
		}
		
		catch(NumberFormatException e) {}
        	
	}

	@Override
	public void update(Observable o, Object arg) {
		
		super.update(o, arg);
		
		if(o instanceof StatValueListener)
			
			this.generateValues();
	}
	
	

}
