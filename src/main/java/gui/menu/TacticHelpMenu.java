package gui.menu;

import javax.swing.JMenu;

class TacticHelpMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2731133344636602966L;
		
	TacticHelpMenu() {
		
		super("Help");
		this.add(new AboutItem());
	}
}
