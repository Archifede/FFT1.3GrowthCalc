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

	public FrameApp() {
		
		GuiConfig config = GuiConfig.getInstance();
		
		config.setLookAndFeel();
		this.setSize(config.FRAME_SIZE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(config.FRAME_LAYOUT);
		this.setIcon();	
		
		/* put the both input panels (static and dynamic) in one panel, North and South respectively */
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(new StaticInputPanel(),BorderLayout.NORTH);
		inputPanel.add(new DynamicInputPanel(),BorderLayout.CENTER);
		
		/* Put the title in the top portion, the input area in the middle and the output on the bottom */
		this.add(config.getTitle(),BorderLayout.NORTH);
		this.add(inputPanel,BorderLayout.CENTER);
		this.add(new StatsPanel.InitialStatsPanel(),BorderLayout.SOUTH);

	}
		
	/**
	 * Sets the face of Ramza as the icon of the program
	 */
	private void setIcon() {
		
		ImageIcon image = new ImageIcon(GuiConfig.getInstance().RAMZA);
		this.setIconImage(image.getImage());
		
	}

	
}
