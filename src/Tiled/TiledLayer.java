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

	ArrayList<Integer> data;
	JSONObject layer;
	JSONArray JSONData;
	String name;
	String type;
	int width, height;
	int x, y;
	double opacity;
	boolean visible;

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
			data = (ArrayList<Integer>) JSONData;
//			System.out.println(opacity);
//			System.out.println(JSONData);
//			System.out.println(data);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getData() {
		return data;
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
	
}
