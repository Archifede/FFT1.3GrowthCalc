package gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Gender;
import gui.StatsPanel.InitialStatsPanel;
import gui.components.ComboBoxInput;
import gui.components.ComboBoxInput.Genders;
import gui.components.ValuesConstants;
import gui.listeners.ComboBoxListener;
import gui.listeners.ValueListener;

/**
 * This class represents the static panel in the InputPanel which takes note of
 * the gender. Since its static, it will remain the same all a long the life of the
 * program. Disactived onces the "OK" button is pressed.
 * 
 * @author Only Brad
 *
 */
final class StaticInputPanel extends JPanel implements ValuesConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9076476461210384210L;
	
	private Genders genders;
	private InitialStatsPanel stats;
	private JButton okButton;
	private ValueListener statsListener;
	private ComboBoxListener genderListener;
	
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
		this.add(this.okButton = new JButton("OK"));
		
		this.addListeners();
	}

	/**
	 * Add listeners to both the gender and InitialStatsPanel
	 */
	private void addListeners() {
		
		this.genders.addItemListener(this.genderListener = new ComboBoxListener(GENDER));
		this.statsListener = this.stats.addValueListener();
		this.okButton.addActionListener(e -> {
			
			this.genders.setEnabled(false);
			this.stats.setEnabled(false);
			
		});
		
	}

	/**
	 * 
	 * @return the Gender enum selected
	 */
	Gender getGender() {
		
		return (Gender) this.genders.getSelectedItem();
	}

	public ValueListener getStatsListener() {
		return statsListener;
	}

	public ComboBoxListener getGenderListener() {
		return genderListener;
	}

}
