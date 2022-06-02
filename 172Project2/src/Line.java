/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#2
 * Lab: MW 2:00-3:15
 * 
 */

public class Line {

	private Point startPoint;
	private Point endPoint;
	private int id;
	
	
	//constructor
	public Line(double x0, double y0, double x1, double y1, int id) {
		this(new Point(x0, y0), new Point(x1, y1), id);
	}
	
	public Line(Point startPoint, Point endPoint, int id) {
		if(startPoint.getX() > endPoint.getX()) {
			this.startPoint = endPoint;
			this.endPoint = startPoint;
		}
		else {
			this.startPoint = startPoint;
			this.endPoint = endPoint;
		}
		this.id = id;
	}
	
	public String toString() {
		return "Line: " + id + " ( " + startPoint.toString() + " , " + endPoint.toString() + ")";
	}
	//getters for the points in the line
	public Point getPoint1() {
		return startPoint;
	}
	
	public Point getPoint2() {
		return endPoint;
	}
	
	public int getID() {
		return id;
	}
	//slope of the line
	public double slope() {
		//y2 - y1/ x2 -x1
		return(endPoint.getY() - startPoint.getY()) / (endPoint.getX() - startPoint.getX());
	}
	
	
	//intersection
	public boolean intersection(Line other) {
		Point A = this.startPoint;
		Point B = this.endPoint;
		Point C = other.startPoint;
		Point D = other.endPoint;
		
		// Line AB represented as a1x + b1y = c1 
        double a1 = B.getY() - A.getY(); 
        double b1 = A.getX() - B.getX(); 
        double c1 = a1*(A.getX()) + b1*(A.getY()); 
       
        // Line CD represented as a2x + b2y = c2 
        double a2 = D.getY() - C.getY(); 
        double b2 = C.getX() - D.getX(); 
        double c2 = a2*(C.getX())+ b2*(C.getY()); 
       
        double determinant = a1*b2 - a2*b1; 
       
        if (determinant == 0) 
        { 
            // The lines are parallel. This is simplified 
            // by returning a pair of FLT_MAX 
            return false;
        } 
        else
        { 
            double x = (b2*c1 - b1*c2)/determinant; 
            double y = (a1*c2 - a2*c1)/determinant; 
            
            if(x < 0 || x > 1 || y < 0 || y > 1) {
            	return false;
            }
            return true;
            		
        } 
        

	}
	
	//ccw
	//given from lab write-up
	public int pointDirection(Point p) { 
		
		double dx1 = endPoint.getX() - startPoint.getX();
		double dy1 = endPoint.getY() - startPoint.getY();
		double dx2 = p.getX() - startPoint.getX();
		double dy2 = p.getY() - startPoint.getY();
		if (dx1*dy2 > dy1*dx2) 
			return 0; //counterclockwise
		else if (dx1*dy2 < dy1*dx2) 
			return 1; //clockwise
		else if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) 
			return 1; //clockwise
		else if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) 
			return 0;   //counterclockwise 
		else 
			return 2; //colinear
	}

}
