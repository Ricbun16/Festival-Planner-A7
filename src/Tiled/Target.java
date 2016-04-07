package Tiled;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Target {
	
	private int[][] path;
	private int id;
	private String name;
	private Point start;
	private TiledObject tObject;
	Point offSets[]= {new Point(1,0),new Point(-1,0), new Point(0,-1),new Point(0,1)};
	
	public Target(TiledObject tObject){
		this.start = (Point) start;
		this.tObject = tObject;
		id = tObject.getID();
		name = tObject.getName();
		
		 start =  new Point(
				 tObject.getX()/32,
				 tObject.getY()/32
				);
	}
	
	public void build(TiledLayer collision) {
		
		path = new int[collision.getWidth()][collision.getHeight()];
		for(int y = 0;y < collision.getHeight() ;y++){
			
			for(int x = 0;x < collision.getWidth() ;x++) {
				path[x][y] = 99999;
			}
		}
		Queue<Point> todo = new LinkedList<>();
		ArrayList<Point> visited = new ArrayList<>();
		path[start.x][start.y] = 0;
		todo.add(start);
		visited.add(start);
		
		
		
		while(!todo.isEmpty()){
			Point current = todo.remove();
			
			for(int i = 0; i < offSets.length;i++){
				Point newPoint = new Point(current.x + offSets[i].x,current.y + offSets[i].y);
				if(newPoint.x < 0 || newPoint.y < 0 || newPoint.x >= collision.getWidth()-1 || newPoint.y >= collision.getHeight()-1)
					continue;
				if(visited.contains(newPoint))
					continue;
				if(collision.getData2DPoint(newPoint.x,newPoint.y) == 364)
					continue;
				
				path[newPoint.x][newPoint.y] = path[current.x][current.y]+1;
				visited.add(newPoint);
				todo.add(newPoint);
			}
		}
	}
	
	public Point getNextPoint(int x, int y){
		if(x>49 || y>49 || x<0 || y<0){
			
		}else{
//		System.out.println("Start:\t" + start.getX() + "\t" + start.getY());
//		System.out.println("else");
		int current = path[x][y];
//		System.out.println("current:\t" + current);
		for(int i = 0;i< offSets.length;i++){
			try{
				
				Point newPoint = new Point(x + offSets[i].x,y + offSets[i].y);
//				System.out.println("next point: \t"+path[newPoint.x][newPoint.y]);
				if(path[newPoint.x][newPoint.y] < current){
//					System.out.println("new point");
					return newPoint;
				}
				
			}catch(Exception e){
				
			}
		}}
		
		return new Point(2,31);
	}
	
	public int getValue(int x, int y){
		if(x>49){
			x = 49;
		}if(y>49){
			y = 49;
		}if(x<0){
			x=0;
		}if(y<0){
			y=0;
		}
		return path[x][y];
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	public int getX(){
		return start.x;
	}
	
	public int getY(){
		return start.y;
	}
	
	public Point2D getXY() {
		return new Point(tObject.getX(), tObject.getY());
	}
}
