/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#3
 * Lab: MW 2:00-3:15
 * 
 */

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

import javax.swing.JFrame;


public class Test {
	public static String container;
	public static String[] input;
	public static String a,b,c,d;
	public static int vCount = 0;
	public static int eCount = 0;
	public static double latitude, longitude;
	public static double Maxlat = Double.NEGATIVE_INFINITY;
	public static double Minlat = Double.POSITIVE_INFINITY;
	public static double Maxlon = Double.NEGATIVE_INFINITY;
	public static double Minlon = Double.POSITIVE_INFINITY;
	//store ID & vertex/ID & edge 
	public static Map<String, Vertex> vMap = new HashMap<String, Vertex>();
	public static Map<String, Edge> eMap = new HashMap<String, Edge>();
	//store count for vertex & vertex/edge count & edge
	public static Map<Integer, Vertex> cvMap = new HashMap<Integer, Vertex>();
	public static Map<Integer, Edge> ceMap = new HashMap<Integer,Edge>();
	public static ArrayList<Edge> dijPath = new ArrayList<Edge>();
	public static ArrayList<Edge> DVals = new ArrayList<Edge>();
	public static ArrayList<Edge> kpPath2 = new ArrayList<Edge>();
	public static ArrayList<Edge> eList = new ArrayList<Edge>();
	public static ArrayList<String>printPath=new ArrayList<String>();
	public static ArrayList<String> known=new ArrayList<String>();
	
