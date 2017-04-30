package gui;

public final class AppGUI {
	
	public final static void main(String[] args) {
		
		FrameApp frame = new FrameApp();
		GuiController controller = GuiController.getInstance(frame);
		controller.start();
		frame.setVisible(true);
	}
}
