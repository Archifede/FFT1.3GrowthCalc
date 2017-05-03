package gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Gender;
import gui.StatsPanel.InitialStatsPanel;
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
	private InitialStatsPanel stats;
	
	StaticInputPanel() {
		
		GuiConfig config = GuiConfig.getInstance();
		
		this.setBackground(config.PANEL_COLOR);
		
		JPanel genderPanel = new JPanel(); // Gender panel container
		genderPanel.setBackground(config.PANEL_COLOR);
		
		JLabel genderLabel = new JLabel("Select Gender");
		genderPanel.add(genderLabel);
		genderPanel.add(this.genders = new ComboBoxInput.Genders());
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		this.add(genderPanel);
		this.add(this.stats = new StatsPanel.InitialStatsPanel());
	}
	
	/**
	 * 
	 * @return the Gender enum selected
	 */
	public Gender getGender() {
		
		return (Gender) this.genders.getSelectedItem();
	}

}
