package gui.listeners;

import java.util.Observable;

import gui.components.ButtonContants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Whenever a button that uses this ActionListener is pressed, it notifies all the observers.
 * the button will send its observer a code indicating what action this button represents.
 * 
 * @author Only Brad
 *
 */
public class ButtonListener extends Observable implements ActionListener,ButtonContants {
		
	private int code;

	public ButtonListener(int code) {
		
		this.code = code;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.setChanged();
		this.notifyObservers(code);

	}

}
