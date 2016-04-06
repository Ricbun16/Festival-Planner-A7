package Tiled;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Toilet implements ActionListener {
	private Visitor visitor;
	private Target oldTarget;
	private Target toiletLocation;
	
	public Toilet(Visitor visitor, Target oldTarget, Target target)
	{
		this.visitor = visitor;
		this.oldTarget = oldTarget;
		this.toiletLocation = target;
		visitor.setTarget(toiletLocation);		
		new Timer(1, this).start();
	}
	
	public boolean onPlace()
	{
		if(visitor.getLocation().distance(toiletLocation.getXY()) <= 120)
			return true;
		else 
			return false;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if((visitor.getTarget() != oldTarget) && (visitor.getTarget() != toiletLocation))
			visitor.setTarget(visitor.getTarget());
		if(onPlace())
			visitor.setTarget(oldTarget);
	}

}