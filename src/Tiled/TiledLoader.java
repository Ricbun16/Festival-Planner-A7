package Tiled;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TiledLoader {
	private File file;

	protected JSONObject layer, tileSet;				
	protected JSONArray data;
	protected String name;
	protected String type;
	protected boolean visible;
	protected int width, height;
	protected int x, y;
	protected int columns;
	protected int firstgid;
	protected String image, tName;
	protected int imageHeight, imageWidth;
	public TiledLoader(){
		
	}
	public TiledLoader(File file){
		try {
			loadFile(file);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

	}
	
	public void loadFile(File file) throws FileNotFoundException, IOException, ParseException{
//		file = this.file;
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(new FileReader(file));
			JSONObject jObj = (JSONObject) obj;
			

			
			JSONArray layers = (JSONArray)jObj.get("layers");
			for(int i = 0; i < layers.size(); i++)
			{
				layer = (JSONObject)layers.get(i);				
				data = (JSONArray) layer.get("data");
				name = (String) layer.get("name");
				type = (String) layer.get("type");
				visible = (boolean) layer.get("visible");
				width = ((Long)layer.get("width")).intValue();
				height = ((Long)layer.get("height")).intValue();
				x = ((Long)layer.get("x")).intValue();
				y = ((Long)layer.get("y")).intValue();

//				System.out.println(data);
//				System.out.println(name);
//				System.out.println(type);
//				System.out.println(visible);
//				System.out.println(x);
//				System.out.println(y);
			}
			
			int tileWidth = ((Long)jObj.get("tilewidth")).intValue();
			int tileHeight = ((Long)jObj.get("tileheight")).intValue();
			
			JSONArray tilesets = (JSONArray) jObj.get("tilesets");
			for(int i = 0; i < tilesets.size(); i++)
			{
				tileSet = (JSONObject) tilesets.get(i);
				columns =((Long) tileSet.get("columns")).intValue();
				firstgid = ((Long) tileSet.get("firstgid")).intValue();
				image = (String) tileSet.get("image");
				imageHeight = ((Long) tileSet.get("imageheight")).intValue();
				imageWidth = ((Long) tileSet.get("imagewidth")).intValue();
				tName = (String) tileSet.get("name");
			}
		}
		finally{
				
		}
		
	}
}
