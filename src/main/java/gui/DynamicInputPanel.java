package gui;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import data.Params;
import gui.components.ButtonContants;
import gui.listeners.ButtonListener;
import gui.listeners.ValueListener;


/**
 * This class represents the dynamic panel in the InputPanel which takes note of
 * the current level, next level and job.
 * Since its dynamic, it will be extended every time a new input is needed.
 * 
 * @author Only Brad
 */
final class DynamicInputPanel extends JPanel implements Observer,ButtonContants {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2307527068273600472L;
	
	DynamicInputPanel() {
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.createNewInputArea();
	}
	
	/**
	 * create a new InputArea and add it to the DynamicInputPanel object
	 */
	private void createNewInputArea() {
		
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
			minLevel = Integer.parseInt(previousArea.getNextLevelPanel().getText());
			
			/* desactivate the previous "Ok" button */
			previousArea.getOkButton().setEnabled(false);
		}

		InputArea inputArea = new InputArea(minLevel);
		/* add a listener to the "OK" button so that a new InputArea can be created when pressed 
		 * Register this panel with the OK Button's ActionListener so that its notified 
		 * when the "OK" button gets clicked
		 */
		ButtonListener buttonListener = new ButtonListener(OK);
		buttonListener.addObserver(this); // the DynamicInputPanel will observe the OK button clicks
		inputArea.getOkButton().addActionListener(buttonListener);
		
		this.add(inputArea);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		/* if the observable is a ButtonListener, then look at the code 
		 * to determine what action to take */
		if(o instanceof ButtonListener) {
			
			int code = (Integer) arg;
			
			switch(code) {
			
			case OK: createNewInputArea();
			this.revalidate();
			this.repaint();
			
			}
		}

	}
	
	
}
