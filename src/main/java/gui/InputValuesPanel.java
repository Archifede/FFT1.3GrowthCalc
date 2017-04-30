package gui;

import javax.swing.*;


/**
 * This class represents the Input Values Panel of the FrameApp. This is where the user is to enter numerical
 * values. Some values will be automatically generated because they depend on Gender only.
 * 
 * @author Only Brad
 *
 */
public class InputValuesPanel extends JPanel implements ValuesConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4795777516067075188L;
	private JTextField[] textFields;

	public InputValuesPanel() {
		
		this.setLayout(GuiConfig.getInstance().inputValuesLayout);
		this.createTextFields();
		this.addTextFieldsAndLabels();
		
	}
	
	private void createTextFields() {
		
		this.textFields = new JTextField[5];
		
		for(int i=0;i<this.textFields.length;i++) 
			
			textFields[i] = new JTextField();
		
	}

	private void addTextFieldsAndLabels() {
		
		String[] labels = {"HP","MP","Speed","Pa","Magic"};
		
		for(int i=0;i<labels.length;i++) {
			
			JLabel label = new JLabel(labels[i]);
			label.setFont(GuiConfig.getInstance().h2);
			label.setHorizontalAlignment(JLabel.CENTER);
			this.add(label);
			this.add(this.textFields[i]);
		}
	}

}
