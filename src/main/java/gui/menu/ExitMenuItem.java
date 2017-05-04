package gui.menu;

import javax.swing.JMenuItem;

class ExitMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2385103328651243366L;
	
	public ExitMenuItem() {
		
		super("Exit");
		
		this.addActionListener(e->System.exit(0));
	}

}
