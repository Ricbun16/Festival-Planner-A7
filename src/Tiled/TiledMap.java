package Tiled;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TiledMap  extends JPanel{
	TiledLoader tLoader;
	AffineTransform cameraTransform;
	int oldX, oldY;
	int newX, newY;
	private Point mousePoint;
	
//	File file = new File("JSON/TestJSON3.json");
	File file = new File("JSON/event.json");
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Tiled");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new TiledMap();
		frame.setContentPane(panel);
		frame.setSize(840, 480);
		frame.setVisible(true);
		
	}
	
	public TiledMap(){
		cameraTransform = new AffineTransform();
		tLoader = new TiledLoader(file);
		tLoader.createLayers();
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me){
				mousePoint = me.getPoint();
				//System.out.println(oldX);
				oldX = me.getX();
				oldY = me.getY();
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent md){
				newX = md.getX();
				newY = md.getY();
				cameraTransform.translate(newX - oldX, newY - oldY);
				oldX = newX;
				oldY = newY;
				repaint();
			}
		});
		
		addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent mwe){
				int wheelRotation = mwe.getWheelRotation();
				double scale = 1 - mwe.getWheelRotation() * 0.1;
				cameraTransform.scale(scale, scale);
				repaint();
			}	
		});
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//cameraTransform.translate(500, 100);
		g2.setTransform(cameraTransform);
		
		
		tLoader.draw(g2);
		
//		g2.drawLine(10, 10, 210, 10);
	}
}
