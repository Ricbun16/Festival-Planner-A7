import java.util.ArrayList;

public class Stage {
	private ArrayList<Artist> artist = new ArrayList<Artist>;
	private ArrayList<Integer> timeSlot = new ArrayList<Integer>;
	private int TimeSlotLength;
	private String location;
	private String stageName;
	
	public Stage()
	{
		
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
