package gui;

import java.util.Observable;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This is the class that listens to the JTextField changes. Whenever a stats (HP and MP)
 * is changed, all observers are notified (example the JTextFields of the OutPutValuesPanel class)
 *  
 * 
 * @author Only Brad
 *
 */
public class StatValueListener extends Observable implements DocumentListener {

	@Override
	public void insertUpdate(DocumentEvent e) {
		
		this.changed();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		
		this.changed();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		
		this.changed();

	}
	
	/**
	 * Notify the observers
	 */
	private void changed() {
		
		this.setChanged();
		this.notifyObservers();
	}

}
