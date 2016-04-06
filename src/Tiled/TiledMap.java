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

import Agenda.Schedule;
import Agenda.TimeSlot;

public class TiledMap  extends JPanel implements ActionListener{
	TiledLoader tLoader;
	AffineTransform cameraTransform;
	int oldX, oldY;
	int newX, newY;
	int aantalVisitors;// = 100;
	private Point mousePoint;
	private ArrayList<Visitor> visitors;
	private Schedule schedule;
	private ArrayList<TimeSlot> currentTimeSlots;
	private int currentTime;
	private ArrayList<Target> targets;
	
	File file = new File("JSON/event.json");
	private int seconds;
	private int minutes;
	private int tick;
	private JButton terug;
	private JButton vooruit;
	private JLabel tijd;
	private int tijdState = 0;
	private ArrayList<TimeSlotMem> memSlotList;

	public TiledMap(Schedule schedule){
		cameraTransform = new AffineTransform();
		tLoader = new TiledLoader(file);
		tLoader.createLayers();
		visitors = new ArrayList<Visitor>();
		memSlotList = new ArrayList<TimeSlotMem>();
		this.schedule = schedule;
		currentTimeSlots = new ArrayList<TimeSlot>();
		currentTime = schedule.getScheduleStartTime()*100;
		targets = tLoader.getTargets();
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
		
//		for(int i = 0; i < aantalVisitors; i++)
//		{
//			
//			Point2D position = new Point2D.Double(70,1800);
//			while(!canSpawn(position)){
//				if(position.getX()<240){
//				
//				position = new Point2D.Double(position.getX()+50, position.getY());}
//				else{position = new Point2D.Double(70, position.getY()+50);}
//			}
//			visitors.add(new Visitor(position));
//		}
//		spawnVisitors(aantalVisitors);
		
		new Timer(1, this).start();
		
		switchTimeslot(0);
		
	}
	
	public void spawnVisitors(int amountVisitors){
		for(int i = 0; i < amountVisitors; i++)
		{
			Point2D position = new Point2D.Double(70,1800);
			while(!canSpawn(position)){
				if(position.getX()<240){
				
				position = new Point2D.Double(position.getX()+50, position.getY());}
				else{position = new Point2D.Double(70, position.getY()+50);}
			}
			visitors.add(new Visitor(position,this));
			
		}

		switchTimeslot(0);
		setInitialTarget();
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
			if(currentTime + 30 > schedule.getScheduleStartTime()*100){
			currentTime-=30;
			if(currentTime%100>60)
				currentTime-=40;
			for(TimeSlotMem m : memSlotList) {
				if(m.getTime() == currentTime) {
					visitors.clear();
					for(Point2D p : m.getLocations())
						visitors.add(new Visitor(p, this));
					for(TimeSlot ts : m.getLijst())
						currentTimeSlots.add(ts);
					break;
				}
			}
			}
			else{
				currentTime = schedule.getScheduleStartTime()*100;
				for(TimeSlotMem m : memSlotList) {
					if(m.getTime() == currentTime) {
						visitors.clear();
						for(Point2D p : m.getLocations())
							visitors.add(new Visitor(p,this));
						for(TimeSlot ts : m.getLijst())
							currentTimeSlots.add(ts);
						break;
					}
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
						case "Stage 1": visitors.get(i).setTarget(targets.get(1));
						break;
						case "Stage 2": visitors.get(i).setTarget(targets.get(2));
						break;
						case "Stage 3": visitors.get(i).setTarget(targets.get(3));
						break;
						case "Stage 4": visitors.get(i).setTarget(targets.get(4));
						break;
						case "Stage 5": visitors.get(i).setTarget(targets.get(5));
						break;
						default:  visitors.get(i).setTarget(targets.get(0));
						break;
					}
				}
				count += pop.get(ii);
			}
		}		
		currentTimeSlots.clear();	
	}
	public void setInitialTarget()
	{
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
						case "Stage 1": visitors.get(i).setTarget(targets.get(1));
						break;
						case "Stage 2": visitors.get(i).setTarget(targets.get(2));
						break;
						case "Stage 3": visitors.get(i).setTarget(targets.get(3));
						break;
						case "Stage 4": visitors.get(i).setTarget(targets.get(4));
						break;
						case "Stage 5": visitors.get(i).setTarget(targets.get(5));
						break;
						default:  visitors.get(i).setTarget(targets.get(0));
						break;
					}
				}
				count += pop.get(ii);
			}
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
		
		for(Visitor b : visitors){
			setInitialTarget();
			b.update(visitors, tLoader.getColLayer());
		}
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
				new Toilet(visitors.get((int)(Math.random() * aantalVisitors)), visitors.get((int)(Math.random() * aantalVisitors)).getTarget(), targets.get(8));
				new Toilet(visitors.get((int)(Math.random() * aantalVisitors)), visitors.get((int)(Math.random() * aantalVisitors)).getTarget(), targets.get(7));
				new Toilet(visitors.get((int)(Math.random() * aantalVisitors)), visitors.get((int)(Math.random() * aantalVisitors)).getTarget(), targets.get(6));
				new Toilet(visitors.get((int)(Math.random() * aantalVisitors)), visitors.get((int)(Math.random() * aantalVisitors)).getTarget(), targets.get(5));
			}
		
			if (seconds == 26){
				for(int i = 0 ; i < 50; i++) {
				new Toilet(visitors.get((int)(Math.random() * aantalVisitors)), visitors.get((int)(Math.random() * aantalVisitors)).getTarget(), targets.get(6));
				new Toilet(visitors.get((int)(Math.random() * aantalVisitors)), visitors.get((int)(Math.random() * aantalVisitors)).getTarget(),targets.get(7));
				new Toilet(visitors.get((int)(Math.random() * aantalVisitors)), visitors.get((int)(Math.random() * aantalVisitors)).getTarget(), targets.get(8));
				new Toilet(visitors.get((int)(Math.random() * aantalVisitors)), visitors.get((int)(Math.random() * aantalVisitors)).getTarget(), targets.get(5));
				}
			}
			if (seconds > 30) {
				seconds = 0;
				switchTimeslot(1);
			}
			if (minutes == 59)
				minutes = 0;
			else {
				switchTimeslot(0);
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

	public void setAantalVisitors(int aantal){
		this.aantalVisitors = aantal;
	}
	
	public int getAantalVisitors(){
		return aantalVisitors;
	}

}