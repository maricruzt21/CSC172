/* Name: Maricruz
 * 
 */
public class QueueList<T> implements QueueListInterface<T> {
	private int size; 
	private QueueNode<T> head; 
	private QueueNode<T> tail;
	
	// Constructor.
		public QueueList() 
		{
			head = new QueueNode<T>();
			tail = new QueueNode<T>();
			head.next = tail;
			tail.previous = head;
		}
	
	@Override
	public void insert(T x) {
		doInsert(x);
		
	}
	// Internal method to insert element at tail.
	private void doInsert(T x) {	
		// Variable declaration.
		QueueNode<T> currentNode = tail;
		QueueNode<T> insertedNode = new QueueNode<T>();
		insertedNode.data = x;
		
		// Insert node at tail.
		insertedNode.previous = currentNode.previous;
		currentNode.previous.next = insertedNode;
		insertedNode.next = currentNode;
		currentNode.previous = insertedNode;
		
		++size;
  	}
	@Override
	public T delete() {
		
			return doDelete();
	
	}
	
	private T doDelete() 
	{		
		QueueNode<T> deletedNode = head.next;
		head.next.next.previous = head;
		head.next = head.next.next;
		--size;
		return deletedNode.data;					
	}
	
	// Method to determine if element of interest is in DLL.
	@Override
	public boolean isPresent(T x) {
		return doIsPresent(x);
	}
	
	// Internal method to determine if element of interest is in DLL.
		private boolean doIsPresent(T x) 
		{
			QueueNode<T> currentNode = head;
			
			// Iterate until x is found in DLL.
			while (currentNode.next != null) 
			{ 
				if (currentNode.next.data == x)
					return true;
				
				else 
					currentNode = currentNode.next;	
			} 
				
			return false;
		}	
		
		// Method to look up and return value of element of interest.	
	@Override
	public T lookup(T x) {
		return doLookup(x);
	}
	// Internal method to look up and return value of element of interest.
		private T doLookup(T x) 
		{	
			T returnValue = null;
				
			QueueNode<T> currentNode = head;
			
			// Iterate until x is found in DLL.
			while (currentNode.next != null) 
			{ 	
				if (currentNode.next.data == x) 
					returnValue = x;
				
				else 
					currentNode = currentNode.next;
			} 
				
			return returnValue;	
		}
	
	@Override
	public boolean isEmpty() {
		return returnSize() == 0;
	}
	// Method to return size of DLL.
		public int returnSize() 
		{
			return doReturnSize();
		}
		
		// Internal method to return size of DLL.
		public int doReturnSize() 
		{
			return size;
		}
		
	public void printList() {
		doPrintList();
        System.out.println();
		
	}
	// Internal method to print list.
		private void doPrintList() 
		{
			QueueNode<T> currentNode = head;
			
			// Iterate through DLL.
			while (currentNode.next.next != null) 
			{ 
				System.out.print(currentNode.next.data + " "); 
				
				currentNode = currentNode.next;
			} 	
		}


		public T peek()
		{
			return head.next.data;
		}
}
