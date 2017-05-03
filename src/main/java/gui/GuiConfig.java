package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;


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
	
	private final String LOOK_AND_FEEL;
	private final String TITLE;
	private final String TITLE_IMAGE;
	private final String FONT_PATH;
	private final String TITLE_IMAGE_PATH;
	private final String TACTIC_FONT_NAME;
	
	public final Dimension FRAME_SIZE;
	public final String RAMZA;
	public final Font H1;
	public final Font H2;
	public final Font TACTIC_FONT;
	public final int TITLE_SIZE;
	public final int LABEL_SIZE;
	public final String[] STATS;
	public final Color PANEL_COLOR;

	private int tabId;
	
	private GuiConfig() {
		
		/* strings */
		this.LOOK_AND_FEEL = UIManager.getSystemLookAndFeelClassName();
		this.TITLE = "FFT 1.3 GrowthCalc";
		this.TACTIC_FONT_NAME = "Final_Fantasy_Tactics.ttf";
		this.STATS = new String[]{"HP","MP","Speed","Physical Damage","Magic"};
		this.TITLE_IMAGE = "Final_Fantasy_Tactics_Logo.jpg";
		
		/* path strings */
		this.RAMZA = Paths.get(new File("").getAbsolutePath(),"img","ramza.png").toString();
		this.FONT_PATH = Paths.get(new File("").getAbsolutePath(),"font").toString();
		this.TITLE_IMAGE_PATH = Paths.get(new File("").getAbsolutePath(),"img",this.TITLE_IMAGE).toString();
		
		/* values */
		this.TITLE_SIZE = 50;
		this.LABEL_SIZE = 30;
		
		/* fonts */
		this.H1 = this.h1();
		this.H2 = this.h2();
		this.TACTIC_FONT = generateTacticFont(this.LABEL_SIZE);
				
		/* dimensions */
		this.FRAME_SIZE = new Dimension(1024,768);
		
		/* colors */
		this.PANEL_COLOR = Color.WHITE;
		
	}
	
	private Font generateTacticFont(float size) {
		
		String path = Paths.get(
				this.FONT_PATH,this.TACTIC_FONT_NAME
				).toString();

	    try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
			font = font.deriveFont(Font.PLAIN,this.TITLE_SIZE);
		    
		    return font;
		    
			
		} catch (FontFormatException | IOException e) {
			return null;
		}
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
			UIManager.setLookAndFeel(this.LOOK_AND_FEEL);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return The Panel title of the app
	 */
	public JLabel getTitle() {
		
		ImageIcon imageIcon = null;
		try {
			imageIcon = new ImageIcon(ImageIO.read(new File(this.TITLE_IMAGE_PATH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel label = new JLabel(imageIcon);
		return label;
	
			
	}

	public String nextTabId() {
		return "character"+String.valueOf(++this.tabId);
	}
}
