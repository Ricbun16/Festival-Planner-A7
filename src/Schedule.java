import java.util.ArrayList;

public class Schedule {

	private ArrayList<Artist> artist;
	private ArrayList<Stage> stages;
	private String artistName;
	private int scheduleStartTime;
	private int scheduleStopTime;
	
	public Schedule(int startTime, int stopTime)
	{
		scheduleStartTime = startTime;
		scheduleStopTime = stopTime;
	}
	
	public void setArtists(String artistName){
		this.artistName = artistName;
	}
}
