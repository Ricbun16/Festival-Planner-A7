import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Stage implements Serializable {
	private ArrayList<Artist> artist = new ArrayList<Artist>();
	private ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
	private int timeSlotLength;
	private String name;
	private int stageStartTime;
	private int stageStopTime;
	
	public Stage(String name, int startTime, int stopTime, int timeSlotLength)
	{
		stageStartTime = startTime;
		stageStopTime = stopTime;
		this.setTimeSlotLength(timeSlotLength);
		this.name = name;
		int timeSlotStart = stageStartTime;
		int timeSlotStop;
		timeSlots.add(new TimeSlot(0,0,false,name));
		while( timeSlotStart + timeSlotLength < stageStopTime)
		{
			String start = timeSlotStart+"";
			String subStart = start.substring(start.length()-2,start.length());
			System.out.println(subStart);
			if(Integer.parseInt(start.substring(start.length()-2,start.length()))>=60){
				timeSlotStart+=40;
			}
			timeSlotStop = timeSlotStart + timeSlotLength;
			String stop = timeSlotStop+"";
			String subStop = stop.substring(stop.length()-2,stop.length());
			System.out.println(subStop);
			if(Integer.parseInt(stop.substring(stop.length()-2,stop.length()))>=60){
				timeSlotStop+=40;
			}
			TimeSlot timeSlot = new TimeSlot(timeSlotStart, timeSlotStop, false,name);
			timeSlots.add(timeSlot);
			timeSlotStart = timeSlotStop;
		}
	}

	public void scheduleArtist(int i, Artist artist,int popularity) {
		TimeSlot currentSlot = timeSlots.get(i);
		if(currentSlot.getOccupied() == false){
		currentSlot.setOccupied(true);
		currentSlot.setArtist(artist);
		currentSlot.setPopularity(popularity);
		timeSlots.set(i, currentSlot);}
		else{JOptionPane.showMessageDialog(null, "Timeslot is occupied", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// Gets the stage name.
	public String getName()
	{
		return name;
	}

	public ArrayList<Artist> getArtist() {
		return artist;
	}

	public void setArtist(ArrayList<Artist> artist) {
		this.artist = artist;
	}

	public int getTimeSlotLength() {
		return timeSlotLength;
	}

	public void setTimeSlotLength(int timeSlotLength) {
		this.timeSlotLength = timeSlotLength;
	}
	
	public ArrayList<TimeSlot> getTimeSlots() {
		return timeSlots;
	}
	
	 public TimeSlot getTimeSlot(int i)
	 {
		 return timeSlots.get(i);
	 }

	public String toString()
	{
		return name;
	}
}
