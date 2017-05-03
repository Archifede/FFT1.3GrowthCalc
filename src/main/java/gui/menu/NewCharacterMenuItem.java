package gui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import gui.FrameApp;

/**
 * The option to create a new character stat panel
 * 
 * @author Only Brad
 *
 */
class NewCharacterMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7820737658199024188L;

	NewCharacterMenuItem() {
		
		super("New");
		
		this.addActionListener(e-> {
			
			JPopupMenu parent = (JPopupMenu) this.getParent();
			JMenu invoker = (JMenu) parent.getInvoker();
			FrameApp frame = (FrameApp) invoker.getTopLevelAncestor();
			
			frame.newTab();

		});
	}
	
	

}
