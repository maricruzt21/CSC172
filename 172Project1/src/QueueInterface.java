/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * CSC 172 Project#1
 * Lab: MW 2:00-3:15
 * 
 */
public interface QueueInterface<T> {
	public boolean isEmpty();
	public void enqueue(T x);
	public T dequeue();
	public T peek();
}
