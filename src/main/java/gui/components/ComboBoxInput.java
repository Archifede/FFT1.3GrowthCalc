package gui.components;

import javax.swing.JComboBox;

import data.Gender;
import data.Job;

/**
 * 
 * Class that represent a JComboBox of enums. The class will automatically generate the values
 * of the enums based of all the enum intances.
 * 
 * @author Only Brad
 *
 * @param <E>
 */
public abstract class ComboBoxInput<E extends Enum<E>> extends JComboBox<String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5408268611698071942L;

	public ComboBoxInput(Class<E> clazz) {
		
		super(getEnumNames(clazz));
	}
	
	/**
	 * The Job JComboBox
	 * 
	 * @author Only Brad
	 *
	 */
	public static class Jobs extends ComboBoxInput<Job> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5750872502857393780L;

		public Jobs() {
			super(Job.class);
		}
	}
	
	/**
	 * The Gender JComboBox
	 * 
	 * @author Only Brad
	 *
	 */
	public static class Genders extends ComboBoxInput<Gender> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5386357295488040789L;

		public Genders() {
			super(Gender.class);
		}

	}
	
	/**
	 * 
	 * @return an array of String containing the names of elements of an enum in lower case
	 */
	static <E extends Enum<E>> String[] getEnumNames(Class<E> clazz) {
		
		E[] e = clazz.getEnumConstants();
		
		String[] enumNames = new String[e.length];
		
		for(int i=0;i<enumNames.length;i++)
			
			enumNames[i] = e[i].name().toLowerCase();
		
		return enumNames;
				
				
	}
}

