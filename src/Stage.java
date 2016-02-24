import java.io.Serializable;
import java.util.ArrayList;

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
		
		while( timeSlotStart + timeSlotLength < stageStopTime)
		{
			timeSlotStop = timeSlotStart + timeSlotLength;
			TimeSlot timeSlot = new TimeSlot(timeSlotStart, timeSlotStop, false);
			timeSlots.add(timeSlot);
			timeSlotStart = timeSlotStop;
		}
	}

	public void scheduleArtist(int i, Artist artist) {
		TimeSlot currentSlot = timeSlots.get(i);
		if(currentSlot.getOccupied() == false){
		currentSlot.setOccupied(true);
		currentSlot.setArtist(artist);
		timeSlots.set(i, currentSlot);}
		else{System.out.println("TimeSlot is occupied");
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
