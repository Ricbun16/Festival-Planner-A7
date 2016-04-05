package Tiled;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Visitor{
	private Point2D location;
	private double speed = 5;
	private Image sprite;
	private double direction;

	private Point2D pointTarget;
	private Target target;

	public Visitor(Point2D location)
	{
		this.location = location;
		sprite = new ImageIcon("visitor.png").getImage();
		direction = Math.random() * Math.PI*2;
		
		pointTarget = new Point2D.Double(250, 0);
	}

	public void draw(Graphics2D g2d) {
		AffineTransform transform = new AffineTransform();
		transform.translate(
				location.getX() - sprite.getWidth(null)/2, 
				location.getY() - sprite.getHeight(null)/2);
		transform.rotate(direction, sprite.getWidth(null)/2, sprite.getHeight(null)/2);
		g2d.drawImage(sprite, transform, null);
		
	}

	public void update(ArrayList<Visitor> visitors) {
		if(target != null){
//			System.out.println(target.getX());
//			System.out.println(target.getY());
//			System.out.println(location.getX());
//			System.out.println(location.getY());
		if(isAtTarget()){
		//	System.out.println(1);
		
		}else{
		
			int tileX = (int) Math.floor(location.getX() / 32);
//			System.out.println(location.getX());
//			System.out.println(location.getX() / 32);
//			System.out.println(Math.floor(location.getX() / 32));
			int tileY = (int) Math.floor(location.getY() / 32);
//			System.out.println(location.getY());
//			System.out.println(location.getY() / 32);
//			System.out.println(Math.floor(location.getY() / 32));
//			if(tileX>49){
//				tileX=49;
//			}if(tileY>49){
//				tileY=49;
//			}
//			if(tileX < 0) 
//				tileX = 0;
//			if(tileY < 0) 
//				tileY = 0;
			Point newPoint = target.getNextPoint(tileX, tileY);	
			pointTarget =(Point2D) new Point(newPoint.x*32,newPoint.y*32);
		}
		}
//		System.out.println(pointTarget.getX());
//		System.out.println(pointTarget.getY());
		double dx = pointTarget.getX() - location.getX();
		double dy = pointTarget.getY() - location.getY();
		double newDirection = Math.atan2(dy, dx);
		
		//direction = newDirection;
		double deltaDirection = newDirection - direction;
		while(deltaDirection > Math.PI)
			deltaDirection -= 2 * Math.PI;
		while(deltaDirection < -Math.PI)
			deltaDirection += 2 * Math.PI;
		
		if(deltaDirection < 0)
			direction -= 0.1;
		if(deltaDirection > 0)
			direction += 0.1;
			
		
		Point2D newLocation = new Point2D.Double(
				location.getX() + Math.cos(direction) * speed,
				location.getY() + Math.sin(direction) * speed
				);
		
		boolean isCollision = false;
		for(Visitor b : visitors)
		{
			if(b == this)
				continue;
			if(b.location.distance(newLocation) < 18)
			{
				isCollision = true;
				break;
			}
			TiledLoader loader = new TiledLoader();
			if(b.location.equals(loader.getColLayer()));
		}
		
		if(!isCollision)
			location = newLocation;
		else{
			direction += 0.3;
			
		}
		
	}
	
	public boolean isAtTarget(){
		try{
		if(target.getValue(((int)location.getX()/32)-1,((int)location.getY()/32)-1)<3){
			//System.out.println(target.getValue(((int)location.getX()/32)-1,((int)location.getY()/32)-1));
			return true;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
			//System.out.println(false);
			return false;
	}
	
	public Point2D getLocation()
	{
		return location;
	} 

	public void setPointTarget(Point point) {
		this.pointTarget = point;		
	}
	
	public void setTarget(Target target){
		this.target = target;
	}


}