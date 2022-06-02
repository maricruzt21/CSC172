/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#3
 * Lab: MW 2:00-3:15
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class Draw extends JPanel {
	//data structures to draw edges
	private Map<Integer,Edge> edges = new HashMap<Integer, Edge>();
	private ArrayList<Edge> dEdges = new ArrayList<Edge>();
	double latDistance, lonDistance, lonMax, latMin, wlat, wlon, vlat, vlon;  //latitude and longitude
	Edge drawLine;
	Shape line;
	
	 //adds edges to graph
	 public void implementEdges(Map<Integer,Edge> map, double maxLat, double minLat, double maxLon, double minLon) {
		
		latDistance = Math.abs(maxLat - minLat);
		lonDistance = Math.abs(maxLon - minLon);
		latMin = Math.abs(minLat);
		lonMax = Math.abs(maxLon);
		edges.putAll(map);
		
	    repaint();
	 }
	 
	 //draws the values of dijkstra/kruskal on top of map
	 public void drawtheD(Map<Integer,Edge> map,ArrayList<Edge> e, double maxLat, double minLat, double maxLon, double minLon){
		    
		 latDistance = Math.abs(maxLat - minLat);
		 lonDistance = Math.abs(maxLon - minLon);
		 latMin = Math.abs(minLat);
		 lonMax = Math.abs(maxLon);
		 edges.putAll(map);
		 dEdges.addAll(e);
		 repaint();
	 }
	 
	 //paint
	 public void paintComponent(Graphics graphics) {
		 	Graphics2D g2 = (Graphics2D) graphics;
	        super.paintComponent(graphics);

	        //width and height of screen
	         int width = getHeight();
	         int height = getWidth();
	       
	        
	        //set background to black
	        this.setBackground(Color.BLACK);
	        //draws the map 
	        for(int i = 0; i < edges.size(); i++){ //draws the lines 
	        	graphics.setColor(Color.WHITE); //lines white
	            line = new Line2D.Double(height -(1-(((edges.get(i).v.longitude + lonMax) * (height / lonDistance)))),
	            		(width-((edges.get(i).v.latitude - latMin) * (width / latDistance))), 
	            		(height-(1-((edges.get(i).w.longitude + lonMax) * (height / lonDistance)))),
	            		(width-((edges.get(i).w.latitude - latMin) * (width / latDistance))));
	            g2.draw(line);
	        }
	        //draws the lines for the shortest path
	        if(dEdges.size()!=0){
	        	g2.setColor(Color.RED); //makes the path red
	        	for(int j=0;j<dEdges.size();j++){
	        		line = new Line2D.Double(height -(1-(((dEdges.get(j).v.longitude + lonMax) * (height / lonDistance)))),
            		(width-((dEdges.get(j).v.latitude - latMin) * (width / latDistance))), 
            		(height-(1-((dEdges.get(j).w.longitude + lonMax) * (height / lonDistance)))),
            		(width-((dEdges.get(j).w.latitude - latMin) * (width / latDistance))));
	        	g2.draw(line);
	        	}
	        }
	 }
}