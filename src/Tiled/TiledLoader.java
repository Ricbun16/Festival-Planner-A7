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
	private Graphics2D g2;

	
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
	
	public void draw(Graphics2D g){
		for(int i = 0; i < tilelayers.size(); i++)
		{
			if(tilelayers.get(i).isVisible()) 
				g.drawImage(tilelayers.get(i).layerImage, 0, 0, null);
		}
		
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
	
	public void createLayers() {
		for(int i = 0; i < tilelayers.size(); i++) {
			System.out.println(tilelayers.size());
			System.out.println(tilesets.size());
			long numberID = 0;
			TiledTileset ts = new TiledTileset();
			for(TiledLayer tl : tilelayers) {
				for(Long idnumber : tl.getData()) {
					if (idnumber != 0)
						numberID = idnumber;
				}
			}
			System.out.println("numberID\t" + numberID);
			for(int q = tilesets.size() -1; q >= 0; q--) {
				if(numberID > tilesets.get(q).getFirstgid()) {
					ts = tilesets.get(q);
				}
			}
//			for(TiledTileset ts : tilesets) {
//				if( ts.getFirstgid() >)
//			}
			BufferedImage bI = new BufferedImage(tilelayers.get(i).getWidth() * ts.getTileWidth(), tilelayers.get(i).getHeight() * ts.getTileHeight(), BufferedImage.TYPE_INT_ARGB);
			tilelayers.get(i).layerImage = bI;
			g2 = bI.createGraphics();
			ArrayList<Long> data = tilelayers.get(i).getData();
			int lastPosition = 0;
			for(int y = 0; y < tilelayers.get(i).getHeight(); y++) {
				
				for(int x = 0; x < tilelayers.get(i).getWidth(); x++) {
					int number = data.get(lastPosition).intValue();
					

					g2.drawImage(tilesetTiles.get(number),x *  ts.getTileWidth(),y * ts.getTileHeight(), ts.getTileWidth(), ts.getTileHeight(), null);
					lastPosition++;
				}
			}
		}
	}
	
	public ArrayList<TiledLayer> getTiledLayers() {
		return tilelayers;
	}
}
