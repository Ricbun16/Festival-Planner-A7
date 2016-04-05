package Tiled;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class TiledLayer {

	ArrayList<Long> data;
	Long[][] data2D;
	JSONObject layer;
	JSONArray JSONData;
	String name;
	String type;
	private int width, height;
	private int x, y;
	double opacity;
	boolean visible;
	BufferedImage layerImage;

	public TiledLayer(JSONObject tilelayer) {
		try {
			loadTilelayer(tilelayer);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	public void loadTilelayer(JSONObject layer) throws FileNotFoundException, IOException, ParseException {
		
		try {
			// take all needed information from a tilelayer.
			this.layer = layer;
			JSONData = (JSONArray) layer.get("data");
			
			name = (String) layer.get("name");
			type = (String) layer.get("type");
			opacity = ((Long) layer.get("opacity")).doubleValue();
			visible = (boolean) layer.get("visible");
			width = ((Long)layer.get("width")).intValue();
			height = ((Long)layer.get("height")).intValue();
			x = ((Long)layer.get("x")).intValue();
			y = ((Long)layer.get("y")).intValue();
			data = (ArrayList<Long>) JSONData;
			data2D = new Long[(int) Math.sqrt(data.size())][(int) Math.sqrt(data.size())];
//			System.out.println(data);
//			System.out.println(opacity);
//			System.out.println(JSONData);
//			System.out.println(data);
			int i = 0;
			for(int y =0; y < height; y ++) {
				for(int x = 0; x< width ; x++) {
					data2D[x][y] = data.get(i);
					i++;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Long> getData() {
		return data;
	}
	
	public Integer getData2DPoint(int x, int y) {
		return data2D[x][y].intValue();
	}

	public JSONObject getLayer() {
		return layer;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getOpacity() {
		return opacity;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
