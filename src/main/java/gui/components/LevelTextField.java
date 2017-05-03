package gui.components;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import data.Params;

/**
 * This class represent a level JTextField where the only inputs allowed are 0-99
 * 
 * @author Only Brad
 *
 */
public final class LevelTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8507662671956387050L;
	private int minLevel;
	private int maxLevel;

	/**
	 * Default constructor, the Params class will determine the default min level or max level
	 */
	public LevelTextField() {
		
		this(Params.STARTING_LEVEL,Params.MAX_LEVEL);
		
		this.setColumns(100);
	}
		
	/**
	 * Constructor that sets a specific minLevel and maxLevel
	 * 
	 * @param minLevel the minimum level allowed in this JTextField
	 * @param maxLevel the maximum level allowed in this JTextField
	 */
	public LevelTextField(int minLevel, int maxLevel) {
		
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
		
		AbstractDocument document = (AbstractDocument) this.getDocument();
		document.setDocumentFilter(new OnlyNumbers(this.minLevel, this.maxLevel));
	}
	
	/**
	 * Changes the minimum level allowed in this JTextField
	 * 
	 * @param minLevel
	 */
	public void changeMinLevel(int minLevel) {
		
		AbstractDocument document = (AbstractDocument) this.getDocument();
		document.setDocumentFilter(new OnlyNumbers(this.minLevel = minLevel, this.maxLevel));
	}
	
	/**
	 * Changes the maximum level allowed in this JTextField
	 * 
	 * @param maxLevel
	 */
	public void changeMaxLevel(int maxLevel) {
		
		AbstractDocument document = (AbstractDocument) this.getDocument();
		OnlyNumbers filter = (OnlyNumbers) document.getDocumentFilter();
		filter.setMaxLevel(maxLevel);
	}
	
}
