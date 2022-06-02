/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#2
 * Lab: MW 2:00-3:15
 * 
 */

public class BSTrees {
	public TreeNode root;
	
	//constructor
	public BSTrees(Line[] lineArray) {
		
		root = new TreeNode(lineArray[0]);
		for(int i = 1; i < lineArray.length; i++) {
			root.addLineToTree(lineArray[i]);
			
		}
		root.avgPathLength();
	}
	
	
	@Override
	public String toString() {
		return "root: " + root.toString( );
	}

	public String getZone(Point a) {
		TreeNode curr = root;
		String result = "";
		
		while(curr != null) {
			result += curr.getLine().getID() + "/";
			int direction = curr.getLine().pointDirection(a);
			if(direction == 0) {
				curr = curr.getLeft();
				
			}else {
				curr = curr.getRight();
			}
		}
		
		return result;
		
	}
	
	
	
}
