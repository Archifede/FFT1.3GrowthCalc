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
	
	private int code;
	private boolean activated; // if you want to temporarely disable this listener

	/**
	 * 
	 * @param code The type of text field to which this listener is attached to
	 */
	public ValueListener(int code) {
		
		this.code = code;
		this.activated = true;
	}

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
		
		if(this.activated) {
			this.setChanged();
			this.notifyObservers(code);
		}
	}
	
	/**
	 * Method that enables or disables this listener
	 * 
	 * @param activated activated or not
	 */
	public void setActivated(boolean activated) {
		
		this.activated = activated;
	}

}
