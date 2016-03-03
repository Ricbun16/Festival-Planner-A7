package Tiled;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.json.simple.parser.ParseException;

public class TiledMap {
	//TiledLoader tLoader = new TiledLoader();
	TiledLayer tLayer = new TiledLayer();
	
	File file = new File("TestJSON.json");
	
	public static void main(String[] args){
		new TiledMap();
	}
	
	public TiledMap(){
		JFrame frame = new JFrame("Tiled");
		
		frame.setSize(840, 480);
		frame.setVisible(true);
	}
	
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

	}

}
