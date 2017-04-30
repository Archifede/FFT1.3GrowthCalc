package gui;

import javax.swing.*;
import data.*;


/**
 * This class represents the Input Selection Panel of the FrameApp. This is where the user is going to input
 * the Gender and the Job.
 * 
 * @author Only Brad
 *
 */
public final class InputSelectionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8600368121678685563L;
	private JComboBox<String> genders;
	private JComboBox<String> jobs;
	
	
	InputSelectionPanel() {
		
		this.setLayout(GuiConfig.getInstance().inputSelectionLayout);
		
		/* Add the combo box and labels in the panel */
		JLabel genderLabel = new JLabel("Gender");
		this.add(genderLabel);
		this.add(this.genders = new JComboBox<>(getGenders()));
		JLabel jobLabel = new JLabel("Job");
		this.add(jobLabel);
		this.add(this.jobs = new JComboBox<>(getJobs()));
		
		/* attach the label to the combo box */
		genderLabel.setLabelFor(this.genders);
		jobLabel.setLabelFor(this.jobs);
		
	}
	
	/**
	 * 
	 * @return an array of String containing the names of elements of an enum in lower case
	 */
	private static <E extends Enum<E>> String[] getEnumNames(Class<E> clazz) {
		
		String[] enumStrings = new String[clazz.getEnumConstants().length];
		
		for(int i=0;i<enumStrings.length;i++)
			
			enumStrings[i] = clazz.getEnumConstants()[i].name().toLowerCase();
		
		return enumStrings;
	}

	/**
	 * 
	 * @return an array of String containing the names of the jobs in lower case using
	 * the Job enum.
	 */
	private static String[] getJobs() {
		
		return getEnumNames(Job.class);
	}
	
	/**
	 * 
	 * @return an array of String containing the names of the genders in lower case using the 
	 * Gender enum.
	 */
	private static String[] getGenders() {
		
		return getEnumNames(Gender.class);
	}
	
}
