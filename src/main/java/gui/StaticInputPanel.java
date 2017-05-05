package gui;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Gender;
import gui.StatsPanel.InitialStatsPanel;
import gui.components.ButtonConstants;
import gui.components.ComboBoxInput;
import gui.components.ComboBoxInput.Genders;
import gui.components.ValuesConstants;
import gui.listeners.ButtonListener;
import gui.listeners.ComboBoxListener;
import gui.listeners.ValueListener;

/**
 * This class represents the static panel in the InputPanel which takes note of
 * the gender. Since its static, it will remain the same all a long the life of the
 * program. Disactived once the "OK" button is pressed.
 * 
 * @author Only Brad
 *
 */
final class StaticInputPanel extends JPanel implements ValuesConstants,ButtonConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9076476461210384210L;
	
	private Genders genders;
	private InitialStatsPanel rawStats;
	private ObserverButton okButton;
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
		this.genders.setSelectedIndex(-1);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		this.add(genderPanel);
		this.add(this.rawStats = new StatsPanel.InitialStatsPanel());
		this.add(this.okButton = new ObserverButton("OK"));
		
		this.addListeners();
	}

	/**
	 * Add listeners to both the gender and InitialStatsPanel.
	 * A ComboBoxListener will be added to the gender JComboBox and a the JTextFields of the 
	 * StatPanel's Document will receive a DocumentFilter that will listen to all changes.
	 * The "OK" button will desactivate all the components in the current object then will
	 * create the first InputArea in the DynamicInputPanel of the CharacterStatPanel. 
	 * the "OK" button will observe the gender JComboBox for changes, if a gender is selected
	 * then the "OK" button will be activated
	 */
	private void addListeners() {
		
		this.genders.addItemListener(this.genderListener = new ComboBoxListener(GENDER));
		this.statsListener = this.rawStats.addValueListener();
		this.genderListener.addObserver(this.rawStats);
		this.genderListener.addObserver(this.okButton);
		
		this.okButton.addActionListener(e -> {
			
			CharacterStatsPanel parent = (CharacterStatsPanel) StaticInputPanel.this.getParent().getParent();
			this.genders.setEnabled(false);
			this.rawStats.setEditable(false);
			this.okButton.setEnabled(false);
	
			parent.getDynamicInputPanel().createNewInputArea();
			parent.revalidate();
			parent.repaint();
			
		});

	}
	
	/**
	 * Attach the "OK" button of this panel to the output StatsPanel of the CharacterStatsPanel
	 */
	void attachOkToOutput() {
		
		ButtonListener buttonListener = new ButtonListener(OK_STATIC);
		this.okButton.addActionListener(buttonListener);
		CharacterStatsPanel parent = (CharacterStatsPanel) StaticInputPanel.this.getParent().getParent();
		buttonListener.addObserver(parent.getOutput());
		
	}
	
	/**
	 * 
	 * @return the Gender enum selected
	 */
	Gender getGender() {
		
		return  Gender.valueOf( ((String)this.genders.getSelectedItem()).toUpperCase());
	}

	ValueListener getStatsListener() {
		return statsListener;
	}

	ComboBoxListener getGenderListener() {
		return genderListener;
	}
	
	StatsPanel getRawStats() {
		
		return this.rawStats;
	}
	
	/**
	 * A Special JButton that will listene to an Observable,
	 * if the Observable calls the update method on this button,
	 * then this button will become enabled.
	 * 
	 * @author Only Brad
	 *
	 */
	private class ObserverButton extends JButton implements Observer{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2498382459115128447L;

		public ObserverButton(String title) {
			super(title);
			this.setEnabled(false);
		}

		@Override
		public void update(Observable o, Object arg) {
			
			/* verify the code */
			int code = (Integer)arg;
			
			switch(code) {
			case GENDER:
				this.setEnabled(true);
			}
			
		}
		
		

	}


}
