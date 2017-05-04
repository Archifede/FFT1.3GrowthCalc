package gui;

import java.awt.*;
import javax.swing.*;

import gui.StatsPanel.FinalStatsPanel;
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
	
	public FrameApp() {
		
		GuiConfig config = GuiConfig.getInstance();
		
		this.setTitle("Final Fantasy Tactic 1.3 Growth Calculator");
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