	//main method
	public static void main(String[] args) {
		//length of argument
		int inputsize=args.length;
		try{
			//buffered reader for arguments
			BufferedReader read = new BufferedReader(new FileReader(args[0]));
			//container equals each line
			container = read.readLine();
			while(container != null){
				//split container by tabs
				input = container.split("\t");
				//a= intersection or road b=ID c=latitude d=longitude
				a = input[0];
				b = input[1];
				c = input[2];
				d = input[3];
				
				
				if(!a.equals(null) && !b.equals(null) && !c.equals(null) && !d.equals(null)){
			
					if(a.equals("i")){ //intersection
						if(!a.equals(null) && !b.equals(null) && !c.equals(null) && !d.equals(null)){
							//parse latitude and longitude
							latitude = Double.parseDouble(c);
							longitude = Double.parseDouble(d);
						//input vertex name into vertex map
							Vertex v = vMap.get(b);
							if(v == null){
								//create new vertex of current count, name, lat and long
								v = new Vertex(vCount,b, latitude, longitude);
								//place vertex count and vertex into cmap
								cvMap.put(vCount, v);
								//place ID and vertex into vertex map
								vMap.put(b,v);
								//increment count
								vCount++;
							}
							//alter maximum, minimum longitude and latitude based on values from file
							if(latitude >Maxlat)
								Maxlat = latitude;
							if(latitude < Minlat)
								Minlat = latitude;
							if(longitude > Maxlon)
								Maxlon = longitude;
							if(longitude < Minlon)
								Minlon = longitude;
						}
				
					}else if(a.equals("r")){ //road
						if(!vMap.get(c).equals(null)&&!vMap.get(d).equals(null)){
							
							Vertex v1 = vMap.get(c);
							Vertex v2 = vMap.get(d);
							
							Edge e = new Edge(v1,v2);
							
							eMap.put(b, e);
							
							eList.add(e);
							
							ceMap.put(eCount, e);
							
							vMap.get(e.v.name).insert(new Adjacents(e.w,e.weight));
							vMap.get(e.w.name).insert(new Adjacents(e.v,e.weight));
							//increment edge count
							eCount++;
						}
					}
				}else
					//print that inputs are not valid
						System.out.println(a + " " + b + " " + c + " " + d+" are not valid. Try again.");
				//read next line
						container = read.readLine();
			}
			read.close();
			
			//exceptions
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
		
		if(inputsize==2){
			if(args[1].equals("-show")){
				//show graph 
				JFrame frame=new JFrame();
				frame.setPreferredSize(new Dimension(700,700));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Draw draw=new Draw();
				frame.add(draw);
				frame.pack();
				frame.setResizable(true);
				frame.setVisible(true);
				draw.implementEdges(ceMap, Maxlat, Minlat, Maxlon, Minlon);
			}else if(args[1].equals("-meridianmap")){
				//show meridian maps roads
				printkPath();
			}else
				//incorrect input
				System.out.println(args[0]+" "+args[1]+" are incorrect inputs. Try again.");
			
	
		}else if(inputsize==3){
			//if show is within command line arguments
			if(args[1].equals("-show") || args[2].equals("-show")){
				//and meridian map is within command line arguments
				if(args[1].equals("-meridianmap") || args[2].equals("-meridianmap")){
					//set draw values to print k path 
					DVals=printkPath();
					//draw kruskal path
					JFrame frame = new JFrame();
					frame.setPreferredSize(new Dimension(700,700));
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Draw draw = new Draw();
					frame.setVisible(true);
					frame.add(draw);
					frame.pack();
					draw.drawtheD(ceMap,DVals,Maxlat,Minlat,Maxlon,Minlon);
				}
		}else
			System.out.println(args[0]+" "+args[1]+" "+args[2]+" are incorrect inputs. Try again.");
		
		}else if(inputsize==4){
			//if directions is included
			if(args[1].equals("-directions") && vMap.containsKey(args[2]) && vMap.containsKey(args[3])){
				String first = args[2];
				String second = args[3];
				//print directions
				System.out.println("Here is how to get from " + vMap.get(first).name + " to " + vMap.get(second).name + ": ");
				DVals = fPath(vMap.get(first),vMap.get(second));
			}else
				System.out.println(args[0]+" "+args[1]+" "+args[2]+" "+args[3]+" are incorrect inputs");
		
		}else if(inputsize==5){
			//show map and directions
			if(args[1].equals("-show") && args[2].equals("-directions") && vMap.containsKey(args[3]) && vMap.containsKey(args[4])){
				String first=args[3];
				String second=args[4];
				System.out.println("Here is how to get from "+vMap.get(first).name+" to "+ vMap.get(second).name+": ");
				DVals = fPath(vMap.get(first),vMap.get(second));
				JFrame frame = new JFrame();
				frame.setPreferredSize(new Dimension(700,700));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Draw draw = new Draw();
				frame.add(draw);
				frame.pack();
				frame.setVisible(true);	
				draw.drawtheD(ceMap,DVals,Maxlat,Minlat,Maxlon,Minlon);
				//show map and directions if -directions -show is command line argument
			}else
				System.out.println(args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]+" are invalid inputs. Try again.");
		
		} else {
			//arguments incorrect
			System.out.print("Invalid input! Try again. ");
			for(int i = 0; i < args.length; i++){
				System.out.print(args[i] + " ");
			}
		}
	}
	//dijkstras algorithm
	public static void dijkstras(Vertex v, Vertex w){
		//set distance to zero and known
		v.distance=0;
		v.known=true;
		//add first vertex to known arraylist
		known.add(v.name);
		//set smallest vertex to current vertex
		Vertex smallestVertex=v;
		//instantiate a temp vertex and weight
		double weight;
		Vertex rando;
		//while second vertex is not known
		while(!w.known){
			//set smallest vertex to be known
			smallestVertex.known=true;
			//instantiate linked list of adjacencies and set equal to smallest vertexes adjacencies
			LinkedList<Adjacents> adjLand=smallestVertex.adjacent;
			//iterate through linked list
			for(int i=0;i<adjLand.size();i++){
				//set temp to vertex at i
				rando=vMap.get(smallestVertex.name).adjacent.get(i).v;
				//set weight to weight of vertex at i
				weight=smallestVertex.adjacent.get(i).w;
				//add temp to known array list
				known.add(rando.name);
				//if vertex of adjacents linked list is not known
				if(!vMap.get(adjLand.get(i).v.name).known){
					//and if the smallest vertexes distance + weight is less than the temps distance
					if(smallestVertex.distance+weight<vMap.get(rando.name).distance){
						//set temp vertex distance to smallest vertex distance plus the weight
						vMap.get(rando.name).distance=smallestVertex.distance+weight;
						//set the path/smallest distance at temp to smallest vertex
						vMap.get(rando.name).path=smallestVertex;
					}
				}
			}
			//set smallest vertex to value from smallest vertex class
			smallestVertex=vMap.get(smallV(known));
		}
	}
	
