package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Job;
import data.Params;
import gui.components.ButtonConstants;
import gui.components.ComboBoxInput;
import gui.components.ComboBoxInput.Jobs;
import gui.listeners.ComboBoxListener;
import gui.listeners.ValueListener;
import gui.components.LevelTextField;
import gui.components.ValuesConstants;

/**
 * This class represents one entry in the DynamicInputPanel. Every time a new entry is needed,
 * a new instance of this class is created and is added to the DynamicInputPanel. This panel
 * is formed of 2 LevelTextField : The current level and the next level, the job and a 
 * confirmation button.
 * 
 * To respect the condition nextLevel > currentLevel, every time the currentLevel is changed,
 * A verification must be done to compare the two levels, if the condition is not satisfied, then
 * the "OK" button is desactivated until the condition is satisfied.
 *  
 *  
 * @author Only Brad
 *
 */
final class InputArea extends JPanel implements Observer,ValuesConstants,ButtonConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4612738299499732457L;
	
	private LevelTextField currentLevel;
	private LevelTextField nextLevel;
	private Jobs jobs;
	private JButton ok;
	private ValueListener textListener;
	private ComboBoxListener jobListener;
	
	/**
	 * 
	 * @param minLevel the minLevel allowed
	 */
	InputArea(int minLevel) {
		
		GuiConfig config = GuiConfig.getInstance();
		
		GridLayout thisGridLayout = new GridLayout(2,0); // layout of the InputArea
		GridLayout levelGridLayout = new GridLayout(2,2); // layout of the level panels
		FlowLayout labelLayout = new FlowLayout(FlowLayout.LEFT,5,5); // Layout of the label
		FlowLayout textLayout = new FlowLayout(FlowLayout.CENTER,0,5); // Layout of the text area
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.setLayout(thisGridLayout);
		JPanel levelPanel = new JPanel(levelGridLayout);
		levelPanel.setBackground(config.PANEL_COLOR);
		
		/* Create the current level panel row */
		this.addCurrentLevelRow(labelLayout,textLayout,minLevel,levelPanel);
		this.currentLevel.setText(""+minLevel);
		
		/* Create the next level panel row */
		this.addNextLevelRow(labelLayout,textLayout,minLevel,levelPanel);
		this.nextLevel.setText(""+(minLevel+1));
		
		/*add everything to the InputArea*/
		this.add(levelPanel);
		JPanel jobPanel = new JPanel(labelLayout);
		jobPanel.setBackground(config.PANEL_COLOR);
		JPanel jobAndButtonPanel = new JPanel(new GridLayout(2,1));
		jobAndButtonPanel.setBackground(config.PANEL_COLOR);
		jobAndButtonPanel.add(jobPanel);
		jobAndButtonPanel.add(this.ok = new JButton("OK"));
		jobPanel.add(this.jobs = new ComboBoxInput.Jobs());
		this.add(jobAndButtonPanel);
		
		this.setBackground(config.PANEL_COLOR);
		this.addListeners();
	
	}
	
	/**
	 * Add the "Current Level" row of the panel
	 * 
	 * @param labelLayout the layout of the label 
	 * @param textLayout the layout of the text
	 * @param minLevel the minimum level allowed
	 * @param levelPanel the level panel
	 */
	private void addCurrentLevelRow(FlowLayout labelLayout, FlowLayout textLayout, int minLevel, JPanel levelPanel) {
		
		GuiConfig config = GuiConfig.getInstance();
		
		JPanel labelPanel = new JPanel(labelLayout); //label container
		labelPanel.setBackground(config.PANEL_COLOR);
		JPanel textPanel = new JPanel(textLayout); //text field container
		textPanel.setBackground(config.PANEL_COLOR);
		labelPanel.add(new JLabel("Current Level"));
		textPanel.add(this.currentLevel = new LevelTextField(Params.STARTING_LEVEL,Params.MAX_LEVEL));
		this.currentLevel.setBackground(config.PANEL_COLOR);
		this.currentLevel.setText(String.valueOf(minLevel));
		this.currentLevel.setColumns(2);
		this.currentLevel.setEditable(false);
		levelPanel.add(labelPanel);
		levelPanel.add(textPanel);
	}
	
	/**
	 * Add the "Next Level" row of the panel
	 * 
	 * @param labelLayout the layout of the label 
	 * @param textLayout the layout of the text
	 * @param minLevel the minimum level allowed
	 * @param levelPanel the level panel
	 */
	private void addNextLevelRow(FlowLayout labelLayout, FlowLayout textLayout, int minLevel, JPanel levelPanel) {
		
		GuiConfig config = GuiConfig.getInstance();
		
		JPanel labelPanel = new JPanel(labelLayout); //label container
		labelPanel.setBackground(config.PANEL_COLOR);
		JPanel textPanel = new JPanel(textLayout); //text field container
		textPanel.setBackground(config.PANEL_COLOR);
		labelPanel.add(new JLabel("Next Level"));
		textPanel.add(this.nextLevel= new LevelTextField(Params.STARTING_LEVEL,Params.MAX_LEVEL));
		this.nextLevel.setColumns(2);
		levelPanel.add(labelPanel);
		levelPanel.add(textPanel);
	}
	
	/**
	 * change the enability of the OK button depending 
	 * on the values of currentLevel and nextLevel
	 */
	private void changeOKButtonEnability() {
		
		int currentLevel = 0;
		int nextLevel = 0;

		/* verify if there are values other than numbers inside the text fields 
		 * (happens when deleted or replacing a value)
		 */
		
		if(this.nextLevel.getText().equals("") || this.currentLevel.getText().equals("") ) {
		
			this.ok.setEnabled(false);
			return;
		}
		
		/* verify if currentLevel > nextLevel, if yes then desactivate the "OK" button 
		 * otherwise activate it
		 * */
		currentLevel = Integer.parseInt(this.currentLevel.getText());
		nextLevel = Integer.parseInt(this.nextLevel.getText());
		
		if(currentLevel >= nextLevel || nextLevel == this.nextLevel.getMaxLevel())
			
			this.ok.setEnabled(false);
		
		else
			this.ok.setEnabled(true);
		
	}
	
	/** add a listener to the "next level" and "current level" text field of this object 
	* to verify the condition nextLevel > currentLevel
    * whenever a new value is entered in nextLevel, 
    * the listener will notify this object. Then add a listener to the job combo box.
	*/
	private void addListeners() {
		
		this.setTextListener(new ValueListener(LEVEL));
		this.getTextListener().addObserver(this);
		this.getNextLevel().getDocument().addDocumentListener(this.getTextListener());
		this.getCurrentLevel().getDocument().addDocumentListener(this.getTextListener());
		
		this.jobs.addItemListener(this.jobListener = new ComboBoxListener(JOB));
		
	}
	
	public ComboBoxListener getJobListener() {
		return this.jobListener;
	}

	public void setJobListener(ComboBoxListener jobListener) {
		this.jobListener = jobListener;
	}
	
	
	LevelTextField getCurrentLevel() {
		return currentLevel;
	}

	LevelTextField getNextLevel() {
		return nextLevel;
	}
	
	/**
	 * 
	 * @return the Job enum selected
	 */
	Job getJob() {
		
		return Job.valueOf(((String) this.jobs.getSelectedItem()).toUpperCase());
	}
	
	Jobs getJobs() {
		
		return this.jobs;
	}

	JButton getOkButton() {
		return ok;
	}
	
	/**
	 * 
	 * @param listener the new ValueListener that will be used on the text fields
	 */
	void setTextListener(ValueListener listener) {
		this.textListener = listener;
	}

	/**
	 * 
	 * @return The ValueListener that will be used on the text fields
	 */
	ValueListener getTextListener() {
		return this.textListener;
	}
	

	@Override
	public void update(Observable o, Object arg) {
		
		/* look at what triggered the update method */
		int code = (Integer)arg;
			
		switch(code) {
		case LEVEL: this.changeOKButtonEnability();
		}
		
	}



   
}
