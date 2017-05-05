package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import data.Gender;
import data.Job;
import gui.components.ButtonConstants;
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
abstract class StatsPanel extends JPanel implements ValuesConstants,ButtonConstants,Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4795777516067075188L;
	protected JTextField[] textFields;
	protected String[] labels;
	protected Stats stats;
	protected ValueListener valueListener;
	
	
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
	
	StatsPanel(JPanel parent) {
		
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
		
		return this.valueListener = listener;
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
	 * Set all the JTextFields of this object editable property to bool
	 * 
	 * @param bool the editable property value of the JTextFields 
	 */
	public void setEditable(boolean bool) {
		
		for(JTextField textField : this.textFields)
			
			textField.setEditable(bool);
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
		public void update(Observable o, Object arg) {
			
			int code = (Integer)arg;
			
			switch(code){
			
			/* if a field has been modified:
			 * then calculate the new stats */
			
			case GENDER: this.computeRawStats();break;
			case STAT: this.computeCustomStats();
			
			}
		}
		
		/**
		 * Raw stats will change when changing the gender. Every time the Gender value is changed in the GUI
		 * compute the new Raw Stats. Extract the Gender from the StaticInputPanel of the CharacterStatPanel parent.
		 */
		private void computeRawStats() {
			
			StaticInputPanel staticInput = (StaticInputPanel) this.getParent();
			Gender gender = staticInput.getGender();
			
			this.compute(gender);
		}
		
		/**
		 * Generate the new raw stats using the Gender
		 * The max values of HP and MP will be automatically generated
		 */
		private void compute(Gender gender) {
			
			double hp = gender.getRawHps()[1];
			double mp = gender.getRawMps()[1];
			double sp = gender.getRawSpeed();
			double pa = gender.getRawPa();
			double ma = gender.getRawMa();
			
			this.stats.setStats(new double[]{hp,mp,sp,pa,ma});
			
			/*disable the listener of the textfield so that the textfield listener doesn't notify
			its observers of changed. Since the InitialStatsPanel is observing itself, every time values are
			written in it, it will notify itself of the changes which will case glitches. */
			
			this.valueListener.setActivated(false);
			this.changeStats();
			this.valueListener.setActivated(true);
		}
		
		/**
		 * Update the Stats object by using the values inside the JTextFields
		 */
		private void computeCustomStats() {
			
			double[] stats = new double[this.stats.getStats().length];
				
			for(int i=0;i<stats.length;i++) {
					
				if(this.textFields[i].getText().equals("")) //happens when deleting or replacing
					
					continue;
				
				stats[i] = Integer.parseInt(this.textFields[i].getText());
				
			}
				
			this.stats.setStats(stats);
			
		}

		
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
		
		Gender gender;
		Job job;
		LevelTextField currentLevel;
		LevelTextField nextLevel;
		Stats statsBuffer;
		
		/**
		 * Disable all fields
		 */
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
			case LEVEL: this.showNewStats();break; 
			case OK_STATIC: this.copyRawStats();break; // the "OK" button in the StaticInputPanel was clicked
			case OK_DYNAMIC: this.saveNewStats(); // the "OK" button in the DynamicInputPanel was clicked			
			}
			
		}
		
		/**
		 * This method is called when the user presses the "OK" button in a DynamicInputPanel, it will signal
		 * that the changes made in the previous InputArea are final which means that we can update 
		 * the statsBuffer.
	     */

		private void saveNewStats() {
			
			this.statsBuffer.setStats(this.stats);
			
		}

		/**
		 * This method is called when the "OK" Button in the StaticInputArea is pressed
		 * Copy the values in the raw StatsPanel into the Stats object, then refresh the GUI.
		 */
		private void copyRawStats() {
			
			CharacterStatsPanel parent = (CharacterStatsPanel) this.getParent().getParent();
			StaticInputPanel staticInput = parent.getStaticInputPanel();
			StatsPanel rawStatsPanel = staticInput.getRawStats();
			
			this.stats.setStats(rawStatsPanel.stats);
			this.changeStats();
			this.statsBuffer = new Stats(this.stats);
		}

		/**
		 * Extract the needed components from the parent CharacterStatsPanel, then
		 * get the needed values (job, nextLevel and currentLevel) in order
		 * to calculate the new stats based of the older ones. Use the
		 * statBuffer instead of the actual stats because this method will
		 * only show the newStats. After showing the stats, the StatsPanel
		 * internal Stats object will reset to the previous one that was stored in the
 		 * statBuffer attribute.
		 */
		private void showNewStats() {
			
			/* revert back to the previous stats (otherwise every time the user enters a value the program
			   will recalculate over the previous value and give erronous values */
			this.stats.setStats(this.statsBuffer); 

			/* get all the needed components */
			CharacterStatsPanel parent = (CharacterStatsPanel) this.getParent().getParent();
			DynamicInputPanel dynamicInput = parent.getDynamicInputPanel();
			InputArea lastInputArea = dynamicInput.getLastInputArea();
			this.job = lastInputArea.getJob();
			this.nextLevel = lastInputArea.getNextLevel();
			this.currentLevel = lastInputArea.getCurrentLevel();
			
			/* extract the currentLevel and nextLevel from these components */
			String textCurrentLevel = this.currentLevel.getText();
			String nextCurrentLevel = this.nextLevel.getText();
			
			/* will happen when deleting or replacing a value */
			if(textCurrentLevel.equals("") || nextCurrentLevel.equals("")) 
					
				return; 
			
			/* parse values to int */
			int currentLevel = Integer.parseInt(textCurrentLevel);
			int nextLevel = Integer.parseInt(nextCurrentLevel);
			
			/* show the new stats */
			this.stats.computeStat(currentLevel, nextLevel, this.job);
			this.changeStats();
			
		}
		
	}
	
}
