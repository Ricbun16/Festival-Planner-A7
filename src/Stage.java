import java.util.ArrayList;

public class Stage {
	private ArrayList<Artist> artist = new ArrayList<Artist>();
	private ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
	private int timeSlotLength;
	private String location;
	private String stageName;
	private int stageStartTime;
	private int stageStopTime;
	
	public Stage(int startTime, int stopTime, int timeSlotLength, String location)
	{
		this.location = location;
		stageStartTime = startTime;
		stageStopTime = stopTime;
		this.timeSlotLength = timeSlotLength;
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
		currentSlot.setOccupied(true);
		currentSlot.setArtist(artist);
		timeSlots.set(i, currentSlot);
		
	}
	
	// Sets the location of the stage.
	public void setLocation(String location)
	{
		this.location = location;
	}
	// Gets the location of the stage.
	public String getLocation()
	{
		return location;
	}
	
	// Sets the stage name.
	public void setStageName(String stageName)
	{
		this.stageName = stageName;
	}
	// Gets the stage name.
	public String getStageName()
	{
		return stageName;
	}

}
