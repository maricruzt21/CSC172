/* Name: Maricruz 
 * CSC 172 Project#2

 * 
 */
public class TreeNode {
	TreeNode left, right, parent;
	TreeNode root;
	Line lineVal;
	int depth = 0;
	
	
	TreeNode() {

	}
	TreeNode(Line a) {
		lineVal = a;
		depth =0;
		
	}
	TreeNode(Line a, int depth) {
		lineVal = a;
		this.depth = depth;
		
	}

	//getters and setters
	public TreeNode getParent() {
		return parent;
	}

	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}

	public Line getLine() {
		return lineVal;
	}

	public void setLeft(TreeNode newLeft) {
		left = newLeft;
	}

	public void setRight(TreeNode newRight) {
		right = newRight;

	}

	public void setParent(TreeNode newParent) {
		parent = newParent;
	}

	public void setLine(Line x) {
		lineVal = x;
	}
	
	public String toString() {
		String tab = "\n";
		for(int i = 0; i <= depth; i++) {
			tab += "\t";
		}
		return   lineVal.toString() + tab + "right: " + right + tab+ "left: " + left;
	}
	
	private int numOfExternalNodes() {
		int value = left == null || right ==null ? 1 : 0;
		int leftValue = left == null ? 0 : left.numOfExternalNodes();
		int rightValue = right == null ? 0: right.numOfExternalNodes();
		
		return value + leftValue + rightValue;
	}

	public double avgPathLength() {
		System.out.println("Num of external nodes: " + numOfExternalNodes());
		return depth/ numOfExternalNodes();
	}
	public void addLineToTree(Line x) {
		
		
		// checks for intersection
		if (lineVal.intersection(x) == true) {
			//check if left = null
			if(left == null) {
				left = new TreeNode(x, depth+1); //simply add to it
			}else { //not null
				left.addLineToTree(x); //recursive
			}
			
			if(right == null) { //right child is null
				right = new TreeNode(x,  depth+1); //add to it since it is null
			}else {
				right.addLineToTree(x); //continues to go through 
			}
			
		}else {//not intersection
			// 0 = counterclockwise
			// 1 = clockwise
			// 2 = colinear

			//getting the point direction
			int xPointDir = lineVal.pointDirection(x.getPoint1());
			int xPointDir2 = lineVal.pointDirection(x.getPoint2());
			
			if(xPointDir == 0) {
				if(left == null) {
					left = new TreeNode(x, depth+1);
				}else {
					left.addLineToTree(x);
				}
			}else {
				if(right == null) {
					right = new TreeNode(x,  depth+1);
				}else {
					right.addLineToTree(x);
				}
			}
		}

	}

}
