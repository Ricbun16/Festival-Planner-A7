import java.util.ArrayList;

public class TimeSlot {

	int timeSlotStart = 0000;
	int timeSlotEnd = 0100;
	int popularity = 0;
	boolean isOccupied = false;
	ArrayList<Artist> artists = new ArrayList<artist>();
	
	public TimeSlot(int timeSlotStart, int timeSlotEnd, boolean isOccupied) {
		this.timeSlotStart = timeSlotStart;
		this.timeSlotEnd = timeSlotEnd;
		this.isOccupied = isOccupied;
	}
	
	public void scheduleArtistOnSlot(Artist artist) {
		artists.add(artist);
	}
	
	public void sceduleArtistsOnSlot(ArrayLsit<Artist> artists) {
		this.artists = artists;
	}
	
	public ArrayList<Artist> getsceduledAtists() {
		return artists;
	}
	
	public boolean checkIsOccupied() {
		return isOccupied;
	}
	
	public int getTimeSlotStart() {
		return timeSlotStart;
	}
	
	public int getTimeSlotEnd() {
		return timeSlotEnd;
	}
	
	public int getPopularity() {
		return popularity;
	}
	
	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
}
