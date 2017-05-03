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

import data.Params;
import gui.components.ComboBoxInput;
import gui.components.ComboBoxInput.Jobs;
import gui.listeners.ValueListener;
import gui.components.LevelTextField;

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
final class InputArea extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4612738299499732457L;
	
	private LevelTextField currentLevel;
	private LevelTextField nextLevel;
	private Jobs jobs;
	private JButton ok;
	
	/**
	 * 
	 * @param minLevel the minLevel allowed
	 */
	InputArea(int minLevel) {
		
		GridLayout thisGridLayout = new GridLayout(2,0); // layout of the InputArea
		GridLayout levelGridLayout = new GridLayout(2,2); // layout of the level panels
		FlowLayout labelLayout = new FlowLayout(FlowLayout.LEFT,5,5); // Layout of the label
		FlowLayout textLayout = new FlowLayout(FlowLayout.CENTER,0,5); // Layout of the text area
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.setLayout(thisGridLayout);
		JPanel levelPanel = new JPanel(levelGridLayout);
		
		/* Create the current level panel row */
		this.addCurrentLevelRow(labelLayout,textLayout,minLevel,levelPanel);
		this.currentLevel.setText(""+minLevel);
		
		/* Create the next level panel row */
		this.addNextLevelRow(labelLayout,textLayout,minLevel,levelPanel);
		this.nextLevel.setText(""+(minLevel+1));
		
		/*add everything to the InputArea*/
		this.add(levelPanel);
		JPanel jobPanel = new JPanel(labelLayout);
		JPanel jobAndButtonPanel = new JPanel(new GridLayout(2,1));
		jobAndButtonPanel.add(jobPanel);
		jobAndButtonPanel.add(this.ok = new JButton("OK"));
		jobPanel.add(this.jobs = new ComboBoxInput.Jobs());
		this.add(jobAndButtonPanel);
	
		/* add a listener to the "nextLevel" text field to verify the condition nextLevel > currentLevel
		 * whenever a new value is entered in nextLevel, the listener will notify this panel of it
		 */
		ValueListener valueListener = new ValueListener();
		valueListener.addObserver(this);
		this.nextLevel.getDocument().addDocumentListener(valueListener);
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
		
		JPanel labelPanel = new JPanel(labelLayout);
		JPanel textPanel = new JPanel(textLayout);
		labelPanel.add(new JLabel("Current Level"));
		textPanel.add(this.currentLevel = new LevelTextField(Params.STARTING_LEVEL,Params.MAX_LEVEL));
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
		
		JPanel labelPanel = new JPanel(labelLayout);
		JPanel textPanel = new JPanel(textLayout);
		labelPanel.add(new JLabel("Next Level"));
		textPanel.add(this.nextLevel= new LevelTextField(Params.STARTING_LEVEL,Params.MAX_LEVEL)); 
		this.nextLevel.setColumns(2);
		levelPanel.add(labelPanel);
		levelPanel.add(textPanel);
	}
	
	public LevelTextField getCurrentLevel() {
		return currentLevel;
	}

	public LevelTextField getNextLevelPanel() {
		return nextLevel;
	}

	public Jobs getJobs() {
		return jobs;
	}

	public JButton getOkButton() {
		return ok;
	}

	@Override
	public void update(Observable o, Object arg) {
		
	
		/* if the observable is a ValueListener, 
		 * then the value of nextLevel has been changed */
		if(o instanceof ValueListener) {
		
			int currentLevel = 0;
			int nextLevel = 0;
	
			/* verify if there are values other than numbers inside the text field 
			 * (happens when deleted or replacing a value)
			 */
			
			if(this.nextLevel.getText().equals(""))
				
				return;
			
			/* verify if currentLevel > nextLevel, if yes then desactivate the "OK" button 
			 * otherwise activate it
			 * */
			currentLevel = Integer.parseInt(this.currentLevel.getText());
			nextLevel = Integer.parseInt(this.nextLevel.getText());
			
			if(currentLevel > nextLevel)
				
				this.ok.setEnabled(false);
			
			else
				this.ok.setEnabled(true);
			
		}
		
	}

	
}
