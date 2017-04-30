package gui;

import java.awt.*;
import javax.swing.*;


/**
 *  
 * The Frame of the FFT1.3GrowthCalc GUI Version 
 *  
 * @author Only Brad
 *
 */
public final class FrameApp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3797364490221587074L;
	private InputSelectionPanel inputSelection;
	private InputValuesPanel inputValues;
	private OutputValuesPanel outputValues;

	public FrameApp() {
		
		GuiConfig config = GuiConfig.getInstance();
		
		config.setLookAndFeel();
		this.setSize(config.frameSize);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(config.frameLayout);
		this.setIcon();
		
		/* add all the input panels inside one panel */		
		JPanel inputPanel = new JPanel();
		FlowLayout layout = (FlowLayout) inputPanel.getLayout();
		layout.setHgap(100);
		layout.setVgap(250);
		
		inputPanel.add(this.inputSelection = new InputSelectionPanel());
		inputPanel.add(this.inputValues = new InputValuesPanel());
		
		this.add(config.getTitle(),BorderLayout.NORTH);
		this.add(inputPanel,BorderLayout.CENTER);
		this.inputValues.generateValues();
		this.add(this.outputValues = new OutputValuesPanel(), BorderLayout.SOUTH);
		this.outputValues.generateValues();
		
		addBorderToPanels((JPanel) this.getContentPane());
	}
	
	/**
	 * Add a border to all the JPanels recursively
	 */
	private static void addBorderToPanels(JPanel panel) {
		
		for(int i=0;i<panel.getComponentCount();i++) {
						
			if(panel.getComponent(i) instanceof JPanel) {
				
				addBorderToPanels((JPanel) panel.getComponent(i));
				((JPanel)panel.getComponent(i)).setBorder(
						BorderFactory.createLineBorder(Color.BLACK)
						);
			}
				
		}
				
	}

	/**
	 * Sets the face of Ramza as the icon of the program
	 */
	private void setIcon() {
		
		ImageIcon image = new ImageIcon(GuiConfig.getInstance().ramza);
		this.setIconImage(image.getImage());
		
	}
	
	/**
	 * 
	 * @return the inputSelection JPanel
	 */
	public InputSelectionPanel getInputSelection() {
		
		return this.inputSelection;
	}
	
	/**
	 * 
	 * @return the inputValues JPanel
	 */
	public InputValuesPanel getInputValues() {
		
		return this.inputValues;
	}

	/**
	 * 
	 * @return the outputValues JPanel
	 */
	public OutputValuesPanel getOutputValues() {
		
		return this.outputValues;
	}
	
	
}
