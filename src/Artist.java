import java.util.*;
//Class in which artist is named
public class Artist {
	private String artistName;
	private ArrayList<String> genres;
	
	public Artist(String name, ArrayList<String> genres)
	{
		this.genres = genres;
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
	
	public ArrayList getGenre()
	{
		return genres;
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
