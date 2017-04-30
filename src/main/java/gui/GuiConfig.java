package gui;

import java.awt.*;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.*;

/**
 * 
 * The configuration variables of the GUI, contains values that can change the look of the
 * GUI. [Singleton]
 * 
 * @author Only Brad
 *
 */
public final class GuiConfig {
	

	private static GuiConfig instance = new GuiConfig(); //Singleton
	
	private final String lookAndFeel;
	private final String title;
	
	public final Dimension frameSize;
	public final String ramza;
	public final LayoutManager frameLayout;
	public final LayoutManager inputSelectionLayout;
	public final LayoutManager inputValuesLayout;
	public final Font h1;
	public final Font h2;

	private GuiConfig() {
		
		this.lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		this.title = "FFT 1.3 GrowthCalc";
		this.h1 = this.h1();
		this.h2 = this.h2();
		this.frameSize = new Dimension(1024,768);
		this.ramza = Paths.get(new File("").getAbsolutePath(),"ico","ramza.png").toString();
		this.frameLayout = new BorderLayout();
		this.inputSelectionLayout = new FlowLayout();
		this.inputValuesLayout = new GridLayout(0,2);
	}
	
	/**
	 * 
	 * @return the font of the "h1" label
	 */
	private Font h1() {
		
		return labelFont(3);
		
	}
	
	private Font h2() {
		
		return labelFont(1.5);
	}
	
	private Font labelFont(double sizeModifier) {
		
		Font font = UIManager.getFont("Label.font");
		String name = font.getName();
		int size = font.getSize();
		int style = font.getStyle();
		
		return new Font(name,style, (int)(sizeModifier*size));
	}


	/** 
	 * return the GuiConfig object 
	 */
	public static GuiConfig getInstance() {
		
		return instance;
	}

	/**
	 * Set the look and feel of the OS on which you are using this frame
	 */
	public void setLookAndFeel() {
		
		try {
			UIManager.setLookAndFeel(this.lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return The JLabel title of the app
	 */
	public JLabel getTitle() {
		
		JLabel title = new JLabel(this.title);
		title.setFont(this.h1);
		title.setHorizontalAlignment(JLabel.CENTER);
		return title;
	}
	
}
