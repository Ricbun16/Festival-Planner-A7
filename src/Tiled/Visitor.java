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
	private double speed ;
	private Image sprite;
	private double direction;

	private Point2D pointTarget;
	private Target target;

	public Visitor(Point2D location)
	{
		this.location = location;
		sprite = new ImageIcon("visitor.png").getImage();
		direction = Math.random() * Math.PI*2;
		speed = 10;
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

	public void update(ArrayList<Visitor> visitors, TiledLayer collisionLayer) {

		if(target != null){
		if(isAtTarget()){
		}
		else{
			int tileX = (int) Math.floor(location.getX() / 32);
			int tileY = (int) Math.floor(location.getY() / 32);
//			if(tileX>49)
//				tileX = 49;
//			if(tileY>49)
//				tileY = 49;
//			if(tileX < 0) 
//				tileX = 0;
//			if(tileY < 0) 
//				tileY = 0;
			Point newPoint = target.getNextPoint(tileX, tileY);	
			pointTarget =(Point2D) new Point((newPoint.x*32)+16,(newPoint.y*32)+16);
		}
		}
//		System.out.println("target x"+ pointTarget.getX());
//		System.out.println("target y "+ pointTarget.getY());
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
			direction -= 0.5;
		if(deltaDirection > 0)
			direction += 0.5;
			
		
		Point2D newLocation = new Point2D.Double(
				location.getX() + Math.cos(direction) * speed,
				location.getY() + Math.sin(direction) * speed
				);
		
		boolean isCollision = false;
		for(Visitor b : visitors)
		{
			if(b == this)
				continue;
			if(b.location.distance(newLocation) < 20)
			{
				isCollision = true;
				break;
			}
//			TiledLoader loader = new TiledLoader();
//			if(b.location.equals(loader.getColLayer()));
		}
	
		
		if(!isCollision)
		{
			int tileX = (int) Math.floor(newLocation.getX() / 32);
			int tileY = (int) Math.floor(newLocation.getY() / 32);
			if(collisionLayer.getData2DPoint(tileX, tileY) != 364)
				location = newLocation;
		}
		else{
			direction += 0.9f;
			
		}
		
	}
	
	public boolean isAtTarget(){
		
		if(target.getValue((int)location.getX()/32,(int)location.getY()/32)<3){
//			System.out.println(true);
			return true;
		}
//			System.out.println(false);
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

	public Target getTarget(){
		return target;
	}
}