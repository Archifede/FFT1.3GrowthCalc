package gui;

import java.awt.Dimension;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

import data.Gender;

/**
 * This class represents the Input Values Panel of the FrameApp. This is where the user is to enter numerical
 * values. Some values will be automatically generated because they depend on Gender only.
 * 
 * @author Only Brad
 *
 */
class InputValuesPanel extends JPanel implements ValuesConstants,Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4795777516067075188L;
	protected JTextField[] textFields;
	protected String[] labels;  

	InputValuesPanel(String ... labels) {
		
		this.labels = labels;
		
		this.setLayout(GuiConfig.getInstance().inputValuesLayout);
		this.createTextFields();
		this.addTextFieldsAndLabels();
		
		
		/* double the width of this panel */
		this.setPreferredSize( new Dimension(
				
				(int)this.getPreferredSize().getWidth()*2,
				(int)this.getPreferredSize().getHeight()
				
				) 		
		);

	}
	
	InputValuesPanel() {
		
		this("Raw HP","Raw MP","Raw Speed","Raw Pa","Raw Magic");
	}
	
	/**
	 * generate values of the JTextFields, this should be called only once this panel is inside another container,
	 * otherise this.getTopLevelAncestor will return null. This method should be called everytime a
	 * JComboBox value associated with the InputValuesPanel is changed.
	 */
	void generateValues() {
		
		FrameApp frame = (FrameApp) this.getTopLevelAncestor();
		InputSelectionPanel panel = frame.getInputSelection();
		
		Gender currentGender = Gender.valueOf(Gender.class,
				((String)panel.getGenders().getSelectedItem()).toUpperCase());
		
		this.textFields[SPEED].setText(String.valueOf( (int)currentGender.getRawSpeed()));
		this.textFields[PHYSICAL_DAMAGE].setText(String.valueOf( (int)currentGender.getRawPa()));
		this.textFields[MAGIC].setText(String.valueOf( (int)currentGender.getRawMa()));
		
	}

	/**
	 * Generate the JTextFields and store them inside an array
	 */
	private void createTextFields() {
		
		this.textFields = new JTextField[5];
		
		for(int i=0;i<this.textFields.length;i++) {
			
			textFields[i] = new JTextField();
			
			PlainDocument document = (PlainDocument)textFields[i].getDocument();
			document.setDocumentFilter(new OnlyNumbers());
		}
		
		this.textFields[HP].setText("0");
		this.textFields[MP].setText("0");
		this.textFields[SPEED].setEditable(false);
		this.textFields[PHYSICAL_DAMAGE].setEditable(false);
		this.textFields[MAGIC].setEditable(false);
	}
	
	/**
	 * Add the JTextFields and the JLabel into the JPanel
	 */
	private void addTextFieldsAndLabels() {
				
		for(int i=0;i<labels.length;i++) {
			
			JLabel label = new JLabel(this.labels[i]);
			label.setFont(GuiConfig.getInstance().h2);
			label.setHorizontalAlignment(JLabel.CENTER);
			this.add(label);
			this.add(this.textFields[i]);
		}
	}
	
	/**
	 * 
	 * @param index The specific JTextField (use ValuesConstances constants)
	 * @return the JTextField specified by the index
	 */
	public JTextField getField(int index) {
		
		return this.textFields[index];
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		/* verify if the subject notifying the changes is a BoxItemListener */
		if(o instanceof BoxItemListener) 
			
			this.generateValues();
		
	}
	
	
	
	/**
	 * DocumentFiler so that only numbers can be entered in the stats text fields
	 * 
	 * @author Only Brad
	 *
	 */
	private class OnlyNumbers extends DocumentFilter {

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			
			super.insertString(fb, offset, filterString(string), attr);
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			
			super.replace(fb, offset, length, filterString(text), attrs);
		}
		

		private String filterString(String string) {
						
			String filteredString = "";
			
			for(int i=0;i<string.length();i++)
				
				if(Character.isDigit(string.charAt(i)))
					
					filteredString += string.charAt(i);
			
			return filteredString;
		}
		
		
	}

}
