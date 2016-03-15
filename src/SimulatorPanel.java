import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SimulatorPanel extends JPanel {
	Schedule schedule;
	
	public SimulatorPanel(Schedule schedule){
		this.schedule = schedule;
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
	}
}
