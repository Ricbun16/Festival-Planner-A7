import java.io.Serializable;
import java.util.ArrayList;

public class TimeSlot implements Serializable {

	int timeSlotStart;
	int timeSlotEnd;
	int popularity = 0;
	String genre = "No genre available";
	String stageName;
	boolean isOccupied = false;
	//ArrayList<Artist> artists = new ArrayList<Artist>();
	Artist artist = new Artist("No artist available", new ArrayList<String>());
	
	public TimeSlot(int timeSlotStart, int timeSlotEnd, boolean isOccupied,String stageName) {
		this.timeSlotStart = timeSlotStart;
		this.timeSlotEnd = timeSlotEnd;
		this.isOccupied = isOccupied;
		this.stageName = stageName;
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
	
	public String getStageName(){
		return stageName;
	}
	
	// return starting time
	public int getTimeSlotStart() {
		return timeSlotStart;
	}
	
	// return ending time
	public int getTimeSlotEnd() {
		return timeSlotEnd;
	}
	
	public String getTimeSlotStartString(){
		String start = timeSlotStart+"";
		if(start.length()<4){
			return 0 + start;
		}else{
			return start;
		}
	}
	
	public String getTimeSlotEndString(){
		String end = timeSlotEnd+"";
		if(end.length()<4){
			return 0 + end;
		}else{
			return end;
		}
	}
	
	public String timeSlotStartToString(){
		
		String hours = getTimeSlotStartString().substring(0,2);
		String minutes = getTimeSlotStartString().substring(2,4);
		return hours+":"+minutes;
		}
	
	public String timeSlotEndToString(){
		String hours = getTimeSlotEndString().substring(0,2);
		String minutes = getTimeSlotEndString().substring(2,4);
		return hours+":"+minutes;
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
