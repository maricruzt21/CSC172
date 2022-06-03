/* Name: Maricruz Tolosa Amaya
 * CSC 172 Project#2
 * 
 */


This is my submission for Project #2 - Point Location. 

The files included are: 
BSTree.java: for the Binary Trees
Line.java: creates Lines based on Points
Point.java: creates points
TreeNode.java: create nodes
Test.java: gets the info from the lab and tests it two different ways
Input1.txt : from the lab prompt
Output.txt: output from Test


Snyposis of Code: 
The Point class can set and get the values of the X and Y values of a Point. In the Line class, a Line has a start point and an end point. There are methods that can get the values of the start point and end point. There is a method that will return true or false if the lines intersects with one another. I renamed the ccw method given in the lab as pointDirection. It returns either 0, 1, or 2, to represent counterclockwise, clockwise, and collinear, respectively. The TreeNode class get take in a Line and can get and set parent, left and right nodes. There is a method that will first check if the lines are intersection with one another, then it will be added to the left side and to the right side. If either the right Node and the left node is null, then it will just simply replace the null and add to it. But if the nodes are not null, then it will recursively call the function until it finds a node that is null. If the two lines don't intersect, then the ccw/pointDirection method will be used to determine if it is either counterclockwise or clockwise and colinear. I grouped clockwise and collinear together. If the lines are counterclockwise of each other, then it will simply add to the left node. If the lines are clockwise or collinear, then it will add to the right node. The BSTrees creates a Line array and adds to it. There is a getZone method which will show the path that the point goes through. 
The test class uses the points/lines from the lab prompt to test it out. For the text files, the lines had to be parsed. The results are shown in Output.txt. 

If you need to test a program, change the file name in the Test class (replace Input1.txt). 

To Run: 
1. Open terminal/command
2.Change directory 
3. Compile with javac *.java (to compile  java source files)
4. Run Test.java by: java Test
