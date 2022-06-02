/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#3
 * Lab: MW 2:00-3:15
 * 
 */

import java.util.LinkedList;

public class Vertex {
	
	public int vNum; //number of vertex
	public String name;
	public double latitude, longitude, distance;
	public boolean known;
	public Vertex path;
	public LinkedList<Adjacents> adjacent;
	
	//constructor
	public Vertex(int vNum, String name, double latitude, double longitude){
		this.vNum = vNum;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		known = false;
		path = null;
		adjacent = new LinkedList<Adjacents>();
		distance = Double.POSITIVE_INFINITY;
	}
	
	//add
	public void insert(Adjacents a){
		adjacent.add(a);
	}
	
}