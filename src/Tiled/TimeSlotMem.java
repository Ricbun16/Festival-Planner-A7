package Tiled;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import Agenda.TimeSlot;

public class TimeSlotMem {
	
	private ArrayList<TimeSlot> lijst;
	private ArrayList<Point2D> locations;
	private int time;

	public TimeSlotMem(int time, ArrayList<Point2D> locations) {
		lijst = new ArrayList<TimeSlot>();
		this.locations = new ArrayList<Point2D>(locations);
		this.setTime(time);
	}

	public ArrayList<TimeSlot> getLijst() {
		return lijst;
	}
	
	public ArrayList<Point2D> getLocations() {
		return locations;
	}

	public void setLijst(ArrayList<TimeSlot> lijst) {
		this.lijst = lijst;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void addTimeslot(TimeSlot ts) {
		lijst.add(ts);
	}
	
}
