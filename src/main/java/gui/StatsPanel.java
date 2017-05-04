package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

import data.Gender;
import data.Job;
import gui.components.ComboBoxInput.Genders;
import gui.components.ComboBoxInput.Jobs;
import gui.components.LevelTextField;
import gui.components.OnlyNumbers;
import gui.components.ValuesConstants;
import gui.listeners.ValueListener;
import logic.Stats;

/**
 * This class represents a Stats Panel. This is where the value of the stats are going to be
 * shown and some of them can be modified depending on the situation.
 * 
 * @author Only Brad
 *
 */
abstract class StatsPanel extends JPanel implements ValuesConstants,Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4795777516067075188L;
	protected JTextField[] textFields;
	protected String[] labels;
	protected Stats stats;
	
	
	StatsPanel(String ... labels) {
		
		this.stats = new Stats(new double[]{0,0,0,0,0});
		
		GuiConfig config = GuiConfig.getInstance();
		
		this.labels = labels;
		this.setBackground(config.PANEL_COLOR);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new GridLayout(labels.length,2));
		this.createTextFields();
		this.addTextFieldsAndLabels();

	}
	
	StatsPanel() {
		
		this(GuiConfig.getInstance().STATS);
	}
	
	/**
	 * change the stats on the GUI using the values in the Stats object
	 */
	void changeStats() {
		
		for(int i=0;i<this.textFields.length;i++) {
			
			int stat = (int) this.stats.getStats(i);
			String textStat = String.valueOf(stat);
			this.textFields[i].setText(textStat);
		
		}
	}
	
	/**
	 * Generate a ValueListener inside all the text field then return the generated ValueListener
	 * 
	 * @return the ValueListener that was added in all the textfields
	 */
	ValueListener addValueListener() {
		
		ValueListener listener = new ValueListener(STAT);
		
		for(int i=0;i<this.textFields.length;i++)
			
			this.textFields[i].getDocument().addDocumentListener(listener);
		
		return listener;
	}

	/**
	 * Generate the JTextFields and store them inside an array
	 */
	protected void createTextFields() {
		
		this.textFields = new JTextField[5];
		
		for(int i=0;i<this.textFields.length;i++) {
			
			textFields[i] = new JTextField();
			textFields[i].setBackground(Color.WHITE);
			PlainDocument document = (PlainDocument)textFields[i].getDocument();
			document.setDocumentFilter(new OnlyNumbers(0,Integer.MAX_VALUE));
		}
		
		this.changeStats();
	}
	
	/**
	 * Change the JTextFields by using the values stored in the Stats object
	 */
	protected void changeTextFields() {
		
		for(int i=0;i<this.textFields.length;i++)
			
			this.textFields[i].setText(String.valueOf(this.stats.getStats(i)));
	}
	
	/**
	 * Add the JTextFields and the JLabel into the JPanel
	 */
	protected void addTextFieldsAndLabels() {
		
		for(int i=0;i<labels.length;i++) {
			
			JLabel label = new JLabel(this.labels[i]);
			label.setFont(GuiConfig.getInstance().H2);
			label.setHorizontalAlignment(JLabel.LEFT);
			this.add(label);
			this.add(this.textFields[i]);
			
		}
		
	}
	
	/**
	 * 
	 * @param index The specific JTextField (use ValuesConstances constants)
	 * @return the JTextField specified by the index
	 */
	JTextField getField(int index) {
		
		return this.textFields[index];
	}
	
	/**
	 * 
	 * This class represents the initial stats (raw stats) where the user can enter
	 * the raw hp and raw mp
	 * 
	 * @author Only Brad
	 *
	 */
	final static class InitialStatsPanel extends StatsPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -4382171376215021988L;

		InitialStatsPanel() {
			
			super(GuiConfig.getInstance().RAW_STATS);
		}
		
		@Override
		public void update(Observable o, Object arg) {}
		
	}
	
	/**
	 * This class represents the final stats (at MAX_LEVEL) where the user can see the values
	 * 
	 * @author Only Brad
	 *
	 */
	final static class FinalStatsPanel extends StatsPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -710997824047600320L;
		
		Genders genders;
		Jobs jobs;
		LevelTextField currentLevel;
		LevelTextField nextLevel;
		
		FinalStatsPanel() {
			
			super(GuiConfig.getInstance().FINAL_STATS);
			
			for(int i=0;i<this.textFields.length;i++)
				
				this.textFields[i].setEditable(false);
		}
		
		@Override
		public void update(Observable o, Object arg) {
			
			int code = (Integer)arg;
			
			switch(code){
			
			/* if a field has been modified:
			 * then calculate the new stats */
			
			case JOB:
			case STAT:
			case LEVEL: this.computeNewStats();
			
			}
			
		}

		private void computeNewStats() {
			
			Job currentJob = Job.valueOf(Job.class,
					((String)this.jobs.getSelectedItem()).toUpperCase());
					
			int currentLevel = Integer.parseInt(this.currentLevel.getText());
			int nextLevel = Integer.parseInt(this.nextLevel.getText());
			this.stats.computeStat(currentLevel, nextLevel, currentJob);
			this.changeStats();
		}
		
	}
	
}
