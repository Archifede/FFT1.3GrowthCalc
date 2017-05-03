package gui.listeners;

import gui.FrameApp;
import gui.components.ValuesConstants;

/**
 * 
 * Class that attachs the ActionListeners to the GUI elements. 
 * [Singleton]
 * 
 * @author Only Brad
 *
 */
public final class GuiController implements ValuesConstants {
	
	private FrameApp frame; 
	
	private static GuiController instance;
	
	private GuiController(FrameApp frame) {
		
		this.frame = frame;
	}
	
	public static GuiController getInstance() {
		
		if(instance == null)
			
			throw new NullPointerException("Cannot instantiate a GuiController without a FrameApp pointer");
		
		return instance;
			
	}
	
	public static GuiController getInstance(FrameApp frame) {
		
		if(instance == null)
			
			return (instance = new GuiController(frame));
		
		/* change the frame if they are different) */
		else if(instance.frame != frame)
			
			instance.frame = frame;
	
		return instance;
		
	}
	
	/* attach the listeners to the FrameApp components */
	public void start() {
		

	}

}
