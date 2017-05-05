package gui.menu;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import gui.GuiConfig;

class AboutItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3293540779844217567L;
	
	AboutItem() {
		
		super("About");
		
		/* set the ramza icon */
		GuiConfig config = GuiConfig.getInstance();
		Image image = null;
		try {
			image = ImageIO.read(new File(config.RAMZA));
		} catch (IOException e1) {}	
		ImageIcon icon = new ImageIcon(image);
		
		/* the actionlistener when the "About" menu item is clicked */
		this.addActionListener(e-> {
			
			JTextArea message = new JTextArea();
			message.setEditable(false);
			message.setText(
						"Created by Achifede (Logic and CLI version) and Only Brad (GUI version).\n"
						+ "The source code of this program can be found on the following git repository:\n\n"
						+ "https://github.com/Archifede/FFT1.3GrowthCalc.\n\n"
						+ "This program has been Written in Java 8 using Swing as the library for the user "
						+ "graphics interface."
						);
		
			JOptionPane.showMessageDialog(
					null,
					message,
					"About",
					JOptionPane.INFORMATION_MESSAGE,
					icon);
			});
	}

}
