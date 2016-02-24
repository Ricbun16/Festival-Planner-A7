import java.util.ArrayList;

import javax.annotation.processing.FilerException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystemNotFoundException;

public class Schedule implements Serializable{

	private ArrayList<Artist> artists;
	private ArrayList<Stage> stages;
	private String artistName;
	private int scheduleStartTime;
	private int scheduleStopTime;
	
	public Schedule()
	{
		
	}
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
	
	public Schedule loadSchedule()throws FileNotFoundException{
		try{
			FileInputStream fis = new FileInputStream("agenda");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Object object = ois.readObject();
			ois.close();
			if(object instanceof Schedule){
				System.out.println("Agenda has loaded.");
				Schedule temp = new Schedule();
//				artists = temp.getArtist();
				stages = temp.getStages();
				System.out.println("Stage:" + stages);
			return (Schedule) object;
			}
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
