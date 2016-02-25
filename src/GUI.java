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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

public class GUI extends JFrame{
	
	private Schedule schedule;
	private JPanel westBorder;
	private JPanel center;
	private JTable table;
	private TableModel tableModel = new TableModel();
	private ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
	private ArrayList<Artist> artists = new ArrayList<Artist>();
	private short state = 0, buttonState = 0;
 	private String title;
 	private JLabel titleLabel;
 	private int scheduleStart;
 	private int scheduleStop;
 	
	public static void main(String s[]){
		new GUI();
	}
	
	public GUI(){
		super("Agenda");
		setSize(1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		makeFrame();
		getScheduleTime();
		schedule = new Schedule(scheduleStart, scheduleStop);
		timeSlots = new ArrayList<TimeSlot>();
		this.artists = schedule.getArtist();
	}
public void getScheduleTime(){
		
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		
		int hoursStart;
		int hoursStop;
		
		Object[] message = {"Start Uur",field1,"Eind Uur:",field2,		};
		int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION)
		{
			
			hoursStart = Integer.parseInt(field1.getText());
			hoursStop = Integer.parseInt(field2.getText());
			if(hoursStart<= hoursStop){
			scheduleStart = hoursStart;
			scheduleStop = hoursStop;
			}
			else { System.out.println("Geen geledige schedule tijd");}
			
		}
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
        		addShow();
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
        
        JButton loadFile = new JButton("Load");
        loadFile.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		try {
				Schedule temp = Schedule.load();
				if(temp != null){
					schedule = temp;
					tableModel.refresh();
				}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
        	}
        });
        eastBorder.add(loadFile);
        
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
        
        center = new JPanel(new BorderLayout());
        JPanel centerNorth = new JPanel(new FlowLayout());
        //centerNorth.add(new JLabel("Artist & Genres"));
        
        
        table = new JTable(tableModel);
        center.add(new JScrollPane(table), BorderLayout.CENTER);
        
        
        switchState();
        
        centerNorth.add(titleLabel);
        centerNorth.setBorder(blackline);
        center.add(centerNorth, BorderLayout.NORTH);
        
        content.add(center, BorderLayout.CENTER);
        
        setContentPane(content);
        setVisible(true);
	}
	
	
	
	public void addStagePopUp(){
		
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();
		JTextField field4 = new JTextField();
		JTextField field5 = new JTextField();
		JTextField field6 = new JTextField();
		
		int hoursStart;
		int hoursStop;
		int minuteStart;
		int minuteStop;
		int timeSlotLength;
		String stageName;
		
		Object[] message = {"BeginTijd",field1,":",field2,
		"eindtijd",field3,":",field4,
		"Naam",field5,"TimeSlot Length :",field6
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION)
		{
			
			hoursStart = Integer.parseInt(field1.getText());
			minuteStart = Integer.parseInt(field2.getText());
			hoursStop = Integer.parseInt(field3.getText());
			minuteStop = Integer.parseInt(field4.getText());
			stageName = (String)field5.getText();
			timeSlotLength = Integer.parseInt(field6.getText());
			if(schedule.getScheduleStartTime() <= hoursStart && schedule.getScheduleStopTime() >= hoursStop && hoursStart < hoursStop){
			schedule.addStage(stageName, hoursStart*60+minuteStart,hoursStop*60+minuteStop, timeSlotLength);
			makeButton(stageName);}
			else { System.out.println("Stage valt buiten schedule");
			getScheduleTime();}
			
		}
		}
public void addShow(){
		
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();
		
		String artistName =null;
		String genre = null;
		int timeSlot = 0;
		
		Object[] message = {"Artist Name: ",field1,"Genre: " ,field2,
		"TimeSlot: ", field3
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION)
		{
			for(Artist testArtist: artists)
			{
				System.out.println(testArtist.getName());
			}
			
			try{
			artistName = field1.getText();
			genre = field2.getText();
			timeSlot = Integer.parseInt(field3.getText());}
			catch(NumberFormatException e){}
			ArrayList<Stage> stages = new ArrayList<Stage>();
			stages = schedule.getStages();
			try{
			Stage currentStage = stages.get(state-1);
			Artist artist = null;
			boolean artistSet = false;
			for(Artist currentArtist: artists)
			{
				if(currentArtist.getName().equals( artistName))
				{
					artist = currentArtist;
					artistSet = true;
				}
			}
			if(schedule.checkDoubleBooking(artist, timeSlot, currentStage) == false){
			if(artistSet){
				
				try{
			currentStage.scheduleArtist(timeSlot, artist);
			fillTimeslots(currentStage);
			}
				
			catch(Exception e){}}
			else
			{
				artist = new Artist(artistName, genre);
				artists.add(artist);
				currentStage.scheduleArtist(timeSlot, artist);
				fillTimeslots(currentStage);
				schedule.setArtists(artists);
			}
			}
			else
			{
				System.out.println("De artiest is dubbel geboekt");
			}
			}
			catch(IndexOutOfBoundsException e)
			{
				
			}
				center.remove(table);
				tableModel.refresh();
				table = new JTable(tableModel);
		        center.add(new JScrollPane(table), BorderLayout.CENTER);
		        center.repaint();
		        revalidate();
		        repaint();

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
						fillTimeslots(stages.get(0));
					break;
				case 2:	titleLabel.setText(stages.get(1).getName());
						fillTimeslots(stages.get(1));
					break;
				case 3:	titleLabel.setText(stages.get(2).getName());
						fillTimeslots(stages.get(2));
					break;
			}
		} catch(NullPointerException e) {
			titleLabel = new JLabel("Artist & Genres");
		}
		center.remove(table);
		tableModel.refresh();
		table = new JTable(tableModel);
        center.add(new JScrollPane(table), BorderLayout.CENTER);
        center.repaint();
        
        revalidate();
        repaint();
	}
	
	private class ButtonListener implements ActionListener{
		private short buttonState;
		
		public ButtonListener(short buttonState){
			this.buttonState = buttonState;
		}
		
		public void actionPerformed(ActionEvent e){
			state = buttonState;
			switchState();
		}
	}
		public void fillTimeslots(Stage currentStage){
		timeSlots.clear();
		ArrayList<TimeSlot> tempList = currentStage.getTimeSlots();
		for(int i = 0;i<tempList.size();i++){
			if(tempList.get(i).checkIsOccupied())
				timeSlots.add(tempList.get(i));
		}
	}
	
	public class TableModel extends AbstractTableModel{
	
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}
	
		
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			if(timeSlots.size()!=0)
			return timeSlots.size();
			else 
			return 0;
		}
		
	
		
		public Object getValueAt(int row, int column) {
			TimeSlot t = null;
			if(timeSlots != null && timeSlots.size()>row)
				t = timeSlots.get(row);
			else
				return "";
				
			switch(column){
			case 0: return  t.getArtist().getName();
			case 1: return  t.getArtist().getGenre();
			case 2: return  t.getTimeSlotStart();
			case 3: return  t.getTimeSlotEnd();
			case 4: return  t.getPopularity();
			}
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
		
		public void refresh(){
			fireTableDataChanged();
		}
			
	}

}
