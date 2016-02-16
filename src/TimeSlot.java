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
	
	// add new artist
	public void scheduleArtistOnSlot(Artist artist) {
		artists.add(artist);
	}
	
	// replace arraylist artists with new arraylist 
	public void sceduleArtistsOnSlot(ArrayLsit<Artist> artists) {
		this.artists = artists;
	}
	
	// return the arraylist artists
	public ArrayList<Artist> getsceduledAtists() {
		return artists;
	}
	
	// return the boolean isOccupied
	public boolean checkIsOccupied() {
		return isOccupied;
	}
	
	// return the start time
	public int getTimeSlotStart() {
		return timeSlotStart;
	}
	
	// return the ending time
	public int getTimeSlotEnd() {
		return timeSlotEnd;
	}
	
	// return the popularity
	public int getPopularity() {
		return popularity;
	}
	
	// set the boolean isOccupied
	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	// set the integer popularity
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
}