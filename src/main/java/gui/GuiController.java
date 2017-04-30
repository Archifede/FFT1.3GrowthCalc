package gui;

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
		
		/* get the panels */
		InputSelectionPanel selection = frame.getInputSelection();
		InputValuesPanel values = frame.getInputValues();
		OutputValuesPanel output = frame.getOutputValues();
		
		/* create a BoxItemListener and attach observers to it */
		BoxItemListener itemListener = new BoxItemListener();
		itemListener.addObserver(values);
		itemListener.addObserver(output);
		
		/* create a StatsValueListener and attach observers to it */
		StatValueListener statListener = new StatValueListener();
		statListener.addObserver(output);
		
		/* add the listeners to their respective components */
		selection.getGenders().addItemListener(itemListener);
		selection.getJobs().addItemListener(itemListener);
		values.getField(HP).getDocument().addDocumentListener(statListener);
		values.getField(MP).getDocument().addDocumentListener(statListener);
	}

}
