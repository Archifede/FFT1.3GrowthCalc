package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import gui.StatsPanel.FinalStatsPanel;

/**
 * 
 * This class represent one character stat page, where the user can interact with the GUI
 * to calculate the stats of a character.
 * 
 * @author Only Brad
 *
 */
class CharacterStatsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8158749056104678689L;
	
	private String name;
	private final FinalStatsPanel output;
	
	CharacterStatsPanel(String name) {
		
		this.name = name;
		GuiConfig config = GuiConfig.getInstance();
	
		this.setLayout(new BorderLayout());
		this.setBackground(config.PANEL_COLOR);
		
		/* put the both input panels (static and dynamic) in one panel, North and South respectively */
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(new StaticInputPanel(),BorderLayout.NORTH);
		inputPanel.add(new DynamicInputPanel(),BorderLayout.CENTER);
		
		/* create the output panel and insert the FinalStatsPanel in it */
		JPanel outputPanel = new JPanel();
		outputPanel.add(this.output = new StatsPanel.FinalStatsPanel());
		outputPanel.setBackground(config.PANEL_COLOR);
		
		/* Put the title in the top portion, the input area in the middle and the output on the bottom */
		this.add(config.getTitle(),BorderLayout.NORTH);
		this.add(inputPanel,BorderLayout.CENTER);
		this.add(outputPanel,BorderLayout.SOUTH);
	}
	
	/**
	 * Method called when a new inputArea was added in this frame, the output will
	 * be added as an observer to the documentlistener of newly created inputArea
	 * 
	 * @param inputArea
	 */
	void updateOutput(InputArea inputArea) {
		
		inputArea.getListener().addObserver(this.output);
		
	}
	
	public FinalStatsPanel getOutput() {
		
		return this.output;
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
}
