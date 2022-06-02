/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#2
 * Lab: MW 2:00-3:15
 * 
 */

public class Point {

	private double xVal;
	private double yVal;
	
	//contructor
	public Point(double x, double y) {
		xVal = x;
		yVal = y;
	
	}
	
	public String toString() {
		return "X: " + xVal + " Y: " + yVal; 
	}
	
	//getter and setter methods for X and Y location
	public double getX() {
		return xVal;
	}
	
	public double getY(){
		return yVal;
	}
	
	public void setX(double x) {
		xVal = x; 
	}
	
	public void setY(double y) {
		yVal = y;
	}
	
	public void setLocation(double x, double y) {
		xVal = x; 
		yVal = y;
	}
	
	
}
