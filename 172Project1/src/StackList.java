/* Name: Maricruz  
 */

public class StackList<T> implements StackListInterface<T> {
	private int size;
	private StackNode<T> head;
    
	// Constructor.
	public StackList() {
		head = new StackNode<T>();
	}
	
	//inserts objects into stack  at the end
	@Override
	public void add(T x) {
    
		StackNode<T> insertedNode = new StackNode<T>();
		insertedNode.data = x;
		insertedNode.next = head.next;
		head.next = insertedNode;
        ++size;  
	}
	
	//delete node from the top
	@Override
	public T delete() {
	
		StackNode<T> deletedNode = head.next;
		head.next = head.next.next;
		--size;	
		return deletedNode.data;
	}
	
	//checks if object is in the list
	@Override
	public boolean present(T x) {
		
		StackNode<T> currentNode = head;
		
		//runs until object is found 
		while (currentNode.next != null){ 
			if (currentNode.next.data == x) {
				return true;
			
			}else { 
				currentNode = currentNode.next;
		
			} 
		}	
		return false;
	}	
	
	//searches for wanted object and returns whether it was found or not	
	@Override
	public T search(T x) {
		
		T returnValue = null;		
		StackNode<T> currentNode = head;
		
		//runs until object is found in list
		while (currentNode.next != null) 
		{ 
			if (currentNode.next.data == x) { 
				returnValue = x;
			
			}else {
				currentNode = currentNode.next;
			} 
		}	
		return returnValue;	
	}

	//returns size of list
	public int returnSize() {
	
		return size;
	}
	
	//checks if it is empty
	@Override
	public boolean isEmpty() {
		return returnSize() == 0;
	}
		
	// looks at first element in list
	public T peek() {
		return head.next.data;
	}
	
	//print list
	@Override
	public void printList() {
    	
		StackNode<T> currentNode = head;
		
		
		while (currentNode.next != null) { 
			System.out.print(currentNode.next.data + " "); 
			
			currentNode = currentNode.next;
		} 
	}

	@Override
	public boolean isPresent(T x) {
		
		return false;
	}
}
