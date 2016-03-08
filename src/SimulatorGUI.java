import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SimulatorGUI extends JPanel{
	
	private Schedule schedule;
	
	
	public SimulatorGUI(Schedule schedule){
		this.schedule = schedule;
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
	}

}
