package gui.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * DocumentFiler that only allowed values between minValue and maxValue
 * 
 * @author Only Brad
 *
 */
public final class OnlyNumbers extends DocumentFilter {
	
	private int minValue; 
	private int maxValue;
	
	public OnlyNumbers(int minValue, int maxValue) {
		
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {

		string = this.filterString(string);
		
			super.insertString(fb, offset,string, attr);
		}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		
		StringBuilder sb = new StringBuilder(fb.getDocument().getText(
				0, fb.getDocument().getLength())
				);

		sb.replace(offset, offset+length,text);
		
		// verify if the text is between minValue and maxValue
		String filteredText = filterString(sb.toString());
		if(filteredText != "" && verifyRange(Integer.parseInt(filteredText)))
		
			fb.replace(offset, length, filterString(text), attrs); 
		
	}
	
	/**
	 * 
	 * @param string The string that is being added to the document
	 * @return the string with only digits in it
	 */
	private String filterString(String string) {
		
		String filteredString = "";
		
		for(int i=0;i<string.length();i++)
			
			if(Character.isDigit(string.charAt(i)))
				
				filteredString += string.charAt(i);
		
			return filteredString;
		
	}
	
	/**
	 * 
	 * @param val the value to check
	 * @return if the value is in the interval (minValue,maxValue)
	 */
	private boolean verifyRange(int val) {
		
		return val >= this.minValue && val <= this.maxValue;	
	}
	
	/**
	 * Change the minimum value allowed
	 * 
	 * @param minValue
	 */
	public void setMinLevel(int minValue) {
		
		this.minValue = minValue;
	}
	
	/**
	 * Change the maximum value allowed
	 * 
	 * @param maxLevel
	 */
	public void setMaxLevel(int maxValue) {
		
		this.maxValue = maxValue;
		
	}
	
	
}