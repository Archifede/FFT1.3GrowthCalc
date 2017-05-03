package gui.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;

/**
 * 
 * This is the class that listens to the JComboBox changes. Whenever a combo box value
 * is changed, all observers are notified
 * 
 * @author Only Brad
 *
 */
public class ComboBoxListener extends Observable implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		this.setChanged();
		this.notifyObservers();

	}

}
