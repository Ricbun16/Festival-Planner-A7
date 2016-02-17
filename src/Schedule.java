import java.util.ArrayList;

public class Schedule {

	private ArrayList<Artist> artists;
	private ArrayList<Stage> stages;
	private String artistName;
	private int scheduleStartTime;
	private int scheduleStopTime;
	
	public Schedule(int startTime, int stopTime)
	{
		stages = new ArrayList<Stage>();
		artists = new ArrayList<Artist>();
		setScheduleStartTime(startTime);
		setScheduleStopTime(stopTime);
	}
	
	public void addStage(String stageName, int stageStartTime, int stageStopTime, int timeSlotLentgh){
		Stage stage = new Stage(stageName, stageStartTime, stageStopTime, timeSlotLentgh);
		stages.add(stage);
	}
	
	public void setArtists(String artistName) {
		this.setArtistName(artistName);
	}

	public ArrayList<Artist> getArtist() {
		return artists;
	}

	public void setArtist(ArrayList<Artist> artist) {
		this.artists = artist;
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
