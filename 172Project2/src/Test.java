/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#2
 * Lab: MW 2:00-3:15
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws Exception {
		
		//Testing program
		Line[] lineArray = new Line[5];
		
		//creating lines
		lineArray[0] = new Line(0.00, 0.12, 0.23, 1.00, 0);
		lineArray[1] = new Line(1.00, 0.41, 0.00, 0.52, 1);
		lineArray[2] = new Line(1.00, 0.20, 0.30, 1.00, 2);
		lineArray[3] = new Line(0.00, 0.40, 0.10, 0.00, 3);
		lineArray[4] = new Line(1.00, 0.35, 0.10, 1.00, 4);
		
		
		
		//creating and printing binary tree 
		BSTrees tree = new BSTrees(lineArray);
		System.out.println(tree.toString());
		
		//creating the testing points and getting their zones
		Point a = new Point(0.25, 0.80);
		Point b = new Point(0.60, 0.50);
		Point c = new Point(0.95, 0.10);
		Point d = new Point(0.11, 0.50);
		System.out.println(tree.getZone(a));
		System.out.println(tree.getZone(b));
		System.out.println(tree.getZone(c));
		System.out.println(tree.getZone(d));
		
		System.out.println();
		
		
		//text file
		Scanner scanText = new Scanner(new File("Input1.txt"));
		
		//getting number of lines
		File input1 = new File("Input1.txt");
		FileInputStream inputVal = new FileInputStream(input1);
		byte[] byteArray = new byte[(int)input1.length()];
		inputVal.read(byteArray);
		String data = new String(byteArray);
		String [] stringArray = data.split("\n");
		System.out.println("Num of lines in file: " + stringArray.length);
		
		//creating a Line array 
		int N = Integer.parseInt(stringArray[0]);
		Line[] textLineArray = new Line[N];
		for(int i = 1; i < N+1; i++) {
			String[] lineSplit = stringArray[i].split(" ");
			Line newLine = new Line(Double.parseDouble(lineSplit[0]), Double.parseDouble(lineSplit[1]), 
									Double.parseDouble(lineSplit[2]), Double.parseDouble(lineSplit[3]), i-1);
			
			textLineArray[i-1] = newLine;
			
		}
		
		//BST for the text file
		BSTrees textTree = new BSTrees(textLineArray);
		System.out.println(textTree.toString());
		
		
		//points 
		Point[][] pointArray = new Point[stringArray.length - N - 1][2];
		for(int i = N+1; i < stringArray.length; i++) {
			String[] pointSplit = stringArray[i].split(" ");
			Point newPoint1 = new Point(Double.parseDouble(pointSplit[0]), Double.parseDouble(pointSplit[1]));
			Point newPoint2 = new Point(Double.parseDouble(pointSplit[2]), Double.parseDouble(pointSplit[3]));
			
			pointArray[stringArray.length - i - 1][0] = newPoint1;
			pointArray[stringArray.length - i - 1][1] = newPoint2;
			System.out.println(textTree.getZone(newPoint1));
			System.out.println(textTree.getZone(newPoint2));
			
			
			
		}
		
		
		
	}
	
	
	
	
}
