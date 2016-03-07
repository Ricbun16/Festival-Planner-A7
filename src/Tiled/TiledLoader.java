package Tiled;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TiledLoader {
	private File file;
	private ArrayList<TiledTileset> tilesets;
	private ArrayList<TiledLayer> tilelayers;
	private ArrayList<BufferedImage> tilesetTiles;
	
	public TiledLoader(){
		
	}
	public TiledLoader(File file){
		this.file = file;
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
		tilesetTiles =  new ArrayList<BufferedImage>();
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(new FileReader(file));
			JSONObject jObj = (JSONObject) obj;

			// get all tilelayers and put them in an arraylist.
			JSONArray JSONLayers = (JSONArray)jObj.get("layers");
			tilelayers = new ArrayList<TiledLayer>();
			for(int i = 0; i < JSONLayers.size(); i++) {
				tilelayers.add(new TiledLayer((JSONObject) JSONLayers.get(i)));
			}

			// get all the tilesets
			JSONArray JSONTilesets = (JSONArray) jObj.get("tilesets");
			tilesets = new ArrayList<TiledTileset>();
			for(int i = 0; i < JSONTilesets.size(); i++) {
				tilesets.add(new TiledTileset((JSONObject) JSONTilesets.get(i)));
//				tilesets.add(i, new TiledTileset((JSONObject) JSONTilesets.get(i)));
			}
			
			// set first thing in the arraylist to nothing so the indexs are teh samen as the id numbers
			tilesetTiles.add(null);
			
			// put all the tileset tiles into one arraylist
			for(int i = 0; i < tilesets.size(); i++) {
				BufferedImage[] tempBImage =  tilesets.get(i).getTilesetTiles();
				for(int j = 0; j < tilesets.get(i).getTilesAmount(); j++) {
//					System.out.println((tilesets.get(i).getFirstgid() + j));
					tilesetTiles.add((tilesets.get(i).getFirstgid() + j), tempBImage[j]);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
				
		}
		
	}
	
	public ArrayList<TiledLayer> getTiledLayers() {
		return tilelayers;
	}
	
	public TiledLayer getTiledLayer(String name) {
		for(TiledLayer tLayer : tilelayers) {
			if(tLayer.getName() == name) {
				return tLayer;
			}
		}
		return null;
	}
	
	public ArrayList<BufferedImage> getTilesetTiles() {
		return tilesetTiles;
	}
}
