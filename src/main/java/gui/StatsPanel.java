package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

import data.Gender;
import gui.components.OnlyNumbers;
import gui.components.ValuesConstants;
import logic.Stats;

/**
 * This class represents a Stats Panel. This is where the value of the stats are going to be
 * shown and some of them can be modified.
 * 
 * @author Only Brad
 *
 */
abstract class StatsPanel extends JPanel implements ValuesConstants,Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4795777516067075188L;
	protected JTextField[] textFields;
	protected String[] labels;
	protected Stats stats;
	
	
	StatsPanel(String ... labels) {
		
		this.stats = new Stats(new double[]{0,0,0,0,0});
		
		GuiConfig config = GuiConfig.getInstance();
		
		this.labels = labels;
		this.setBackground(config.PANEL_COLOR);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new GridLayout(labels.length,2));
		this.createTextFields();
		this.addTextFieldsAndLabels();
		
		
		/* double the width of this panel, only works in a FlowLayout */
		this.setPreferredSize( new Dimension(
				
				(int)this.getPreferredSize().getWidth()*2,
				(int)this.getPreferredSize().getHeight()
				
				) 		
		);

	}
	
	StatsPanel() {
		
		this(GuiConfig.getInstance().STATS);
	}
	
	/**
	 * change the stats on the GUI using the values in the Stats object
	 */
	void changeStats() {
		
		for(int i=0;i<this.textFields.length;i++) {
			
			int stat = (int) this.stats.getStats(i);
			String textStat = String.valueOf(stat);
			this.textFields[i].setText(textStat);
		
		}
	}
	
	/**
	 * generate values of the JTextFields, this should be called only once this panel is inside another container,
	 * otherise this.getTopLevelAncestor will return null. This method should be called everytime a
	 * JComboBox value associated with the InputValuesPanel is changed.
	 */
	/*void generateValues() {
		
		FrameApp frame = (FrameApp) this.getTopLevelAncestor();
		InputSelectionPanel panel = frame.getInputSelection();
		
		Gender currentGender = Gender.valueOf(Gender.class,
				((String)panel.getGenders().getSelectedItem()).toUpperCase());
		
		this.textFields[SPEED].setText(String.valueOf( (int)currentGender.getRawSpeed()));
		this.textFields[PHYSICAL_DAMAGE].setText(String.valueOf( (int)currentGender.getRawPa()));
		this.textFields[MAGIC].setText(String.valueOf( (int)currentGender.getRawMa()));
		
	}*/

	/**
	 * Generate the JTextFields and store them inside an array
	 */
	protected void createTextFields() {
		
		this.textFields = new JTextField[5];
		
		for(int i=0;i<this.textFields.length;i++) {
			
			textFields[i] = new JTextField();
			textFields[i].setBackground(Color.WHITE);
			PlainDocument document = (PlainDocument)textFields[i].getDocument();
			document.setDocumentFilter(new OnlyNumbers(0,Integer.MAX_VALUE));
		}
		
		this.changeStats();
	}
	
	/**
	 * Add the JTextFields and the JLabel into the JPanel
	 */
	private void addTextFieldsAndLabels() {
		
		for(int i=0;i<labels.length;i++) {
			
			JLabel label = new JLabel(this.labels[i]);
			label.setFont(GuiConfig.getInstance().H2);
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
	
	/**
	 * 
	 * This class represents the initial stats (raw stats) where the user can enter
	 * the raw hp and raw mp
	 * 
	 * @author Only Brad
	 *
	 */
	final static class InitialStatsPanel extends StatsPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -4382171376215021988L;

		InitialStatsPanel() {
			
			super();
			
			this.textFields[SPEED].setEditable(false);
			this.textFields[PHYSICAL_DAMAGE].setEditable(false);
			this.textFields[MAGIC].setEditable(false);
		}
		
		@Override
		public void update(Observable o, Object arg) {
			
			
			
		}
		
	}
	
	/**
	 * This class represents the final stats (at MAX_LEVEL) where the user can see the values
	 * 
	 * @author Only Brad
	 *
	 */
	final static class FinalStatsPanel extends StatsPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -710997824047600320L;
		
		public FinalStatsPanel() {
			
			for(int i=0;i<this.textFields.length;i++)
				
				this.textFields[i].setEditable(false);
		}
		
		@Override
		public void update(Observable o, Object arg) {
			
			int code = (Integer)arg;
			
			switch(code){
			
			/* if a field has been modified:
			 * then calculate the new stats */
			
			case GENDER:
			case JOB:
			case LEVEL: this.computeNewStats();
			
			}
			
		}

		private void computeNewStats() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
