import java.util.ArrayList;

import javax.annotation.processing.FilerException;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.FileSystemNotFoundException;

public class Schedule implements Serializable{

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
	
	public void setArtist(String artistName) {
		this.setArtistName(artistName);
	}

	public ArrayList<Artist> getArtist() {
		return artists;
	}

	public void setArtists(ArrayList<Artist> artist) {
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
	
	public void saveSchedule()throws FileSystemNotFoundException{
		try {
			FileOutputStream fos = new FileOutputStream("agenda");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
