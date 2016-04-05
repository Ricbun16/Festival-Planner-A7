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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Agenda.Schedule;
import Agenda.TimeSlot;

public class TiledMap  extends JPanel implements ActionListener{
	TiledLoader tLoader;
	AffineTransform cameraTransform;
	int oldX, oldY;
	int newX, newY;
	private Point mousePoint;
	private ArrayList<Visitor> visitors;
	private Schedule schedule;
	private ArrayList<TimeSlot> currentTimeSlots;
	private int currentTime;
	private ArrayList<Target> targets;
	
	File file = new File("JSON/event.json");
	
//	public static void main(String[] args){
//		JFrame frame = new JFrame("Simulator");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		JPanel panel = new TiledMap(new Schedule());
//		frame.setContentPane(panel);
//		frame.setSize(840, 480);
//		frame.setVisible(true);
//	}

	public TiledMap(Schedule schedule){
		cameraTransform = new AffineTransform();
		tLoader = new TiledLoader(file);
		tLoader.createLayers();
		visitors = new ArrayList<Visitor>();
		this.schedule = schedule;
		currentTimeSlots = new ArrayList<TimeSlot>();
		currentTime = schedule.getScheduleStartTime()*100;
		targets = tLoader.getTargets();
		
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
		
		for(int i = 0; i < 1; i++)
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
		
		switchTimeslot();
		
	}
	
	public void switchTimeslot(){
		
		currentTimeSlots.clear();
		for(int x = 0; x < schedule.getStages().size(); x++){
			TimeSlot current = schedule.getStages().get(x).getTimeSlots().get(1);
			System.out.println(current.getTimeSlotStart());
			if(current.getTimeSlotStart()==currentTime){
				if(current.getOccupied())
					currentTimeSlots.add(current);
			schedule.getStages().get(x).getTimeSlots().remove(0);
			}
		}
		
		int totalPop = 0;
		ArrayList<Integer> pop = new ArrayList<Integer>();
		if(currentTimeSlots != null){
			for(int y = 0; y < currentTimeSlots.size(); y++){
				totalPop += currentTimeSlots.get(y).getPopularity();
				pop.add(currentTimeSlots.get(y).getPopularity());
			}
		}
		
		for(int i = 0; i < visitors.size(); i++){
			
			visitors.get(i).setTarget(targets.get(0));
			
			
			int random = (int)(Math.random() * totalPop + 1), count = 0;
			for(int ii = 0; ii < pop.size(); ii++){
				if((random > count)&&(random < (count + pop.get(ii)))){
					switch(currentTimeSlots.get(ii).getStageName()){
						case "Stage 1": visitors.get(i).setTarget(targets.get(0));
							break;
							
						case "Stage 2": visitors.get(i).setTarget(targets.get(1));
							break;
						case "Stage 3": visitors.get(i).setTarget(targets.get(2));
						break;
						case "Stage 4": visitors.get(i).setTarget(targets.get(3));
						break;
						case "Stage 5": visitors.get(i).setTarget(targets.get(4));
						break;
							
						default:  visitors.get(i).setPointTarget(new Point(5000,1200));
						break;
					}
				}
				count += pop.get(ii);
			}
		}
		
		currentTime+=30;
		if(currentTime%100>60){
			currentTime+=40;
		}
		
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
