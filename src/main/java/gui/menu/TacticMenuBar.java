package gui.menu;

import javax.swing.JMenuBar;

/**
 * 
 * The menu bar of this app
 * 
 * @author Only Brad
 *
 */
public class TacticMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4918486517459648584L;
	
	public TacticMenuBar() {
		
		this.add(new TacticFileMenu());
		this.add(new TacticHelpMenu());
	}
}
