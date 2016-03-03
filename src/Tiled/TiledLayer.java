package Tiled;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class TiledLayer {
	TiledLoader tLoader = new TiledLoader();
	
	File file = new File("TestJSON.json");
	
	ArrayList<Object> objects;
	ArrayList<Integer> data;
	String name;
	String type;
	int width, height;
	int x, y;
	boolean visible;
	
	public TiledLayer(){
		try {
			tLoader.loadFile(file);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g){
		BufferedImage bImage = null;
		Graphics2D g2 = (Graphics2D) g;
		bImage = new BufferedImage(tLoader.width, tLoader.height, BufferedImage.TYPE_INT_ARGB);
		for(int y = 0; y < tLoader.height ;y++)
		{
			for(int x = 0; y < tLoader.width ; x++)
			{
//				bImage.getSubimage(x, y, tLoader., h);
			}
		}
//		g2.drawImage(img, x, y, width, height, BufferedImage.TYPE_INT_ARGB)
	}
}
