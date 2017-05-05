package gui;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import data.Params;
import gui.components.ButtonConstants;
import gui.components.ValuesConstants;
import gui.listeners.ButtonListener;


/**
 * This class represents the dynamic panel in the InputPanel which takes note of
 * the current level, next level and job.
 * Since its dynamic, it will be extended every time a new input is needed.
 * 
 * @author Only Brad
 */
final class DynamicInputPanel extends JPanel implements Observer,ButtonConstants,ValuesConstants {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2307527068273600472L;
	
	DynamicInputPanel() {
		
		GuiConfig config = GuiConfig.getInstance();
		this.setBackground(config.PANEL_COLOR);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	/**
	 * create a new InputArea and add it to the DynamicInputPanel object
	 */
	void createNewInputArea() {
		
		int minLevel = Params.STARTING_LEVEL;
		
		/* Verify if there is already an existing InputArea inside this panel, if there
		 * is one then the minLevel of the new InputArea must be equal to the nextLevel
		 * of the previous InputArea. Exemple:
		 * 
		 * [InputArea 0]
		 * currentLevel = 1;
		 * nextLevel = 10;
		 * 
		 * [inputArea 1]
		 * currentLevel = 10;
		 * ...
		 */
		if(this.getComponentCount() != 0) {
			
			int lastIndex = this.getComponentCount()-1;
			InputArea previousArea = (InputArea) this.getComponent(lastIndex);
			minLevel = Integer.parseInt(previousArea.getNextLevel().getText());
			
			/* desactivate the previous "Ok" button, the text fields and the job combobox*/
			previousArea.getOkButton().setEnabled(false);
			previousArea.getCurrentLevel().setEditable(false);
			previousArea.getNextLevel().setEditable(false);
			previousArea.getJobs().setEnabled(false);
			
		}

		InputArea inputArea = new InputArea(minLevel);
		this.addButtonListener(inputArea);
		this.attachToOutput(inputArea);
		
		if(this.getComponentCount() == 0)
			
			inputArea.getCurrentLevel().setEditable(true);
		
		this.add(inputArea);
	}
	
	/**
	 * Attach the Job ComboBoxListener and the LevelTextField's DocumentListener to the output, so
	 * that the output can change the stats whenever the job is changed or the levels are changed.
	 * @param inputArea 
	 */
	private void attachToOutput(InputArea inputArea) {
		
		CharacterStatsPanel parent = (CharacterStatsPanel) this.getParent().getParent();
		StatsPanel stats = parent.getOutput();
		
		inputArea.getTextListener().addObserver(stats);
		inputArea.getJobListener().addObserver(stats);
		
	}

	/**
	 * add a listener to the "OK" button on the inputArea so that a new InputArea can be 
	 * created when pressed.
     * Register this panel with the OK Button's ActionListener so that its notified 
     * when the "OK" button gets clicked.
     * Also register the output of the CharacterStatsPanel with the OK Button's ActionListener so
     * that it notifies it that the previous InputArea is no longer used and the stats that are shown
     * are final.
     * 
     * @param inputArea the area whose OK button need to be observed
	 */
	void addButtonListener(InputArea inputArea) {
	
		CharacterStatsPanel parent = (CharacterStatsPanel) this.getParent().getParent();
		
		ButtonListener buttonListener = new ButtonListener(OK_DYNAMIC);
		buttonListener.addObserver(this); 
		buttonListener.addObserver(parent.getOutput());
		inputArea.getOkButton().addActionListener(buttonListener);
		
	}

	/**
	 * Notify the CharacterStatPanel container panel that a new inputArea was added
	 * 
	 * @param inputArea the newly added inputArea
	 */
	void notifyNewChange() {

		CharacterStatsPanel parent = (CharacterStatsPanel) this.getParent().getParent();
		InputArea lastInputArea = (InputArea) this.getComponent(this.getComponentCount()-1);
		parent.updateOutput(lastInputArea);
	}

	/**
	 * 
	 * @param position the index of the InputArea that will be returned
	 * @return return the InputArea at the index position
	 * 
	 */
	InputArea getInputArea(int position) {
		
		return (InputArea) this.getComponent(position);
	}
	
	/**
	 * 
	 * @return the last InputArea in this object
	 */
	InputArea getLastInputArea() {
		
		return this.getInputArea(this.getComponentCount()-1);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		// verifiy the code to see what changed
		int code = (Integer) arg;
		
		switch(code) {
		
		/* if the OK button in the DynamicInputPanel got clicked, then create a new InputArea then
		refresh the GUI*/
		case OK_DYNAMIC: 
			this.createNewInputArea();
			this.revalidate();
			this.repaint();
			this.notifyNewChange();
			break;
		}
		
	}
	
	
}