	//finds smallest vertex of known vertices
	public static String smallV(ArrayList<String>known){
		String smallestV="";
		
	
		double path=Double.POSITIVE_INFINITY;
		
	
		ArrayList<String>delete=new ArrayList<String>();
		
		//iterate through known list
		for(int i=0;i<known.size();i++){
			//if known at i is not known and the distance is less than the current path 
			if(!vMap.get(known.get(i)).known&&vMap.get(known.get(i)).distance<path){
				
				smallestV=vMap.get(known.get(i)).name;
				
				path=vMap.get(known.get(i)).distance;
			
			}else if(vMap.get(known.get(i)).known){
				
				delete.add(vMap.get(known.get(i)).name);
			}
		}
			if(delete.size() > 0){
				//iterate through array list
				 for(int j = 0; j < delete.size(); j++){
					 //remove values to reduce size of arraylist
					 known.remove(delete.get(j));
				 }
		}
			//return the smallest vertex
		return smallestV;
	}
	//final path returns the edges 
	public static ArrayList<Edge> fPath(Vertex v,Vertex w){
		
		dijkstras(v,w);
		Vertex z=w;
		printPath.add(w.name);
		
		//while temp vertex is not equal to v
		while(z!=v){
			
			Edge e=new Edge(z,z.path);
			dijPath.add(e);
			printPath.add(z.path.name);
			z=z.path;
		}
		
		//locations is one less than the size of print path
		 int locs = printPath.size() - 1;
		
		 //iterate through print path array list
		 for(int i = 0; i < printPath.size(); i++){
				System.out.print(printPath.get(i));
			
				if(locs > 0){
					System.out.print(" -> "); //goes to
				}
				//decrement number of locations
				locs--;
			}
		 //print out final distance
		System.out.println("\nThe total distance between " + v.name + " and " + w.name + " is: " + w.distance + " miles");
		return dijPath;
	}
	//kruskals algorithm to find minimum weight spanning tree
	private static ArrayList<Edge> kruskals(){
		//sort list of edges
		Collections.sort(eList);
		int edges=0;
		int index = 0;
		ArrayList<Edge> kList = new ArrayList<Edge>();
		ArrayList<Vertex> knownVertices = new ArrayList<Vertex>();
		//add first edge and vertices associated with it
		kList.add(eList.get(index));
		knownVertices.add(eList.get(index).v);
		knownVertices.add(eList.get(index).w);
		
		//while there are still edges
		while(edges < vCount - 1){
			//if one of the vertices is known or neither
			if(knownVertices.contains(eList.get(index).v)&&!knownVertices.contains(eList.get(index).w)
					||!knownVertices.contains(eList.get(index).v)&&knownVertices.contains(eList.get(index).w)
					||!knownVertices.contains(eList.get(index).v)&&!knownVertices.contains(eList.get(index).w)){
				//add values to kruskal list of edges
				kList.add(eList.get(index));
				//add vertices to known vertices array list
				knownVertices.add(eList.get(index).v);
				knownVertices.add(eList.get(index).w);
			
			}else{}
			//increment index and edges
			index++;
			edges++;
		}
		
		return kList;
	}
	
	//prints path to console
	public static ArrayList<Edge> printkPath(){
		//call kruskals
		kruskals();
		//iterate through kruskals
		for(int i=0;i<kruskals().size();i++){
			//as long as kruskals is not empty
			if(!kruskals().isEmpty()){
			//create an edge from the vertices
			Edge e= new Edge(kruskals().get(i).v,kruskals().get(i).w);
			//add these edges to an array list
			kpPath2.add(e);
			}
		}
		//iterate through arraylist
		for(int j=0;j<kpPath2.size();j++){
			//print out names of vertices to the console
			System.out.println(kpPath2.get(j).v.name+" to "+kpPath2.get(j).w.name);
		}
		//return array list
		return kpPath2;
	}
}