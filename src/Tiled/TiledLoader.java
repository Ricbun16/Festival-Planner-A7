package Tiled;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Agenda.Schedule;
import Agenda.Stage;

public class TiledLoader {
	private File file;
	private ArrayList<TiledTileset> tilesets;
	private ArrayList<TiledLayer> tilelayers;
	private ArrayList<BufferedImage> tilesetTiles;
	private ArrayList<TiledObject> objectTargets;
	private ArrayList<Target> targets = new ArrayList<Target>();
	private Graphics2D g2;
	private TiledLayer collisionLayer;

	
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
				JSONObject jLayer = (JSONObject) JSONLayers.get(i);
				if(jLayer.get("type").equals("tilelayer"))
				{
					tilelayers.add(new TiledLayer(jLayer));
					if(tilelayers.get(i).getName().equals("Colission"))
						collisionLayer = tilelayers.get(i);
				}
				else
				{
					// Get the targets and adds them to an ArrayList
					JSONArray JSONObjects = (JSONArray) jLayer.get("objects");
					objectTargets = new ArrayList<TiledObject>();
					for(int ii = 0; ii< JSONObjects.size(); ii++){
						objectTargets.add(new TiledObject((JSONObject) JSONObjects.get(ii)));
					}
				}
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
	
	public TiledLayer getColLayer(){
		return collisionLayer;
	}
	
	public void createLayers(Schedule schedule) {
		ArrayList<Stage> stages =  schedule.getStages();

		for (int i = 0; i < 9; i++) {
			if(i == 0 || i == 3 || i == 6 || i == 7)
				continue;
			tilelayers.get(i).setVisible(false);
		}
		for(int i = 0; i <  stages.size(); i++) {

			for(int ii = 0; ii < tilelayers.size(); ii++) {
				System.out.println(stages.get(i).getName());
				System.out.println(tilelayers.get(ii).getName());
				if(stages.get(i).getName().equals(tilelayers.get(ii).getName()))
					tilelayers.get(ii).setVisible(true);
			}
		}

		for(int i = 0; i < tilelayers.size(); i++) {
			long numberID = 0;
			TiledTileset ts = new TiledTileset();
			for(TiledLayer tl : tilelayers) {
				for(Long idnumber : tl.getData()) {
					if (idnumber != 0)
						numberID = idnumber;
				}
			}
			
			for(int q = tilesets.size() -1; q >= 0; q--) {
				if(numberID > tilesets.get(q).getFirstgid()) {
					ts = tilesets.get(q);
				}
			}

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
		createTargets();
	}
	public void createTargets() {

		for(TiledObject temp : objectTargets) {
			Target tempTarget =  new Target(temp);
			tempTarget.build(collisionLayer);
			targets.add(tempTarget);
		}
	}
	
	public ArrayList<TiledLayer> getTiledLayers() {
		return tilelayers;
	}
	
	public ArrayList<Target> getTargets() {
		return targets;
	}
}
