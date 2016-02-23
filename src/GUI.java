import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

public class GUI extends JFrame{
	
	private Schedule schedule;
	private JPanel westBorder;
	private ArrayList<TimeSlot> timeSlots;
	private short state = 0, buttonState = 0;
 	private String title;
 	private JLabel titleLabel;
	
	public static void main(String s[]){
		new GUI();
	}
	
	public GUI(){
		super("Agenda");
		setSize(1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		makeFrame();
		schedule = new Schedule(600, 1200);
		timeSlots = new ArrayList<TimeSlot>();
	}
	
	private void makeFrame(){
		
		JPanel content = new JPanel(new BorderLayout());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        
        westBorder = new JPanel();
        westBorder.setBorder(blackline);
        westBorder.setLayout(new BoxLayout(westBorder, BoxLayout.Y_AXIS));
        westBorder.add(new JLabel(" "));
        JButton artistAndGenre = new JButton("Artist & Genre");
        artistAndGenre.setSize(100, 200);
        artistAndGenre.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		state = 0;
         		switchState();
          	}
        }); 
        
        westBorder.add(artistAndGenre);      
        content.add(westBorder, BorderLayout.WEST);
        
        JPanel eastBorder = new JPanel();
        eastBorder.setBorder(blackline);
        eastBorder.setLayout(new BoxLayout(eastBorder, BoxLayout.Y_AXIS));
        eastBorder.add(new JLabel(" "));
        JButton addStage = new JButton("Add stage");
        addStage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		addStagePopUp();
          	}
        });
        eastBorder.add(addStage);    
        
        JButton addPerformance = new JButton("Add show");
        addPerformance.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		
          	}
        });
        eastBorder.add(addPerformance);
        
        JButton saveFile = new JButton("Save");
        saveFile.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
					schedule.saveSchedule();
        	}
        });
        eastBorder.add(saveFile);
        
        content.add(eastBorder, BorderLayout.EAST);
        
        JPanel northBorder = new JPanel(new FlowLayout());
        JLabel naamLable = new JLabel("Festival Naam - 16-02-2016");
        northBorder.setBorder(blackline);
        northBorder.add(naamLable);
        content.add(northBorder, BorderLayout.NORTH);
        
        JPanel southBorder = new JPanel();
        southBorder.setBorder(blackline);
        southBorder.add(new JLabel(" "));
        content.add(southBorder, BorderLayout.SOUTH);
        
        JPanel center = new JPanel(new BorderLayout());
        JPanel centerNorth = new JPanel(new FlowLayout());
        //centerNorth.add(new JLabel("Artist & Genres"));
        switchState();
        centerNorth.add(titleLabel);
        centerNorth.setBorder(blackline);
        center.add(centerNorth, BorderLayout.NORTH);
        
        center.add(new JScrollPane(new JTable(new TableModel())), BorderLayout.CENTER);
        
        content.add(center, BorderLayout.CENTER);
        
        setContentPane(content);
        setVisible(true);
	}
	
	public void addStagePopUp(){
		Object[] possibilities = null;
		String s = (String)JOptionPane.showInputDialog(
		                    null,
		                    "Please name the stage",
		                    "Add stage",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "Stage name");

		//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			schedule.addStage(s, 600, 1200, 60);
		    makeButton(s);
		    return;
		}
	}
	
	public void makeButton(String name) {
	 
	JButton button = new JButton(name);
      	buttonState++;
 	button.addActionListener(new ButtonListener(buttonState));
 	westBorder.add(button);
 	revalidate();
 	}
	
	public void switchState(){
		try{
			ArrayList<Stage> stages = schedule.getStages();
			switch(state){
				case 0: titleLabel.setText("Artist & Genres");
					break;
				case 1: titleLabel.setText(stages.get(0).getName());
					break;
				case 2:	titleLabel.setText(stages.get(1).getName());
					break;
				case 3:	titleLabel.setText(stages.get(2).getName());
					break;
			}
		} catch(NullPointerException e) {
			titleLabel = new JLabel("Artist & Genres");
		}
	}
	
	private class ButtonListener implements ActionListener{
		private short buttonState;
		
		public buttonListener(short buttonSate){
			this.buttonState = buttonState;
		}
		
		public void actionPerfomred(ActionEvent e){
			state = buttonState;
			switchState();
		}
	}
//		public void fillTimeslots(Stage currentStage){
//		timeSlots.clear();
//		ArrayList<TimeSlot> tempList = currentStage.getTimeSlots();
//		for(int i = 0;i<tempList.size();i++){
//			if(!tempList.get(i).getGebruik)
//				timeSlot.add(tempList.get(i));
//		}
//	}
	
	public class TableModel extends AbstractTableModel{
	
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}
	
		
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 4;
		}
	
		
		public Object getValueAt(int row, int column) {
			/*TimeSlot T = timeSlots.get(row + 1);
			switch(column){
			case 0: return  T.getArtist();
			case 1: return  T.getArtist().getGenre();
			case 2: return  T.getTimeSlotStart();
			case 3: return  T.getTimeSlotEnd();
			case 4: return  T.getPopularity();
			}*/
			return "";
		}
		
		public boolean isCellEditable(int row, int column){
			return false;
		}
		
		public String getColumnName(int column){
			switch(column){
			case 0: return "Artiest";
			case 1: return "Genre";
			case 2: return "Start";
			case 3: return "End";
			case 4: return "Popularity";
			}
			return"";
		}
			
	}

}
