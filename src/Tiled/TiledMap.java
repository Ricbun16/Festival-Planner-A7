package Tiled;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

public class TiledMap {
	TiledLoader tLoader;
//	TiledLayer tLayer = new TiledLayer();
	
	File file = new File("TestJSON2.json");
	
	public static void main(String[] args){
		new TiledMap();
	}
	
	public TiledMap(){
		tLoader = new TiledLoader(file);
		JFrame frame = new JFrame("Tiled");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new TiledMapDraw();
		
		frame.setContentPane(panel);
		frame.setSize(840, 480);
		frame.setVisible(true);
	}
	
//	public void draw(Graphics g){
//		Graphics2D g2 = (Graphics2D) g;
//		tLayer.draw(g);
//		tLayer.test();
//	}

}
class TiledMapDraw extends JPanel {
	public TiledMapDraw() {
		setPreferredSize(new Dimension(840, 480));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		/**
		 * Voor zover dat ik weet moet het ongeveer op deze manier.
		 * Hierin of in de TIledLoader moet met de volgende regel code een grote afbeelding gemaakt worden.
		 * bI = new BufferedImage(tLoader.width, tLoader.height, BufferedImage.TYPE_INT_ARGB);
		 * dan kan je bI.creatGraphics(); doen en krijg je een grafics2D terug.
		 * en daar kan je alle kleine stukjes in zetten.
		 */
		
		g2.drawLine(10, 10, 210, 10);
	}
}
