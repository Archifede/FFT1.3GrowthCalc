package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


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
		this.setSize(config.frameSize);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(config.frameLayout);
		this.setIcon();
		
		this.add(config.getTitle(),BorderLayout.NORTH);
		this.add(new InputSelectionPanel(),BorderLayout.CENTER);
		this.add(new InputValuesPanel(), BorderLayout.SOUTH);
		
		this.addBorderToPanels();
	}
	
	/**
	 * Add a border to all the JPanels in this frame
	 */
	private void addBorderToPanels() {
		
		for(Component component : this.getContentPane().getComponents())
			
			if(component instanceof JPanel)
				
				((JPanel) component).setBorder(
						BorderFactory.createLineBorder(Color.BLACK)
						);
				
	}

	/**
	 * Sets the face of Ramza as the icon of the program
	 */
	private void setIcon() {
		
		ImageIcon image = new ImageIcon(GuiConfig.getInstance().ramza);
		this.setIconImage(image.getImage());
		
	}
	

	
	
	
}
