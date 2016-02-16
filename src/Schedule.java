import java.util.ArrayList;

public class Schedule {

	private ArrayList<Artist> artist;
	private ArrayList<Stage> stages;
	private String artistName;
	private int scheduleStartTime;
	private int scheduleStopTime;
	
	public Schedule(int startTime, int stopTime)
	{
		setScheduleStartTime(startTime);
		setScheduleStopTime(stopTime);
	}
	
	public void setArtists(String artistName){
		this.setArtistName(artistName);
	}

	public ArrayList<Artist> getArtist() {
		return artist;
	}

	public void setArtist(ArrayList<Artist> artist) {
		this.artist = artist;
	}

	public ArrayList<Stage> getStages() {
		return stages;
	}

	public void setStages(ArrayList<Stage> stages) {
		this.stages = stages;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public int getScheduleStartTime() {
		return scheduleStartTime;
	}

	public void setScheduleStartTime(int scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}

	public int getScheduleStopTime() {
		return scheduleStopTime;
	}

	public void setScheduleStopTime(int scheduleStopTime) {
		this.scheduleStopTime = scheduleStopTime;
	}
}
