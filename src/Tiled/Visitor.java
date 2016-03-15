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

	private Point2D target;

	public Visitor(Point2D location)
	{
		this.location = location;
		sprite = new ImageIcon("visitor.png").getImage();
		direction = Math.random() * Math.PI*2;
		
		target = new Point2D.Double(250, 750);
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
		double dx = target.getX() - location.getX();
		double dy = target.getY() - location.getY();
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
		}
		
		if(!isCollision)
			location = newLocation;
		else{
			direction += 0.3;
			
		}
		
	}
	public Point2D getLocation()
	{
		return location;
	} 

	public void setTarget(Point point) {
		this.target = point;		
	}


}