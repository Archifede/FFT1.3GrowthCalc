package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import gui.StatsPanel.FinalStatsPanel;
import gui.components.ButtonConstants;

/**
 * 
 * This class represent one character stat page, where the user can interact with the GUI
 * to calculate the stats of a character.
 * 
 * @author Only Brad
 *
 */
class CharacterStatsPanel extends JPanel implements ButtonConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8158749056104678689L;
	
	private String name;
	private final FinalStatsPanel output;
	private final StaticInputPanel staticInput;
	private final DynamicInputPanel dynamicInput;
	
	/**
	 * 
	 * The primary panel of the program. Each character has one CharacterStatsPanel associated to it.  
	 * To create a new CharacterStatsPanel, you can click in the Menu, then "file", then "new" : a new 
	 * tab with an instance of this class will be created.This class contains all the inputs necessary for 
	 * the calculation of the stats. First the user must Select a gender and specify the initial values then
	 * press on the "OK" button. This will disable on inputs in the StaticInputPanel then create the first 
	 * InputArea (where the user can enter the levels and the job) in the DynamicInputPanel.
	 * 
	 * Every time a value inside an InputArea is modified, the output will automatically updated the new stats.
	 * Clicking on "OK" in the an InputArea will create another one. The "OK" button is desactivated if
	 * the current level and next level don't respect the condition current level < next level.
	 * 
	 * @param name the name of the panel, can be used for the tab name in a JTabbedPane.
	 */
	CharacterStatsPanel(String name) {
		
		this.name = name;
		GuiConfig config = GuiConfig.getInstance();
	
		this.setLayout(new BorderLayout());
		this.setBackground(config.PANEL_COLOR);
		
		/* put the both input panels (static and dynamic) in one panel, North and South respectively */
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());

		inputPanel.add(this.staticInput = new StaticInputPanel(),BorderLayout.NORTH);
		inputPanel.add(this.dynamicInput = new DynamicInputPanel(),BorderLayout.CENTER);
		
		/* create the output panel and insert the FinalStatsPanel in it */
		JPanel outputPanel = new JPanel();
		outputPanel.add(this.output = new StatsPanel.FinalStatsPanel());
		outputPanel.setBackground(config.PANEL_COLOR);
		
		/* Put the title in the top portion, the input area in the middle and the output on the bottom */
		this.add(config.getTitle(),BorderLayout.NORTH);
		this.add(inputPanel,BorderLayout.CENTER);
		this.add(outputPanel,BorderLayout.SOUTH);
		
		/* attach the ok button of the StaticInputArea with the output */
		this.staticInput.attachOkToOutput();
	}
	
	/**
	 * Method called when a new inputArea was added in this panel, the output will
	 * be added as an observer to the documentlistener of newly created inputArea.
	 * Because of how the DynamicInputPanel adds its InputArea, the older InputArea
	 * are disactivated : the output will still observe them but they will never
	 * notify their observers because they will never change. In user terms: The panel
	 * that lets you choose job and levels cannot be modified again therefore the final stats
	 * will no longer be notified of any changes because there will never be any changes; they are
	 * disactivated once a newer panel is created.
	 * 
	 * 
	 * @param inputArea the newly created inputArea that needs to be observed by the output
	 */
	void updateOutput(InputArea inputArea) {
		
		inputArea.getTextListener().addObserver(this.output);
		
	}
	
	FinalStatsPanel getOutput() {
		
		return this.output;
	}
	
	StaticInputPanel getStaticInputPanel() {
		
		return this.staticInput;
	}
	
	DynamicInputPanel getDynamicInputPanel() {
		
		return this.dynamicInput;
	}
	
	String getPanelName() {
		
		return this.name;
	}
	
	void setPanelName(String name) {
		
		this.name = name;
	}
}
