package Tiled;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.Timer;

public class Toilet implements ActionListener {
	private Visitor visitor;
	private Point2D oldPoint;
	private Point2D toiletLocation;
	
	public Toilet(Visitor visitor, Point2D oldPoint, Target target)
	{
		this.visitor = visitor;
		this.oldPoint = oldPoint;
		this.toiletLocation = new Point2D.Double((double)(target.getX()*32), (double)(target.getY()*32));
				//;
//		visitor.setTarget(toiletLocation);		
		new Timer(1, this).start();
	}
	public boolean onPlace()
	{
	
		if(visitor.getLocation().distance(toiletLocation) <= 30  ){
		return true;
		}
		else {
			return false;
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if((visitor.getTarget() != oldPoint) && (visitor.getTarget() != toiletLocation))
			oldPoint = visitor.getTarget();
//		if(onPlace())
//			visitor.setTarget(oldPoint);
	}

}