package Tiled;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class TiledTileset {

	private int columns;
	private int firstgid;
	private String image;
	private int imageHeight;
	private int imageWidth;
	private String tName;
	private int tileHeight;
	private int tileWidth;	
	private BufferedImage bImage;
	private BufferedImage[] tilesetTiles;
	private JSONObject tileset;
	private int tilesAmount;
	private String path;
	
	public TiledTileset(JSONObject tileset) {
		path = "JSON/LPC Base Assets/tiles/";

		try {
			loadTileset(tileset);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public TiledTileset(){
		
	}
	
	public void loadTileset(JSONObject tileSet) throws FileNotFoundException, IOException, ParseException {
		try{
				// take all needed information from a tileset.
				this.tileset = tileset;
				columns =((Long) tileSet.get("columns")).intValue();
				firstgid = ((Long) tileSet.get("firstgid")).intValue();
				image = (String) tileSet.get("image");
				imageHeight = ((Long) tileSet.get("imageheight")).intValue();
				imageWidth = ((Long) tileSet.get("imagewidth")).intValue();
				tName = (String) tileSet.get("name");
				tileHeight = ((Long) tileSet.get("tileheight")).intValue();
				tileWidth = ((Long) tileSet.get("tilewidth")).intValue();
				
				bImage = ImageIO.read(new File(path + image));
				tilesAmount = (imageHeight/tileHeight) * (imageWidth/tileWidth);
				tilesetTiles = new BufferedImage[tilesAmount];
				
				// takes tileset image and split it into pieces wich ar ethen put in an array.
				int lastPosition = 0;
				for(int y = 0; y < (imageHeight/tileHeight) ;y++) {
					for(int x = 0; x < (imageWidth/tileWidth) ; x++) {
						tilesetTiles[(/*firstgid + */lastPosition)] = bImage.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
						lastPosition++;
					}
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getColumns() {
		return columns;
	}

	public int getFirstgid() {
		return firstgid;
	}

	public String getImage() {
		return image;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getTilesAmount() {
		return tilesAmount;
	}
	
	public String gettName() {
		return tName;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public BufferedImage[] getTilesetTiles() {
		return tilesetTiles;
	}

	public JSONObject getTileset() {
		return tileset;
	}
}
