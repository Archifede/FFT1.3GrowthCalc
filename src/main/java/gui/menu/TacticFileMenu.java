package gui.menu;

import javax.swing.JMenu;

/**
 * The "File" menu in the frame's menu bar
 * 
 * @author Only Brad
 *
 */
class TacticFileMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2810142159673941790L;
	
	TacticFileMenu() {
		
		super("File");
		this.add(new NewCharacterMenuItem());
		this.add(new ExitMenuItem());
	}
}
