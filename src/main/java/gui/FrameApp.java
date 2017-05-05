package gui;

import javax.swing.*;

import gui.menu.TacticMenuBar;


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
	
	private JTabbedPane tab;
	
	/**
	 * The primary frame of the program. Check CharacterStatPanel to see what the actual content of this program
	 * looks like.
	 */
	public FrameApp() {
		
		GuiConfig config = GuiConfig.getInstance();
		
		this.setTitle(config.TITLE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(config.FRAME_SIZE);
		this.setIcon();	
		this.setJMenuBar(new TacticMenuBar());
		config.setLookAndFeel();
		
		this.add(this.tab = new JTabbedPane());
		this.newTab();
	}
		
	/**
	 * Sets the face of Ramza as the icon of the program
	 */
	private void setIcon() {
		
		ImageIcon image = new ImageIcon(GuiConfig.getInstance().RAMZA);
		this.setIconImage(image.getImage());
		
	}
	
	/**
	 * Create a new tab for Character stat calculation
	 * 
	 */
	public void newTab() {
		
		GuiConfig config = GuiConfig.getInstance();
		
		CharacterStatsPanel newPanel = new CharacterStatsPanel(config.nextTabId());
		tab.add(newPanel.getPanelName(), newPanel);
	}
	
}
