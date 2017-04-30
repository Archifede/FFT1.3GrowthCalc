package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;

/**
 * 
 * This is the class that listens to the JComboBox changes. Whenever a combo box value
 * is changed, all observers are notified (example the JTextFields of the InputValuesPanel class)
 * 
 * @author Only Brad
 *
 */
public class BoxItemListener extends Observable implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		this.setChanged();
		this.notifyObservers();

	}

}
