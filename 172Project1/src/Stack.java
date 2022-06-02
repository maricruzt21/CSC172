/* Name: Maricruz 
 */

public class Stack<T> implements StackInterface<T> {
	public StackList<T> list = new StackList<T>();
	
	
	//can only delete stuff while there is something in the stack
	public Stack() {
		while (!list.isEmpty())
			list.delete();
	}
	//checks to see if stack is empty
	@Override
	public boolean isEmpty() {
		
		return list.isEmpty(); 
	}
	//puts the given object to the top of stack(front if linked list)
		@Override
		public void push(T x) {
			 
			list.add(x);
		}
		
		//removes and returns the top (most recently inserted) item from the stack 
		//returns null if stack is empty
		@Override
		public T pop() {
			if(isEmpty()) {
				return null; //nothing in list
			}else {
				return list.delete(); //returns th object that was deleted
			}
			
		}
		//shows what is on top but does not remove it
		@Override
		public T peek() {
			
			return list.peek();
		}
		public int returnSize()
		{
			return list.returnSize();
		}
	
}
