import java.io.Serializable;
import java.util.ArrayList;

public class TimeSlot implements Serializable {

	int timeSlotStart = 0000;
	int timeSlotEnd = 0100;
	int popularity = 0;
	String genre = "No genre available";
	boolean isOccupied = false;
	//ArrayList<Artist> artists = new ArrayList<Artist>();
	Artist artist = new Artist("No artist available", new ArrayList<String>());
	
	public TimeSlot(int timeSlotStart, int timeSlotEnd, boolean isOccupied) {
		this.timeSlotStart = timeSlotStart;
		this.timeSlotEnd = timeSlotEnd;
		this.isOccupied = isOccupied;
	}
/*	
	// add an artist to arraylist with artists
	public void setArtist(Artist artist) {
		artists.add(artist);
	}
	
	// replace arraylist artists with new arraylist
	public void scheduleArtistsOnSlot(ArrayList<Artist> artists) {
		this.artists = artists;
	}
	
	// return arraylist with artists
	public ArrayList<Artist> getScheduledArtists() {
		return artists;
	}
*/
	// set the artist
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	// return the artist
	public Artist getArtist() {
		return artist;
	}
	// return boolean isOccupied
	public boolean checkIsOccupied() {
		return isOccupied;
	}
	
	// return starting time
	public int getTimeSlotStart() {
		return timeSlotStart;
	}
	
	// return ending time
	public int getTimeSlotEnd() {
		return timeSlotEnd;
	}
	
	// return populartity
	public int getPopularity() {
		return popularity;
	}
	
	// return the genre
	public String getGenre() {
		return genre;
	}
	
	// set the boolean isOccupied
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	public boolean getOccupied()
	{
		return isOccupied;
	}

	// set the integer popularity
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	
	// set the genre
	public void setGenre(String genre) {
		this.genre = genre;
	}
}
