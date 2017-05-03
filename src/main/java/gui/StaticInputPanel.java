package gui;

import javax.swing.JPanel;

import data.Gender;
import gui.components.ComboBoxInput;
import gui.components.ComboBoxInput.Genders;

/**
 * This class represents the static panel in the InputPanel which takes note of
 * the gender. Since its static, it will remain the same all a long the life of the
 * program
 * 
 * @author Only Brad
 *
 */
final class StaticInputPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9076476461210384210L;
	
	private Genders genders;
	
	StaticInputPanel() {
		
		this.add(this.genders = new ComboBoxInput.Genders());
	}
	
	/**
	 * 
	 * @return the Gender enum selected
	 */
	public Gender getGender() {
		
		return (Gender) this.genders.getSelectedItem();
	}

}
