package Tiled;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TiledMap  extends JPanel implements ActionListener{
	TiledLoader tLoader;
	AffineTransform cameraTransform;
	int oldX, oldY;
	int newX, newY;
	private Point mousePoint;
	private ArrayList<Visitor> visitors;
	
	File file = new File("JSON/event.json");
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Simulator");
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
		visitors = new ArrayList<Visitor>();
		
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
		
		for(int i = 0; i < 500; i++)
		{
			
			Point2D position = new Point2D.Double(60,1600);
			while(!canSpawn(position)){
				if(position.getX()<240){
				
				position = new Point2D.Double(position.getX()+20, position.getY());}
				else{position = new Point2D.Double(50, position.getY()+20);}
			}
			visitors.add(new Visitor(position));
		}
		
		new Timer(1, this).start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setTransform(cameraTransform);
		
		tLoader.draw(g2);
		
		
		for(Visitor b : visitors)
			b.draw(g2);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		for(Visitor b : visitors)
			b.update(visitors);

		repaint();
	}
	
	public boolean canSpawn(Point2D p)
	{
		for(Visitor v : visitors)
			if(v.getLocation().distance(p) < 18)
				return false;
		return true;
	}
}
