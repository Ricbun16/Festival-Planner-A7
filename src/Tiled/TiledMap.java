package Tiled;
import java.awt.FlowLayout;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Agenda.*;

public class TiledMap  extends JPanel implements ActionListener{
	TiledLoader tLoader;
	AffineTransform cameraTransform;
	int oldX, oldY;
	private int tick = 0;
	private int seconds = 0, minutes = 0;
	int newX, newY;
	private Point mousePoint;
	private ArrayList<Visitor> visitors;
	private Schedule schedule;
	private ArrayList<TimeSlot> currentTimeSlots;
	private int currentTime;
	private JLabel tijd;
	private JButton terug, vooruit;
	private ArrayList<TimeSlotMem> memSlotList;
	private int tijdState = 0;
	
	File file = new File("JSON/event.json");

	public TiledMap(Schedule schedule){
		
		cameraTransform = new AffineTransform();
		tLoader = new TiledLoader(file);
		tLoader.createLayers();
		visitors = new ArrayList<Visitor>();
		memSlotList = new ArrayList<TimeSlotMem>();
		this.schedule = schedule;
		currentTimeSlots = new ArrayList<TimeSlot>();
		currentTime = schedule.getScheduleStartTime()*100;
		makeLittleFrame();
		
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
		
		switchTimeslot(0);
		
	}
	
	public void switchTimeslot(int vooruit){
		
		if(vooruit == 1) {
			currentTime+=30;
			if(currentTime%100>=60)
				currentTime+=40;

			ArrayList<Point2D> locations = new ArrayList<Point2D>();
			for(Visitor v : visitors)
				locations.add(v.getLocation());
			memSlotList.add(new TimeSlotMem(currentTime, locations));
			for(int x = 0; x < schedule.getStages().size(); x++){
				TimeSlot current = schedule.getStages().get(x).getTimeSlots().get(1);
				if(current.getTimeSlotStart()==currentTime){
					if(current.getOccupied()) {
						currentTimeSlots.add(current);
						memSlotList.get(tijdState).addTimeslot(current);
					}
					schedule.getStages().get(x).getTimeSlots().remove(0);
				}
			}
				
			if(currentTimeSlots != null)
				tijdState++;
			
			for(TimeSlotMem m : memSlotList) {
				if(m.getTime() == currentTime) {
					System.out.println(currentTime);
					for(TimeSlot ts : m.getLijst())
						currentTimeSlots.add(ts);
					break;
				}
			}
				
		}
		
		if(vooruit == 2) {
			currentTime-=30;
			if(currentTime%100>60)
				currentTime-=40;
			for(TimeSlotMem m : memSlotList) {
				if(m.getTime() == currentTime) {
					visitors.clear();
					for(Point2D p : m.getLocations())
						visitors.add(new Visitor(p));
					for(TimeSlot ts : m.getLijst())
						currentTimeSlots.add(ts);
					break;
				}
			}
		}
		
		if(vooruit == 0) {
			
			ArrayList<Point2D> locations = new ArrayList<Point2D>();
			for(Visitor v : visitors)
				locations.add(v.getLocation());
			memSlotList.add(new TimeSlotMem(currentTime, locations));
			for(int x = 0; x < schedule.getStages().size(); x++){
				TimeSlot current = schedule.getStages().get(x).getTimeSlots().get(1);
				if(current.getTimeSlotStart()==currentTime){
					if(current.getOccupied()) {
						currentTimeSlots.add(current);
						memSlotList.get(tijdState).addTimeslot(current);
					}
					schedule.getStages().get(x).getTimeSlots().remove(0);
				}
			}
			if(currentTimeSlots != null)
				tijdState++;
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
			int random = (int)(Math.random() * totalPop + 1), count = 0;
			for(int ii = 0; ii < pop.size(); ii++){
				if((random > count)&&(random < (count + pop.get(ii)))){
					switch(currentTimeSlots.get(ii).getStageName()){
						case "Stage 1": visitors.get(i).setTarget(new Point(250,750));
						break;
						case "Stage 2": visitors.get(i).setTarget(new Point(800,1350));
						break;
						case "Stage 3": visitors.get(i).setTarget(new Point(800,250));
						break;
						case "Stage 4": visitors.get(i).setTarget(new Point(1400,530));
						break;
						case "Stage 5": visitors.get(i).setTarget(new Point(1400,1050));
						break;
					}
				}
				count += pop.get(ii);
			}
		}		
		currentTimeSlots.clear();	
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
		
		if(tick < 50)
			tick++;
		else {
			if(minutes < 10)
				tijd.setText((currentTime/100) + ":0" + minutes);
			else
				tijd.setText((currentTime/100) + ":" + minutes);
			tick = 0;
			System.out.println(seconds);
			if(seconds > 3){
				new Toilet(visitors.get((int)(Math.random() * 499 + 1)), visitors.get((int)(Math.random() * 499 + 1)).getTarget(), new Point(900,900));
				new Toilet(visitors.get((int)(Math.random() * 499 + 1)), visitors.get((int)(Math.random() * 499 + 1)).getTarget(), new Point(20,20));
			}
		
			if (seconds == 26){
				for(int i = 0 ; i < 50; i++) {
					new Toilet(visitors.get((int)(Math.random() * 499 + 1)), visitors.get((int)(Math.random() * 499 + 1)).getTarget(), new Point(20,20));
					new Toilet(visitors.get((int)(Math.random() * 499 + 1)), visitors.get((int)(Math.random() * 499 + 1)).getTarget(), new Point(900,900));
				}
			}
			if (seconds > 30) {
				seconds = 0;
				switchTimeslot(1);
			}
			if (minutes == 59)
				minutes = 0;
			else {
				seconds++;
				minutes++;
			}
		}

		repaint();
	}
	
	public boolean canSpawn(Point2D p)
	{
		for(Visitor v : visitors)
			if(v.getLocation().distance(p) < 18)
				return false;
		return true;
	}
	
	public void makeLittleFrame(){
		
		JPanel content = new JPanel(new FlowLayout());
		JFrame littleFrame = new JFrame("Bediening");
		littleFrame.setSize(250,60);
		littleFrame.setContentPane(content);
		littleFrame.setResizable(false);
		littleFrame.setVisible(true);
		
		terug = new JButton("<");
		vooruit = new JButton(">");
		tijd = new JLabel(" ");
		content.add(terug);
		content.add(tijd);
		content.add(vooruit);
		
		terug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(minutes < 30) {
					minutes = 30;
					seconds = 0;
				} else {
					minutes = 0;
					seconds = 0;
				} 
				switchTimeslot(2);
			}
		});
		
		vooruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(minutes < 30) {
					minutes = 30;
					seconds = 0;
				} else {
					minutes = 0;
					seconds = 0;
				} 
				switchTimeslot(1);
			}
		});
		
	}
}