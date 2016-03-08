import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SimulatorGUI extends JPanel{
	
	private Schedule schedule;
	
	
	
	public SimulatorGUI(Schedule schedule){
		this.schedule = schedule;
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		TexturePaint paint;
		
		Rectangle2D r1 = new Rectangle2D.Double(100,100,100,100);
		Area a1 = new Area(r1);
		URL url = getClass().getClassLoader().getResource("download.png");
		try{
			BufferedImage image  = ImageIO.read(url);
			paint = new TexturePaint(image,r1);
			g2d.setPaint(paint);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		g2d.fill(a1);
	}

}
