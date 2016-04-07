package Agenda;
import java.io.Serializable;
import java.util.*;
//Class in which artist is named
public class Artist implements Serializable{
	private String artistName;
	private ArrayList<String> genres;
	private String genre;
	private ArrayList <Integer> occupiedTimeSlots;

	
	public Artist(String name, ArrayList<String> genres)
	{
		this.genres = genres;
		artistName = name;
		this.occupiedTimeSlots = null;
	}
	
	public ArrayList<Integer> getOccupiedTimeSlots()
	{
		return occupiedTimeSlots;
	}
	
	public void addTimeSlot(int slot)
	{
		occupiedTimeSlots.add(slot);
	}
	
	public Artist(String name, String genre)
	{
		this.genre = genre;
		artistName = name;
	}
	
	public String getName()
	{
		return artistName;
	}
	
	public void setName(String name)
	{
		artistName = name;
	}
	
	public String getGenre()
	{
		return genre;
	}
	
	public void addGenre(String genre)
	{
		genres.add(genre);
	}
	
	public String getGenresString()
	{
		String genreList = "";
		for(String genre: genres)
		{
			genreList = genreList + ", " + genre;
		}
		return genreList;
	}
	
	public void deleteGenre(int i)
	{
		genres.remove(i);
	}

}
