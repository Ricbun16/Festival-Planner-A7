import java.util.*;
//Class in which artist is named
public class Artist {
	private String artistName;
	private ArrayList<String> genres;
	
	//Constructor for artist class which requires the artists name and an arraylist with genres
	public Artist(String name, ArrayList<String> genres){
		this.genres = genres;
		artistName = name;}
	
	//returns the name of the artist
	public String getName(){return artistName;}
	//changes the name of the artist
	public void setName(String name){	artistName = name;	}
	
	
	public ArrayList<String> getGenre(){return genres;	}
	
	//adds a genre to the arraylist. Input string
	public void addGenre(String genre)	{		genres.add(genre);	}
	
	//returns a string of all the genres seperated with ", "
	public String getGenresString()	{
		String genreList = "";
		for(String genre: genres)
		{
			genreList = genreList + ", " + genre;
		}
		return genreList;	}
	
	//delets the genre on spot i
	public void deleteGenre(int i)	{		genres.remove(i);	}

}
