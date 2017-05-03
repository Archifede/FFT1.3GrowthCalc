package gui.listeners;

import java.util.Observable;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This is the class that listens to the JTextField changes. Whenever a stat
 * is changed, all observers are notified. To use with a text field that need 
 * synchronize with other components in the GUI.
 * 
 * @author Only Brad
 *
 */
public final class ValueListener extends Observable implements DocumentListener {

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
