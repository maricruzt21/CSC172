/* Name: Maricruz
 */
public class Queue<T> implements QueueInterface<T> {
	public QueueList<T> list = new QueueList<T>();
	
	public Queue() {
		//can only delete while there is something in the queue
		while (!list.isEmpty())
			list.delete();
	}
	
	//checking if there is something in queue or not
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	//adding
	@Override
	public void enqueue(T x) {
		list.insert(x);
	}

	//delete
	@Override
	public T dequeue() {
		return list.delete();
	}
	
	//checking but not removing what is on top
	@Override
	public T peek() 
	{
		return list.peek();
	}
	
	//returns size of queue
	public int returnSize() 
	{
		return list.returnSize();
	}
}
